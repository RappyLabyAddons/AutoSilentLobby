package com.rappytv.autosilentlobby.listener;

import com.rappytv.autosilentlobby.AutoSilentLobbyAddon;
import com.rappytv.autosilentlobby.AutoSilentLobbyConfig;
import com.rappytv.autosilentlobby.api.IHotbarApi;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;
import net.labymod.api.event.client.world.WorldEnterEvent;
import net.labymod.api.event.client.world.WorldEnterEvent.Type;
import java.util.Timer;
import java.util.TimerTask;

public class ServerNavigationListener {

    private final AutoSilentLobbyConfig config;
    private final IHotbarApi api;
    private final Timer timer = new Timer();

    public ServerNavigationListener(AutoSilentLobbyAddon addon) {
        this.config = addon.configuration();
        this.api = addon.getApi();
    }

    @Subscribe
    public void onWorldJoin(WorldEnterEvent event) {
        if(event.type() != Type.SINGLEPLAYER) return;
        if(config.onSinglePlayerWorld())
            silentLobby();
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
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                api.changeSlot(config.slot() - 1);
                api.click(config.clickType());
            }
        }, 200);
    }
}
