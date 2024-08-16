package org.saturn.app.model.command.impl;

import org.saturn.app.facade.impl.EngineImpl;
import org.saturn.app.model.annotation.CommandAliases;
import org.saturn.app.model.command.UserCommandBaseImpl;
import org.saturn.app.model.dto.payload.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/*
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⢠⣄⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣟⠻⣉⠈⢧⣂⣝⣳⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠁⣹⡯⠉⢀⡸⠿⠋⠉⠛⠻⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⢸⣟⣳⠤⢤⡄⠀⠀⢀⣀⣀⡀⠙⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠋⠀⢰⠾⣻⣿⠛⢒⣤⡶⣿⣻⣛⣟⣿⣙⣺⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⣺⣷⠇⠀⠈⢾⣏⠉⣴⡋⠉⠶⠿⣿⣿⣿⣿⢿⡿⢿⣄⡀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⣞⡵⠋⠩⢭⣁⣀⡀⠀⣿⠀⠹⢧⣾⠤⠬⠁⣽⣛⣛⢹⣷⣬⡇⠉⠳⣄⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⡫⠞⠉⠀⠀⠀⠀⠀⠉⠙⠳⣾⣿⡀⠀⠀⠀⠀⠀⠛⠛⠋⠙⠛⣿⠀⠀⠀⢹⢦⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠏⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⣿⣟⣶⠀⢸⢁⣀⡤⠶⠖⠚⠒⣾⠀⠀⠀⠈⢻⣧⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣷⣌⠳⣄⠀⢀⣀⣠⠟⠁⠀⠀⠀⠀⢸⣿⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣏⢻⠈⠛⢷⣦⣳⣄⣴⠋⠀⣠⡄⠀⠀⠀⠈⣿⡆⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⠤⣤⡶⢶⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢾⠀⠀⠀⠈⠀⠀⠀⠀⢰⡏⠀⠀⠀⠀⠀⣿⡇⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠞⠋⠉⢀⣽⣯⠁⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⢸⠀⠀⠀⠰⡇⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⡏⡇⠀⠀
⠀⠀⠀⠀⠀⠀⢀⣴⠋⠀⠀⠀⠀⢠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠰⠿⠀⢰⣦⣿⣾⡀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⢸⠀⠀⠀⠀⠁⠀⠀⠀⡾⠀⠀⠀⠀⠀⢀⡗⣇⠀⠀
⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠘⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠹⢿⣿⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣸⠀⠀⠀⠀⠀⠀⢀⣼⠇⠀⠀⠀⠀⠀⢺⡇⣿⠀⠀
⠀⠀⠀⠀⠀⢠⡇⣤⠀⠀⠀⠀⢀⡖⢒⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡆⢿⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⡟⢀⡴⠃⠀⣠⢾⣯⣷⡆⠀⠀⠀⠀⠀⠈⠀⠹⡆⠀
⠀⠀⠀⠀⠀⢸⠽⠃⠀⠀⠀⠀⠈⢷⡄⠉⠓⠲⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡆⠀⠀⠀⠀⠀⠀⠀⢻⣰⡟⠋⠀⢀⡼⠁⣼⣅⠈⢿⣷⡀⠀⣸⡇⠀⠀⣷⢷⠀
⠀⠀⠀⠀⠀⣾⠘⠂⠀⠀⠀⠀⠀⠀⠻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⢿⣆⠀⠀⣠⣶⠄⠀⠈⣿⣇⣠⠴⠋⠀⢀⣿⣧⠀⠀⠘⠻⠞⠛⠀⠀⠀⢿⣼⡄
⠀⠀⠀⠀⠀⢸⢰⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣯⡆⠀⠀⠹⣷⢾⠟⠁⠀⠀⢶⣺⣿⠉⠀⠀⠀⢹⣿⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀
⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣧⣄⠠⣤⣤⠀⠀⠀⠀⠀⣾⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⣿⠀⠀⠀⠀⠘⢿⡞⡆⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⡆
⠀⠀⠀⠀⠀⢸⠃⢀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢳⣄⣬⣽⣿⡃⠴⣞⢦⣸⠀⠻⢤⣤⣤⣤⠀⠀⠀⠀⠀⠀⡏⢹⠀⠀⠀⠀⠀⠈⣿⣳⡄⠀⠀⠀⠀⠀⠀⢸⠋⡏⣧
⠀⠀⠀⠀⠀⣼⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⢸⣯⠀⠈⠻⠿⢭⣝⠛⠛⠛⣿⠆⡀⠀⠹⢿⢿⠆⠀⠀⠀⠀⠀⣷⣾⠀⠀⠀⠀⠀⠀⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⡄⢻
⠀⠀⠀⠀⢰⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠏⠀⠀⠀⠀⠀⠈⢷⡀⠀⠹⡿⢻⠇⠀⠀⠀⠀⠀⠀⠀⠀⢸⢿⡇⠀⠀⠀⠀⠀⠀⠀⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿
⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⣀⣠⣤⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⣻⠀⠀⣿⢹⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠈⣧⣀⠀⠀⠀⠀⠀⠀⢠⣿⡏
⠀⠀⠀⣸⡿⠀⠀⠀⠀⠈⠉⠉⣹⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⠀⠀⢹⣟⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⢀⣿⣿⠃
⠀⠀⢠⡟⠁⠀⠀⠀⠀⠀⣰⣾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⣴⣤⣷⣠⣾⣿⠇⠀
⠀⠀⢻⣷⠇⠀⠀⠀⠀⡼⢵⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠹⣷⢀⡀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⣷⠀⠉⠛⠛⠟⠋⣿⠀⠀
⠀⠀⢈⡏⠀⠀⠀⠀⣀⣵⣾⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠓⠲⠶⣿⡞⠁⠀⠀⠀⠀⠀⣀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠿⠿⠶⢾⣧⣄⣠⣀⣤⣾⣿⠀⠀
⢀⣰⡟⠛⡓⢟⣧⣾⢧⣬⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠀⢀⣰⣿⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣽⣅⣿⣼⠿⠛⠁⠀⠀
⠸⢷⣿⡯⠵⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠾⠟⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⡇⢾⣿⢼⣷⣳⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⠚⠙⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
 */
