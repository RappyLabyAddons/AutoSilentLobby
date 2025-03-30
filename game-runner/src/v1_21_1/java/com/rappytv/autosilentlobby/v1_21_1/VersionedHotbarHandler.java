package com.rappytv.autosilentlobby.v1_21_1;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import com.rappytv.autosilentlobby.api.HotbarHandler;
import com.rappytv.autosilentlobby.api.MouseButtonType;
import java.util.Objects;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

@Singleton
@Implements(HotbarHandler.class)
public class VersionedHotbarHandler implements HotbarHandler {

    @Override
    public void changeSlot(int slot) {
        if (Minecraft.getInstance().player == null) {
            return;
        }
        if (slot < 0 || slot > 8) {
            return;
        }
        Minecraft.getInstance().player.getInventory().selected = slot;
    }

    @Override
    public void click(MouseButtonType type) {
        Objects.requireNonNull(type, "ClickType must not be null");
        if (type == MouseButtonType.NONE) {
            return;
        }
        Key key = switch (type) {
            case LEFT -> InputConstants.getKey("key.mouse.left");
            case RIGHT -> InputConstants.getKey("key.mouse.right");
            case MIDDLE -> InputConstants.getKey("key.mouse.middle");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        KeyMapping.click(key);
    }
}
