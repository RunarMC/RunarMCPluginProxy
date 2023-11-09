package net.runarmc.runarmcpluginproxy.commands.player;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.runarmc.kernel.packets.client.CustomClientPacket;
import net.runarmc.runarmcpluginproxy.RunarMCPluginProxy;
import net.runarmc.runarmcpluginproxy.commands.AbstractCommand;

public class CommandReport extends AbstractCommand {
    public CommandReport() {
        super("report");
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
                .sendTCP(new CustomClientPacket("create_report", body.toString()));

        sender.sendMessage(FOX_TAG + ChatColor.GREEN + " Your report has been sent to the staff.");
        sender.sendMessage(FOX_TAG + ChatColor.GREEN + " Thank you for your fidelity.");
    }
}
