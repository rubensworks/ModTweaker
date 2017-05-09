package modtweaker.mods.integrateddynamics.handlers;

import com.blamejared.mtlib.helpers.InputHelper;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import modtweaker.mods.cyclopscore.handlers.CyclopsRecipeRegistryHandler;
import org.cyclops.cyclopscore.recipe.custom.Recipe;
import org.cyclops.cyclopscore.recipe.custom.component.DurationRecipeProperties;
import org.cyclops.cyclopscore.recipe.custom.component.ItemAndFluidStackRecipeComponent;
import org.cyclops.integrateddynamics.block.BlockDryingBasin;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.integrateddynamics.DryingBasin")
public class DryingBasinHandler extends CyclopsRecipeRegistryHandler<BlockDryingBasin, ItemAndFluidStackRecipeComponent, ItemAndFluidStackRecipeComponent, DurationRecipeProperties> {

    private static final DryingBasinHandler INSTANCE = new DryingBasinHandler();

    @Override
    protected BlockDryingBasin getMachine() {
        return BlockDryingBasin.getInstance();
    }

    @Override
    protected String getRegistryName() {
        return "DryingBasin";
    }

    @ZenMethod
    public static void add(@Optional IItemStack inputStack, @Optional ILiquidStack inputFluid,
                           @Optional IItemStack outputStack, @Optional ILiquidStack outputFluid, int duration) {
        INSTANCE.add(new Recipe<>(
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(inputStack), InputHelper.toFluid(inputFluid)),
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid)),
                new DurationRecipeProperties(duration)));
    }

    @ZenMethod
    public static void remove(@Optional IItemStack inputStack, @Optional ILiquidStack inputFluid,
                              @Optional IItemStack outputStack, @Optional ILiquidStack outputFluid, int duration) {
        INSTANCE.remove(new Recipe<>(
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(inputStack), InputHelper.toFluid(inputFluid)),
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid)),
                new DurationRecipeProperties(duration)));
    }

    @ZenMethod
    public static void remove(@Optional IItemStack outputStack, @Optional ILiquidStack outputFluid) {
        INSTANCE.remove(
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid))
        );
    }
}
