package com.rappytv.autosilentlobby;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoSilentLobbyAddon extends LabyAddon<AutoSilentLobbyConfig> {

    @Override
    protected void enable() {
        registerSettingCategory();
    }

    @Override
    protected Class<? extends AutoSilentLobbyConfig> configurationClass() {
        return AutoSilentLobbyConfig.class;
    }
}
