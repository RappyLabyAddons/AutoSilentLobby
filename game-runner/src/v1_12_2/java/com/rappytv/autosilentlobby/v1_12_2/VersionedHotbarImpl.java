package com.rappytv.autosilentlobby.v1_12_2;

import com.rappytv.autosilentlobby.api.ClickType;
import com.rappytv.autosilentlobby.api.IHotbarApi;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.Objects;

@Implements(IHotbarApi.class)
public class VersionedHotbarImpl implements IHotbarApi {

    @Override
    public void changeSlot(int slot) {
        if(Minecraft.getMinecraft().player == null) return;
        if(slot < 0 || slot > 8) return;
        Minecraft.getMinecraft().player.inventory.currentItem = slot;
    }

    @Override
    public void click(ClickType type) {
        Objects.requireNonNull(type, "ClickType must not be null");
        if(type == ClickType.NONE) return;
        int key = switch (type) {
            case LEFT -> Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
            case RIGHT -> Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode();
            case MIDDLE -> Minecraft.getMinecraft().gameSettings.keyBindPickBlock.getKeyCode();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        KeyBinding.onTick(key);
    }
}
