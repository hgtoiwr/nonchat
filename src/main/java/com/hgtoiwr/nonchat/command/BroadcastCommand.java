package com.hgtoiwr.nonchat.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.hgtoiwr.config.PluginMessages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class BroadcastCommand implements CommandExecutor {

    private PluginMessages messages;

    public BroadcastCommand(PluginMessages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("broadcast") ||
            command.getName().equalsIgnoreCase("bc")) {

            if (!sender.hasPermission("nonchat.broadcast")) {
                sender.sendMessage(Component.text()
                        .append(Component.text(messages.getNoPermission(), TextColor.fromHexString("#ADF3FD")))
                        .build());
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(Component.text()
                        .append(Component.text(messages.getBroadcastCommand(), TextColor.fromHexString("#ADF3FD")))
                        .build());
                return true;
            }

            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(arg).append(" ");
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(" ");
                player.sendMessage(Component.text()
                        .append(Component.text(messages.getBroadcast(), TextColor.fromHexString("#E088FF")))
                        .append(Component.text(message.toString().trim(), TextColor.fromHexString("#FFFFFF")))
                        .build());
                player.sendMessage(" ");

                Bukkit.getConsoleSender().sendMessage(Component.text()
                        .append(Component.text(messages.getBroadcast(), TextColor.fromHexString("#E088FF")))
                        .append(Component.text(message.toString().trim(), TextColor.fromHexString("#FFFFFF")))
                        .build());
            }
            return true;
        }
        return false;
    }
}
