package com.rappytv.autosilentlobby.v1_20_2;

import com.rappytv.autosilentlobby.api.ClickType;
import com.rappytv.autosilentlobby.api.IHotbarApi;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;

@Implements(IHotbarApi.class)
public class VersionedHotbarImpl implements IHotbarApi {

    @Override
    public void changeSlot(int slot) {
        if(Minecraft.getInstance().player == null) return;
        if(slot < 0 || slot > 8) return;
        Minecraft.getInstance().player.getInventory().selected = slot;
        System.out.println("Picked slot " + slot);
    }

    @Override
    public void click(ClickType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
