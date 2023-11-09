package net.runarmc.runarmcpluginproxy.commands.moderation;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.CommandSender;
import net.runarmc.kernel.packets.client.CustomClientPacket;
import net.runarmc.runarmcpluginproxy.RunarMCPluginProxy;
import net.runarmc.runarmcpluginproxy.commands.AbstractCommand;

public class CommandInspect extends AbstractCommand {
    public CommandInspect() {
        super("inspect");
    }

    @Override
    public void onExecute(CommandSender sender, String[] arguments) {
        if (arguments.length < 1) {
            sender.sendMessage(FOX_TAG + " Missing arguments.");
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("player", arguments[0]);
        body.addProperty("type", "inspection");

        RunarMCPluginProxy.getInstance()
                .getKernelClient()
                .getClient()
                .sendTCP(new CustomClientPacket("request_inspection", body.toString()));
    }
}
