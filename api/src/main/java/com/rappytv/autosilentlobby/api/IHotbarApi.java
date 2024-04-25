package com.rappytv.autosilentlobby.api;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public interface IHotbarApi {

    void changeSlot(int slot);
    void click(ClickType type);

}
