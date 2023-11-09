package net.runarmc.runarmcpluginproxy.config;

public class IConfig {
    private String clientId;
    private String serverName;

    public String getClientId() {
        return clientId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
