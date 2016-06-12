
package steamcraft.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class RecipeAPI
{
	public static HashMap<Fluid, Integer> addedFuels = new HashMap();
	public static Map<ItemStack[], ItemStack> addedRecipeList = new HashMap<ItemStack[], ItemStack>();

	public void addLiquidBoilerFuel(Fluid fluid, int value)
	{
		if (fluid != null)
			addedFuels.put(fluid, value);
	}

	public void addBloomeryRecipe(ItemStack input, ItemStack input2, ItemStack result)
	{
		addedRecipeList.put(new ItemStack[] { input, input2 }, result);
	}
}
