package net.runarmc.runarmcpluginproxy;

import com.google.gson.JsonObject;
import com.runarmc.eventbus.StartEventBus;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.runarmc.kernel.KernelClient;
import net.runarmc.kernel.packets.client.CustomClientPacket;
import net.runarmc.runarmcpluginproxy.commands.moderation.CommandGBan;
import net.runarmc.runarmcpluginproxy.commands.moderation.CommandGKick;
import net.runarmc.runarmcpluginproxy.commands.moderation.CommandGWarn;
import net.runarmc.runarmcpluginproxy.commands.moderation.CommandInspect;
import net.runarmc.runarmcpluginproxy.commands.network.CommandUpdateAll;
import net.runarmc.runarmcpluginproxy.commands.player.CommandLobby;
import net.runarmc.runarmcpluginproxy.commands.player.CommandReport;
import net.runarmc.runarmcpluginproxy.commands.network.CommandGInfo;
import net.runarmc.runarmcpluginproxy.event.CustomPacketHandler;
import org.yaml.snakeyaml.Yaml;
import java.util.UUID;

public final class RunarMCPluginProxy extends Plugin {

    private static RunarMCPluginProxy instance;

    private KernelClient kernelClient;
    private StartEventBus startEventBus;

    private Yaml yaml = new Yaml();

    private String serverId = UUID.randomUUID().toString();

    @Override
    public void onEnable() {
        RunarMCPluginProxy.instance = this;

        kernelClient = KernelClient.INSTANCE;
        kernelClient.setClientId("<token>");
        kernelClient.connect("<token>");

        getProxy().getLogger().info("Server Identifier: " + serverId);

        this.startEventBus = this.kernelClient.getStartEventBus();
        this.startEventBus.eventBus.register(new CustomPacketHandler());

        kernelClient.getClient().sendTCP(new CustomClientPacket("register_proxy", generateBody().toString()));

        if (kernelClient.isConnected()) {
            this.getProxy().getPluginManager().registerCommand(this, new CommandGInfo());
            this.getProxy().getPluginManager().registerCommand(this, new CommandUpdateAll());

            this.getProxy().getPluginManager().registerCommand(this, new CommandLobby());
            this.getProxy().getPluginManager().registerCommand(this, new CommandReport());

            this.getProxy().getPluginManager().registerCommand(this, new CommandGBan());
            this.getProxy().getPluginManager().registerCommand(this, new CommandGKick());
            this.getProxy().getPluginManager().registerCommand(this, new CommandGWarn());
            this.getProxy().getPluginManager().registerCommand(this, new CommandInspect());
        } else {
            getProxy().getLogger().severe("Unable to register command when the KernelServer is down.");
        }
    }

    @Override
    public void onDisable() {
        kernelClient.getClient().sendTCP(new CustomClientPacket("unregister_proxy", generateBody().toString()));
        kernelClient.getClient().sendTCP(new CustomClientPacket("broadcast_restart", new JsonObject().toString()));
        kernelClient.getClient().close();
    }

    public KernelClient getKernelClient() {
        return kernelClient;
    }

    public String getServerId() {
        return serverId;
    }

    public static RunarMCPluginProxy getInstance() {
        return instance;
    }

    private JsonObject generateBody() {
        JsonObject body = new JsonObject();
        body.addProperty("serverId", this.serverId);
        body.addProperty("serverProtocol", ProxyServer.getInstance().getProtocolVersion());
        body.addProperty("serverVersion", ProxyServer.getInstance().getVersion());

        return body;
    }
}
