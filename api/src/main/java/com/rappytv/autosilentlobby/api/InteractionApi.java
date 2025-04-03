package com.rappytv.autosilentlobby.api;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public interface InteractionApi {

    void changeSlot(int slot);

    void click(MouseButtonType type);

    boolean isLookingAtSign();

}
