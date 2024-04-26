package com.rappytv.autosilentlobby.v1_20_2;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import com.rappytv.autosilentlobby.api.ClickType;
import com.rappytv.autosilentlobby.api.IHotbarApi;
import net.labymod.api.models.Implements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import java.util.Objects;

@Implements(IHotbarApi.class)
public class VersionedHotbarImpl implements IHotbarApi {

    @Override
    public void changeSlot(int slot) {
        if(Minecraft.getInstance().player == null) return;
        if(slot < 0 || slot > 8) return;
        Minecraft.getInstance().player.getInventory().selected = slot;
    }

    @Override
    public void click(ClickType type) {
        Objects.requireNonNull(type, "ClickType must not be null");
        if(type == ClickType.NONE) return;
        Key key = switch (type) {
            case LEFT -> InputConstants.getKey("key.mouse.left");
            case RIGHT -> InputConstants.getKey("key.mouse.right");
            case MIDDLE -> InputConstants.getKey("key.mouse.middle");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        KeyMapping.click(key);
    }
}
