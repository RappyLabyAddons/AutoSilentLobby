package com.rappytv.autosilentlobby.listener;

import com.rappytv.autosilentlobby.AutoSilentLobbyAddon;
import com.rappytv.autosilentlobby.AutoSilentLobbyConfig;
import com.rappytv.autosilentlobby.api.InteractionApi;
import java.util.concurrent.TimeUnit;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;
import net.labymod.api.event.client.world.WorldEnterEvent;
import net.labymod.api.event.client.world.WorldEnterEvent.Type;
import net.labymod.api.util.concurrent.task.Task;

public class ServerNavigationListener {

    private final AutoSilentLobbyConfig config;
    private final InteractionApi interactionApi;
    private final Task executionTask;

    public ServerNavigationListener(AutoSilentLobbyAddon addon) {
        this.config = addon.configuration();
        this.interactionApi = AutoSilentLobbyAddon.references().interactionApi();
        this.executionTask = Task.builder(() -> {
            this.interactionApi.changeSlot(this.config.slot() - 1);
            for (int i = 0; i < this.config.clickAmount(); i++) {
                this.interactionApi.click(this.config.clickType());
            }
        }).delay(200, TimeUnit.MILLISECONDS).build();
    }

    @Subscribe
    public void onWorldJoin(WorldEnterEvent event) {
        if (event.type() != Type.SINGLEPLAYER) {
            return;
        }
        if (this.config.onSinglePlayerWorld() && !this.preventSignClick()) {
            this.executeActions();
        }
    }

    @Subscribe
    public void onServerJoin(ServerJoinEvent event) {
        if (this.config.onJoin()
            && this.config.servers().contains(event.serverData().address().getHost())
            && !this.preventSignClick()) {
            this.executeActions();
        }
    }

    @Subscribe
    public void onSubServerSwitch(SubServerSwitchEvent event) {
        if (this.config.onSubserverSwitch()
            && this.config.servers().contains(event.serverData().address().getHost())
            && !this.preventSignClick()) {
            this.executeActions();
        }
    }

    private void executeActions() {
        this.executionTask.execute();
    }

    @SuppressWarnings("all")
    private boolean preventSignClick() {
        return this.config.preventSignClick() && this.interactionApi.isLookingAtSign();
    }
}
