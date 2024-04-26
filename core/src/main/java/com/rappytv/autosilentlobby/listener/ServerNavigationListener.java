package com.rappytv.autosilentlobby.listener;

import com.rappytv.autosilentlobby.AutoSilentLobbyAddon;
import com.rappytv.autosilentlobby.AutoSilentLobbyConfig;
import com.rappytv.autosilentlobby.api.IHotbarApi;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;

public class ServerNavigationListener {

    private final AutoSilentLobbyConfig config;
    private final IHotbarApi api;

    public ServerNavigationListener(AutoSilentLobbyAddon addon) {
        this.config = addon.configuration();
        this.api = addon.getApi();
    }

    @Subscribe
    public void onServerJoin(ServerJoinEvent event) {
        if(config.onJoin() && config.servers().contains(event.serverData().address().getHost()))
            silentLobby();
    }

    @Subscribe
    public void onSubServerSwitch(SubServerSwitchEvent event) {
        if(config.onSubserverSwitch() && config.servers().contains(event.serverData().address().getHost()))
            silentLobby();
    }

    private void silentLobby() {
        Laby.labyAPI().minecraft().executeNextTick(() -> {
            api.changeSlot(config.slot() - 1);
            api.click(config.clickType());
        });
    }
}
