package com.rappytv.autosilentlobby;

import com.rappytv.autosilentlobby.api.ClickType;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoSilentLobbyConfig extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @TextFieldSetting
    private final ConfigProperty<String> servers = new ConfigProperty<>("");

    @SliderSetting(min = 1, max = 9)
    private final ConfigProperty<Integer> slot = new ConfigProperty<>(1);

    @DropdownSetting
    private final ConfigProperty<ClickType> clickType = new ConfigProperty<>(ClickType.LEFT);

    @Override
    public ConfigProperty<Boolean> enabled() {
        return enabled;
    }
    public ArrayList<String> servers() {
        return new ArrayList<>(Arrays.asList(servers.get().split(",")));
    }
    public int slot() {
        return slot.get();
    }
    public ClickType clickType() {
        return clickType.get();
    }
}
