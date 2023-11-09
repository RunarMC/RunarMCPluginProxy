package net.runarmc.runarmcpluginproxy.commands.moderation;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.runarmc.kernel.packets.client.CustomClientPacket;
import net.runarmc.runarmcpluginproxy.RunarMCPluginProxy;
import net.runarmc.runarmcpluginproxy.commands.AbstractCommand;

public class CommandGKick extends AbstractCommand {
    public CommandGKick() {
        super("gkick");
    }

    @Override
    public void onExecute(CommandSender sender, String[] arguments) {
        if (arguments.length < 1) {
            sender.sendMessage(FOX_TAG + " Missing arguments.");
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("player", arguments[0]);
        body.addProperty("reason", arguments[1]);
        body.addProperty("createdAt", System.currentTimeMillis());
        body.addProperty("by", ((ProxiedPlayer)sender).getUUID());

        RunarMCPluginProxy.getInstance()
                .getKernelClient()
                .getClient()
                .sendTCP(new CustomClientPacket("create_kick", body.toString()));
    }
}
