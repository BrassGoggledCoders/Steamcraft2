
package steamcraft.common.compat;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;

import net.minecraftforge.common.FishingHooks;

import steamcraft.common.init.InitItems;

public class ForgeHooks
{
	public static void init()
	{
		addCustomFishResults();
	}

	private static void addCustomFishResults()
	{
		for (int i = 0; i < 10; i++)
		{
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemSteelParts, 1, i), 25));
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemIronParts, 1, i), 25));
		}

	}

}
