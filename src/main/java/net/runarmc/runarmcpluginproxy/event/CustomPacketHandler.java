package net.runarmc.runarmcpluginproxy.event;

import com.google.common.eventbus.Subscribe;
import net.runarmc.kernel.packets.server.CustomServerPacket;

public class CustomPacketHandler {
    @Subscribe
    public void onCustomPacket(CustomServerPacket event) {
        switch (event.getKey()) {

        }
    }
}
