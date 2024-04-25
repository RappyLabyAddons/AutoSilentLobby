package com.rappytv.autosilentlobby;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import java.util.ArrayList;

public class AutoSilentLobbyConfig extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @TextFieldSetting
    private final ConfigProperty<String> servers = new ConfigProperty<>("");

    @Override
    public ConfigProperty<Boolean> enabled() {
        return enabled;
    }
    public String[] servers() {
        return servers.get().split(",");
    }
}
