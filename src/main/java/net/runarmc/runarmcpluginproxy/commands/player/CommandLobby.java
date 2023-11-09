package net.runarmc.runarmcpluginproxy.commands.player;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.runarmc.runarmcpluginproxy.commands.AbstractCommand;

public class CommandLobby extends AbstractCommand {
    public CommandLobby() {
        super("lobby");
    }

    @Override
    public void onExecute(CommandSender sender, String[] arguments) {
        ProxiedPlayer player = (ProxiedPlayer) sender;
        player.connect(ProxyServer.getInstance().getServerInfo("lobby"), new Callback<Boolean>() {
            @Override
            public void done(Boolean result, Throwable error) {
                if (!result) {
                    player.sendMessage(FOX_TAG + " Unable to connect you to the lobby.");
                } else {
                    player.sendMessage(FOX_TAG + " Connecting to lobby...");
                }

                done(result, error);
            }
        });
    }
}
