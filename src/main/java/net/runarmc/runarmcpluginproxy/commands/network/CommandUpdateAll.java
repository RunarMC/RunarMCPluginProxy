package net.runarmc.runarmcpluginproxy.commands.network;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.runarmc.kernel.packets.client.CustomClientPacket;
import net.runarmc.runarmcpluginproxy.RunarMCPluginProxy;
import net.runarmc.runarmcpluginproxy.commands.AbstractCommand;

public class CommandUpdateAll extends AbstractCommand {
    public CommandUpdateAll() {
        super("updateall");
    }

    @Override
    public void onExecute(CommandSender sender, String[] arguments) {
        if ((sender instanceof ProxiedPlayer)) {
            sender.sendMessage(FOX_TAG + " Player cannot execute this command.");
            return;
        }

        if (arguments.length < 1) {
            sender.sendMessage(FOX_TAG + " Missing arguments.");
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("player", arguments[0]);
        body.addProperty("packageId", arguments[1]);

        RunarMCPluginProxy.getInstance()
                .getKernelClient()
                .getClient()
                .sendTCP(new CustomClientPacket("update_all", body.toString()));
    }
}
