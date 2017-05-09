package modtweaker.mods.integrateddynamics.handlers;

import com.blamejared.mtlib.helpers.InputHelper;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import modtweaker.mods.cyclopscore.handlers.CyclopsRecipeRegistryHandler;
import org.cyclops.cyclopscore.recipe.custom.Recipe;
import org.cyclops.cyclopscore.recipe.custom.component.DummyPropertiesComponent;
import org.cyclops.cyclopscore.recipe.custom.component.ItemAndFluidStackRecipeComponent;
import org.cyclops.cyclopscore.recipe.custom.component.ItemStackRecipeComponent;
import org.cyclops.integrateddynamics.block.BlockSqueezer;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.integrateddynamics.Squeezer")
public class SqueezerHandler extends CyclopsRecipeRegistryHandler<BlockSqueezer, ItemStackRecipeComponent, ItemAndFluidStackRecipeComponent, DummyPropertiesComponent> {

    private static final SqueezerHandler INSTANCE = new SqueezerHandler();

    @Override
    protected BlockSqueezer getMachine() {
        return BlockSqueezer.getInstance();
    }

    @Override
    protected String getRegistryName() {
        return "Squeezer";
    }

    @ZenMethod
    public static void add(IItemStack inputStack,
                           @Optional IItemStack outputStack, @Optional ILiquidStack outputFluid) {
        INSTANCE.add(new Recipe<>(
                new ItemStackRecipeComponent(InputHelper.toStack(inputStack)),
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid)),
                new DummyPropertiesComponent()));
    }

    @ZenMethod
    public static void remove(IItemStack inputStack,
                              @Optional IItemStack outputStack, @Optional ILiquidStack outputFluid) {
        INSTANCE.remove(new Recipe<>(
                new ItemStackRecipeComponent(InputHelper.toStack(inputStack)),
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid)),
                new DummyPropertiesComponent()));
    }

    @ZenMethod
    public static void remove(@Optional IItemStack outputStack, @Optional ILiquidStack outputFluid) {
        INSTANCE.remove(
                new ItemAndFluidStackRecipeComponent(InputHelper.toStack(outputStack), InputHelper.toFluid(outputFluid))
        );
    }
}
