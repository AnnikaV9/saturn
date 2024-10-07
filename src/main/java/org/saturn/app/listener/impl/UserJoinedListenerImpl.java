package org.saturn.app.listener.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.saturn.app.command.impl.moderator.AutoMoveUserCommandImpl;
import org.saturn.app.facade.EngineType;
import org.saturn.app.facade.impl.EngineImpl;
import org.saturn.app.listener.Listener;
import org.saturn.app.model.dto.User;

import static org.saturn.app.command.impl.moderator.AutoMoveUserCommandImpl.AUTHORIZED_LOUNGE_TRIPS;
import static org.saturn.app.command.impl.moderator.AutoMoveUserCommandImpl.CHANNEL;
import static org.saturn.app.command.impl.moderator.AutoMoveUserCommandImpl.SOURCE_CHANNEL;
import static org.saturn.app.util.Util.gson;

@Slf4j
public class UserJoinedListenerImpl implements Listener {
    @Override
    public String getListenerName() {
        return "joinListener";
    }

    private final EngineImpl engine;

    public UserJoinedListenerImpl(EngineImpl engine) {
        this.engine = engine;
    }

    @Override
    public void notify(String jsonText) {
        JsonElement element = JsonParser.parseString(jsonText);
        JsonObject object = element.getAsJsonObject();
        User user = gson.fromJson(object, User.class);
        log.info("Joined user, nick: {}, trip: {}, hash: {}, channel: {}", user.getNick(), user.getTrip(), user.getHash(), user.getChannel());

        engine.addActiveUser(user);
        engine.shareUserInfo(user);
        engine.proceedShadowBanned(user);
        /* AutoMoveCommand has been triggered */
        if (AutoMoveUserCommandImpl.isAutoMoveStatus() && engine.engineType.equals(EngineType.REPLICA) && engine.channel.equals(SOURCE_CHANNEL)) {
            log.warn("AutoMoveCommand feature flag is true");
            if (AUTHORIZED_LOUNGE_TRIPS.contains(user.getTrip())) {
                engine.outService.enqueueMessageForSending(user.getNick(), " your trip is authorized to join ?lounge, you will be moved to ?lounge", false);
                engine.modService.kickTo(user.getNick(), CHANNEL);
                log.info("User: {}, has been moved to: {}", user.getNick(), CHANNEL);
            }
        }
    }
}

/*
 *grant AAfFKK USER
 *grant VEbeHK USER
 *grant NO/4w4 USER
 *grant Myh1TA USER
 *grant 2ZQ3+0 USER
 *grant coBad2 USER
 *grant 9kQGU6 USER
 *grant +BBiCm USER
 *grant cmdTV+ USER
 *grant aoXWSB USER
 *grant for9zT USER
 *grant zV2BBB USER
 *grant FaAfFY USER
 *grant sDMF6Q USER
 *grant jYTF8t USER
 *grant F7IuX2 USER
 *grant gOtnKd USER
 *grant 0UTOss USER
 *grant 3/e804 USER
 *grant A8FTOC USER
 *grant /JoyWo USER
 *grant 48wNI7 USER
 *grant k5uRbC USER
 *grant Jpr4bJ USER
 *grant IC3bl5 USER
 *grant /StGZu USER
 *grant MQkpTM USER
 *grant jeyM4l USER
 *grant FQ2U+8 USER
 *grant +fs0AT USER
 *grant Tar/// USER
 *grant IiPtqX USER
 *grant 6M6hbr USER
 *grant LY105Q USER
 *grant R5n5dC USER
 *grant fzWxIe USER
 *grant eyJdud USER
 *grant 1G6EnU USER
 *grant vtFJUL USER
 *grant vuPizP USER
 *grant ZIYSBT USER
 *grant 9A2yhx USER
 *grant 0/JM7u USER
 *grant ToP++E USER
 *grant u3rwOv USER
 *grant OSArw7 USER
 *grant CrvQXO USER
 *grant 6Xgj9g USER
 *grant foMeFv USER
 *grant XDL9Nb USER
 *grant mgcSSR USER
 *grant godDDS USER
 *grant /DeDr/ USER
 *grant bPi8nj USER
 *grant LrziAI USER
 *grant V2V5f7 USER
 *grant ko60fH USER
 *grant eHsdHe USER
 *grant 9pP6M5 USER
 *grant k7jgLY USER
 *grant txMoon USER
 *grant ezp/5u USER
 *grant j156Wo USER
 *grant dnS+hr USER
 *grant Zvoxsl USER
 *grant RFa+gs USER
 *grant mBN1Ek USER
 *grant utcAWA USER
 *grant NJRDQJ USER
 *grant wwDQww USER
 *grant DQJCph USER
 *grant HCBt3b USER
 *grant WEBPut USER
 *grant 0AKKA0 USER
 *grant USAriP USER
 *grant aiwLKl USER
 *grant Hi/UVU USER
 *grant /9Br2y USER
 *grant ViMXVG USER
 *grant MRx/sb USER
 *grant IRC/Gz USER
 *grant C5Puj6 USER
 *grant CJ0kky USER
 *grant aEWCdx USER
 *grant BpC+MG USER
 *grant xI/cmd USER
 */

/*
 *grant jYTF8t USER
 *grant F7IuX2 USER
 *grant gOtnKd USER
 *grant 0UTOss USER
 *grant 3/e804 USER
 *grant A8FTOC USER
 *grant /JoyWo USER
 *grant 48wNI7 USER
 *grant k5uRbC USER
 *grant Jpr4bJ USER
 *grant IC3bl5 USER
 *grant /StGZu USER
 *grant MQkpTM USER
 *grant jeyM4l USER
 *grant FQ2U+8 USER
 *grant +fs0AT USER
 *grant Tar/// USER
 *grant IiPtqX USER
 *grant 6M6hbr USER
 *grant LY105Q USER
 *grant R5n5dC USER
 *grant fzWxIe USER
 *grant eyJdud USER
 *grant 1G6EnU USER
 *grant vtFJUL USER
 *grant vuPizP USER
 *grant ZIYSBT USER
 *grant 9A2yhx USER
 *grant 0/JM7u USER
 *grant ToP++E USER
 *grant u3rwOv USER
 *grant OSArw7 USER
 *grant CrvQXO USER
 *grant 6Xgj9g USER
 *grant foMeFv USER
 *grant XDL9Nb USER
 *grant mgcSSR USER
 *grant godDDS USER
 *grant /DeDr/ USER
 *grant bPi8nj USER
 *grant LrziAI USER
 *grant V2V5f7 USER
 *grant ko60fH USER
 *grant eHsdHe USER
 *grant 9pP6M5 USER
 *grant k7jgLY USER
 *grant txMoon USER
 *grant ezp/5u USER
 *grant j156Wo USER
 *grant dnS+hr USER
 *grant Zvoxsl USER
 *grant RFa+gs USER
 *grant mBN1Ek USER
 *grant utcAWA USER
 *grant NJRDQJ USER
 *grant wwDQww USER
 *grant DQJCph USER
 *grant HCBt3b USER
 *grant WEBPut USER
 *grant 0AKKA0 USER
 *grant USAriP USER
 *grant aiwLKl USER
 *grant Hi/UVU USER
 *grant /9Br2y USER
 *grant ViMXVG USER
 *grant MRx/sb USER
 *grant IRC/Gz USER
 *grant C5Puj6 USER
 *grant CJ0kky USER
 *grant aEWCdx USER
 *grant BpC+MG USER
 *grant xI/cmd USER

 */
