package modtweaker.mods.integrateddynamics;

import minetweaker.MineTweakerAPI;
import modtweaker.mods.integrateddynamics.handlers.DryingBasinHandler;
import modtweaker.mods.integrateddynamics.handlers.SqueezerHandler;

public class IntegratedDynamics {
    public IntegratedDynamics() {
    	MineTweakerAPI.registerClass(DryingBasinHandler.class);
		MineTweakerAPI.registerClass(SqueezerHandler.class);
    }
}
