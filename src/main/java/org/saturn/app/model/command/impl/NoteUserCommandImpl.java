package org.saturn.app.model.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.saturn.app.facade.impl.EngineImpl;
import org.saturn.app.model.annotation.CommandAliases;
import org.saturn.app.model.command.UserCommandBaseImpl;
import org.saturn.app.model.dto.payload.ChatMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.saturn.app.util.Util.getWhiteListedTrips;
import static org.saturn.app.util.Util.listToString;

@Slf4j
@CommandAliases(aliases = {"note", "save"})
public class NoteUserCommandImpl extends UserCommandBaseImpl {
    private final List<String> aliases = new ArrayList<>();

    public NoteUserCommandImpl(EngineImpl engine, ChatMessage message, List<String> aliases) {
        super(message, engine, getWhiteListedTrips(engine));
        super.setAliases(this.getAliases());
        this.aliases.addAll(aliases);
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public List<String> getArguments() {
        return super.getArguments();
    }

    @Override
    public void execute() {
        Optional<String> trip = Optional.ofNullable(chatMessage.getTrip());
        boolean isEmpty = getArguments().stream().findFirst().isEmpty();
        if (isEmpty) {
            engine.outService.enqueueMessageForSending(chatMessage.getNick(), engine.prefix + "note Jedi am I?!", isWhisper());
            log.info("Executed [note] command by user: {}", chatMessage.getNick());
            return;
        }

        trip.ifPresent(s -> engine.noteService.save(s, listToString(getArguments())));

        engine.outService.enqueueMessageForSending(chatMessage.getNick(), "note successfully saved!", isWhisper());
        log.info("Executed [note] command by user: {}", chatMessage.getNick());
    }
}
