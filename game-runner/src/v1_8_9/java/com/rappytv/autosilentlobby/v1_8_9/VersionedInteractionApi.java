package com.rappytv.autosilentlobby.v1_8_9;

import com.rappytv.autosilentlobby.api.InteractionApi;
import com.rappytv.autosilentlobby.api.MouseButtonType;
import java.util.Objects;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

@Singleton
@Implements(InteractionApi.class)
public class VersionedInteractionApi implements InteractionApi {

    @Override
    public void changeSlot(int slot) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if (slot < 0 || slot > 8) {
            return;
        }
        Minecraft.getMinecraft().thePlayer.inventory.currentItem = slot;
    }

    @Override
    public void click(MouseButtonType type) {
        Objects.requireNonNull(type, "ClickType must not be null");
        if (type == MouseButtonType.NONE) {
            return;
        }
        int key = switch (type) {
            case LEFT -> Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
            case RIGHT -> Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode();
            case MIDDLE -> Minecraft.getMinecraft().gameSettings.keyBindPickBlock.getKeyCode();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        KeyBinding.onTick(key);
    }

    @Override
    public boolean isLookingAtSign() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.thePlayer;

        if (player == null) {
            return false;
        }
        MovingObjectPosition result = player.rayTrace(10, 1.0f);
        if (result == null || result.typeOfHit != MovingObjectType.BLOCK
            || minecraft.theWorld == null) {
            return false;
        }
        Block block = minecraft.theWorld.getBlockState(result.getBlockPos()).getBlock();

        return block.getUnlocalizedName().equals("tile.sign");
    }
}
