package com.rappytv.autosilentlobby;

import com.rappytv.autosilentlobby.api.MouseButtonType;
import java.util.ArrayList;
import java.util.Arrays;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.configuration.settings.type.SettingElement;
import net.labymod.api.util.MethodOrder;

public class AutoSilentLobbyConfig extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @SettingSection("navigation")
    @SwitchSetting
    private final ConfigProperty<Boolean> singleplayer = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> join = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> subservers = new ConfigProperty<>(true);

    @TextFieldSetting
    private final ConfigProperty<String> servers = new ConfigProperty<>("");

    @SettingSection("hotbar")
    @SliderSetting(min = 1, max = 9)
    private final ConfigProperty<Integer> slot = new ConfigProperty<>(1);

    @SliderSetting(min = 1, max = 5)
    private final ConfigProperty<Integer> clickAmount = new ConfigProperty<>(1);

    @DropdownSetting
    private final ConfigProperty<MouseButtonType> clickType = new ConfigProperty<>(
        MouseButtonType.NONE);

    @SettingSection("presets")
    @MethodOrder(after = "clickType")
    @ButtonSetting
    public void gomme(SettingElement element) {
        this.singleplayer.set(false);
        this.join.set(true);
        this.subservers.set(false);
        this.servers.set("gommehd.net");
        this.slot.set(3);
        this.clickAmount.set(1);
        this.clickType.set(MouseButtonType.RIGHT);
    }

    @Override
    public ConfigProperty<Boolean> enabled() {
        return this.enabled;
    }
    public boolean onSinglePlayerWorld() {
        return this.singleplayer.get();
    }
    public boolean onJoin() {
        return this.join.get();
    }
    public boolean onSubserverSwitch() {
        return this.subservers.get();
    }
    public ArrayList<String> servers() {
        return new ArrayList<>(Arrays.asList(this.servers.get().split(",")));
    }
    public int slot() {
        return this.slot.get();
    }
    public int clickAmount() {
        return this.clickAmount.get();
    }

    public MouseButtonType clickType() {
        return this.clickType.get();
    }
}
