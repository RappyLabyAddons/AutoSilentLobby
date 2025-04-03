package com.rappytv.autosilentlobby.v1_19_4;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import com.rappytv.autosilentlobby.api.InteractionApi;
import com.rappytv.autosilentlobby.api.MouseButtonType;
import java.util.Objects;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;

@Singleton
@Implements(InteractionApi.class)
public class VersionedInteractionApi implements InteractionApi {

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

    @Override
    public boolean isLookingAtSign() {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;

        if (player == null) {
            return false;
        }
        HitResult result = player.pick(10, 1.0f, false);
        if (result.getType() != Type.BLOCK || minecraft.level == null
            || !(result instanceof BlockHitResult blockHitResult)) {
            return false;
        }
        Block block = minecraft.level.getBlockState(blockHitResult.getBlockPos()).getBlock();

        return BuiltInRegistries.BLOCK.getKey(block).getPath().contains("sign");
    }
}
