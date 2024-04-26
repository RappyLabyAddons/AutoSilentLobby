package com.rappytv.autosilentlobby;

import com.rappytv.autosilentlobby.api.ClickType;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import java.util.ArrayList;
import java.util.Arrays;

public class AutoSilentLobbyConfig extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @SettingSection("navigation")
    @TextFieldSetting
    private final ConfigProperty<String> servers = new ConfigProperty<>("");

    @SwitchSetting
    private final ConfigProperty<Boolean> join = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> subservers = new ConfigProperty<>(true);

    @SettingSection("hotbar")
    @SliderSetting(min = 1, max = 9)
    private final ConfigProperty<Integer> slot = new ConfigProperty<>(1);

    @DropdownSetting
    private final ConfigProperty<ClickType> clickType = new ConfigProperty<>(ClickType.NONE);

    @Override
    public ConfigProperty<Boolean> enabled() {
        return enabled;
    }
    public ArrayList<String> servers() {
        return new ArrayList<>(Arrays.asList(servers.get().split(",")));
    }
    public boolean onJoin() {
        return join.get();
    }
    public boolean onSubserverSwitch() {
        return subservers.get();
    }
    public int slot() {
        return slot.get();
    }
    public ClickType clickType() {
        return clickType.get();
    }
}
