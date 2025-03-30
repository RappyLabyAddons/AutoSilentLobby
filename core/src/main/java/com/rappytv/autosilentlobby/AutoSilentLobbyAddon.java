package com.rappytv.autosilentlobby;

import com.rappytv.autosilentlobby.api.generated.ReferenceStorage;
import com.rappytv.autosilentlobby.listener.ServerNavigationListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoSilentLobbyAddon extends LabyAddon<AutoSilentLobbyConfig> {

    private static AutoSilentLobbyAddon instance;

    public static ReferenceStorage references() {
        return instance.referenceStorageAccessor();
    }

    @Override
    protected Class<? extends AutoSilentLobbyConfig> configurationClass() {
        return AutoSilentLobbyConfig.class;
    }

    @Override
    protected void enable() {
        instance = this;

        this.registerSettingCategory();
        this.registerListener(new ServerNavigationListener(this));
    }
}
