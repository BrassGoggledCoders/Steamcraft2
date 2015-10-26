package steamcraft.common.init;

import net.minecraft.item.ItemStack;
import tconstruct.TConstruct;
import tconstruct.library.crafting.FluidType;
import tconstruct.library.crafting.Smeltery;

public class InitTinkersSupport {

	public static void init() {
		initCastingRecipes();
		initSmeltingRecipes();
	}

	public static void initCastingRecipes() {

	}

	public static void initSmeltingRecipes() {
		FluidType.registerFluidType("Zinc", InitBlocks.blockMetal, 3, 400, InitBlocks.moltenZincFluid, true);
		FluidType zinc = FluidType.getFluidType("Zinc");
		Smeltery.addMelting(zinc, new ItemStack(InitItems.itemIngot, 1, 3), 0, TConstruct.ingotLiquidValue);
	}
}