@CommandAliases(aliases = {"ape", "harambe"})
public class ApeUserCommandImpl extends UserCommandBaseImpl {

    private static  final String ape = "\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⢠⣄⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣟⠻⣉⠈⢧⣂⣝⣳⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠁⣹⡯⠉⢀⡸⠿⠋⠉⠛⠻⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⢸⣟⣳⠤⢤⡄⠀⠀⢀⣀⣀⡀⠙⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠋⠀⢰⠾⣻⣿⠛⢒⣤⡶⣿⣻⣛⣟⣿⣙⣺⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⣺⣷⠇⠀⠈⢾⣏⠉⣴⡋⠉⠶⠿⣿⣿⣿⣿⢿⡿⢿⣄⡀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⣞⡵⠋⠩⢭⣁⣀⡀⠀⣿⠀⠹⢧⣾⠤⠬⠁⣽⣛⣛⢹⣷⣬⡇⠉⠳⣄⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⡫⠞⠉⠀⠀⠀⠀⠀⠉⠙⠳⣾⣿⡀⠀⠀⠀⠀⠀⠛⠛⠋⠙⠛⣿⠀⠀⠀⢹⢦⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠏⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⣿⣟⣶⠀⢸⢁⣀⡤⠶⠖⠚⠒⣾⠀⠀⠀⠈⢻⣧⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣷⣌⠳⣄⠀⢀⣀⣠⠟⠁⠀⠀⠀⠀⢸⣿⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣏⢻⠈⠛⢷⣦⣳⣄⣴⠋⠀⣠⡄⠀⠀⠀⠈⣿⡆⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⠤⣤⡶⢶⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢾⠀⠀⠀⠈⠀⠀⠀⠀⢰⡏⠀⠀⠀⠀⠀⣿⡇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠞⠋⠉⢀⣽⣯⠁⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⢸⠀⠀⠀⠰⡇⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⡏⡇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣴⠋⠀⠀⠀⠀⢠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠰⠿⠀⢰⣦⣿⣾⡀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⢸⠀⠀⠀⠀⠁⠀⠀⠀⡾⠀⠀⠀⠀⠀⢀⡗⣇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠘⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠹⢿⣿⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣸⠀⠀⠀⠀⠀⠀⢀⣼⠇⠀⠀⠀⠀⠀⢺⡇⣿⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢠⡇⣤⠀⠀⠀⠀⢀⡖⢒⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡆⢿⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⡟⢀⡴⠃⠀⣠⢾⣯⣷⡆⠀⠀⠀⠀⠀⠈⠀⠹⡆⠀\n" +
            "⠀⠀⠀⠀⠀⢸⠽⠃⠀⠀⠀⠀⠈⢷⡄⠉⠓⠲⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡆⠀⠀⠀⠀⠀⠀⠀⢻⣰⡟⠋⠀⢀⡼⠁⣼⣅⠈⢿⣷⡀⠀⣸⡇⠀⠀⣷⢷⠀\n" +
            "⠀⠀⠀⠀⠀⣾⠘⠂⠀⠀⠀⠀⠀⠀⠻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⢿⣆⠀⠀⣠⣶⠄⠀⠈⣿⣇⣠⠴⠋⠀⢀⣿⣧⠀⠀⠘⠻⠞⠛⠀⠀⠀⢿⣼⡄\n" +
            "⠀⠀⠀⠀⠀⢸⢰⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣯⡆⠀⠀⠹⣷⢾⠟⠁⠀⠀⢶⣺⣿⠉⠀⠀⠀⢹⣿⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀\n" +
            "⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣧⣄⠠⣤⣤⠀⠀⠀⠀⠀⣾⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⣿⠀⠀⠀⠀⠘⢿⡞⡆⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⡆\n" +
            "⠀⠀⠀⠀⠀⢸⠃⢀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢳⣄⣬⣽⣿⡃⠴⣞⢦⣸⠀⠻⢤⣤⣤⣤⠀⠀⠀⠀⠀⠀⡏⢹⠀⠀⠀⠀⠀⠈⣿⣳⡄⠀⠀⠀⠀⠀⠀⢸⠋⡏⣧\n" +
            "⠀⠀⠀⠀⠀⣼⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⢸⣯⠀⠈⠻⠿⢭⣝⠛⠛⠛⣿⠆⡀⠀⠹⢿⢿⠆⠀⠀⠀⠀⠀⣷⣾⠀⠀⠀⠀⠀⠀⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⡄⢻\n" +
            "⠀⠀⠀⠀⢰⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠏⠀⠀⠀⠀⠀⠈⢷⡀⠀⠹⡿⢻⠇⠀⠀⠀⠀⠀⠀⠀⠀⢸⢿⡇⠀⠀⠀⠀⠀⠀⠀⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿\n" +
            "⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⣀⣠⣤⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⣻⠀⠀⣿⢹⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠈⣧⣀⠀⠀⠀⠀⠀⠀⢠⣿⡏\n" +
            "⠀⠀⠀⣸⡿⠀⠀⠀⠀⠈⠉⠉⣹⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⠀⠀⢹⣟⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⢀⣿⣿⠃\n" +
            "⠀⠀⢠⡟⠁⠀⠀⠀⠀⠀⣰⣾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⣴⣤⣷⣠⣾⣿⠇⠀\n" +
            "⠀⠀⢻⣷⠇⠀⠀⠀⠀⡼⢵⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠹⣷⢀⡀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⣷⠀⠉⠛⠛⠟⠋⣿⠀⠀\n" +
            "⠀⠀⢈⡏⠀⠀⠀⠀⣀⣵⣾⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠓⠲⠶⣿⡞⠁⠀⠀⠀⠀⠀⣀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠿⠿⠶⢾⣧⣄⣠⣀⣤⣾⣿⠀⠀\n" +
            "⢀⣰⡟⠛⡓⢟⣧⣾⢧⣬⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠀⢀⣰⣿⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣽⣅⣿⣼⠿⠛⠁⠀⠀\n" +
            "⠸⢷⣿⡯⠵⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠾⠟⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⡇⢾⣿⢼⣷⣳⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⠚⠙⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    private final List<String> aliases = new ArrayList<>();

    public ApeUserCommandImpl(EngineImpl engine, ChatMessage message, List<String> aliases) {
        super(message, engine, List.of("x"));
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
        String author = chatMessage.getNick();
        engine.outService.enqueueMessageForSending(author," " + ape.replace("\n","\\n"), isWhisper());
    }
}
