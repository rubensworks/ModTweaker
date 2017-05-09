package modtweaker.mods.cyclopscore.handlers;

import minetweaker.MineTweakerAPI;
import org.cyclops.cyclopscore.recipe.custom.api.*;

/**
 * Main handler for the Cyclops {@link org.cyclops.cyclopscore.recipe.custom.api.IRecipeRegistry}.
 * @author rubensworks
 */
public abstract class CyclopsRecipeRegistryHandler
        <M extends IMachine<M, I, O, P>, I extends IRecipeInput, O extends IRecipeOutput, P extends IRecipeProperties> {

    protected abstract M getMachine();
    protected abstract String getRegistryName();

    public void add(IRecipe<I, O, P> recipe) {
        MineTweakerAPI.apply(new CyclopsRecipeRegistryAddition<>(getRegistryName(), getMachine(), recipe));
    }

    public void remove(IRecipe<I, O, P> recipe) {
        MineTweakerAPI.apply(new CyclopsRecipeRegistryRemoval<>(getRegistryName(), getMachine(), recipe));
    }

    public void remove(O output) {
        MineTweakerAPI.apply(new CyclopsRecipeRegistryRemoval<>(getRegistryName(), getMachine(), output));
    }

}
