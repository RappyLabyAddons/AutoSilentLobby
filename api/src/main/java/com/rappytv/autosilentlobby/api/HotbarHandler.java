package com.rappytv.autosilentlobby.api;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public interface HotbarHandler {

    void changeSlot(int slot);

    void click(MouseButtonType type);

}
