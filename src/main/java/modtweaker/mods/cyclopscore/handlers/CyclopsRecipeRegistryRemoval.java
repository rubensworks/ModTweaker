package modtweaker.mods.cyclopscore.handlers;

import com.blamejared.mtlib.helpers.LogHelper;
import com.blamejared.mtlib.utils.BaseListRemoval;
import org.cyclops.cyclopscore.recipe.custom.api.*;

/**
 * Generic CyclopsCore {@link IRecipeRegistry} recipe removal class.
 * @author rubensworks
 */
public class CyclopsRecipeRegistryRemoval
        <M extends IMachine<M, I, O, P>, I extends IRecipeInput, O extends IRecipeOutput, P extends IRecipeProperties>
        extends BaseListRemoval<IRecipe<I, O, P>> {

    private final M machine;

    protected CyclopsRecipeRegistryRemoval(String machineName, M machine, IRecipe<I, O, P> recipe) {
        super(machineName, machine.getRecipeRegistry().allRecipes());
        this.machine = machine;
        this.recipes.add(recipe);
    }

    protected CyclopsRecipeRegistryRemoval(String machineName, M machine, O output) {
        super(machineName, machine.getRecipeRegistry().allRecipes());
        this.machine = machine;
        this.recipes.addAll(machine.getRecipeRegistry().findRecipesByOutput(output));
    }

    @Override
    public void apply() {
        if (recipes.isEmpty()) {
            return;
        }

        for (IRecipe<I, O, P> recipe : this.recipes) {
            if (recipe != null) {
                IRecipe<I, O, P> removed = machine.getRecipeRegistry().unregisterRecipe(recipe);
                if (removed != null) {
                    successful.add(removed);
                } else {
                    LogHelper.logError(String.format("Error removing %s Recipe for %s", name, getRecipeInfo(recipe)));
                }
            } else {
                LogHelper.logError(String.format("Error removing %s Recipe: null object", name));
            }
        }
    }

    @Override
    protected String getRecipeInfo(IRecipe<I, O, P> recipe) {
        return CyclopsRecipeRegistryAddition.getRecipeInfo(this.name, recipe);
    }

}
