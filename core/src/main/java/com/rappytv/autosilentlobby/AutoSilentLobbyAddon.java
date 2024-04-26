package com.rappytv.autosilentlobby;

import com.rappytv.autosilentlobby.api.IHotbarApi;
import com.rappytv.autosilentlobby.core.generated.DefaultReferenceStorage;
import com.rappytv.autosilentlobby.listener.ServerNavigationListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoSilentLobbyAddon extends LabyAddon<AutoSilentLobbyConfig> {

    private IHotbarApi api;

    @Override
    protected void enable() {
        registerSettingCategory();
        api = ((DefaultReferenceStorage) referenceStorageAccessor()).iHotbarApi();
        registerListener(new ServerNavigationListener(this));
    }

    @Override
    protected Class<? extends AutoSilentLobbyConfig> configurationClass() {
        return AutoSilentLobbyConfig.class;
    }

    public IHotbarApi getApi() {
        return api;
    }
}
