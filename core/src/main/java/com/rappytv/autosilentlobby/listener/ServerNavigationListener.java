package com.rappytv.autosilentlobby.listener;

import com.rappytv.autosilentlobby.AutoSilentLobbyAddon;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;

public class ServerNavigationListener {

    private final AutoSilentLobbyAddon addon;

    public ServerNavigationListener(AutoSilentLobbyAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onServerJoin(ServerJoinEvent event) {
        if(addon.configuration().servers().contains(event.serverData().address().getHost()))
            silentLobby();
    }

    @Subscribe
    public void onSubServerSwitch(SubServerSwitchEvent event) {
        if(addon.configuration().servers().contains(event.serverData().address().getHost()))
            silentLobby();
    }

    private void silentLobby() {
        Laby.labyAPI().minecraft().executeNextTick(() -> {
            addon.getApi().changeSlot(addon.configuration().slot() - 1);
            addon.getApi().click(addon.configuration().clickType());
        });
    }
}
