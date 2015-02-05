/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
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
		for(int i = 0; i < 10; i++)
		{
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemBrassParts, 1, i), 25));
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemCopperParts, 1, i), 25));
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemSteelParts, 1, i), 25));
			FishingHooks.addJunk(new WeightedRandomFishable(new ItemStack(InitItems.itemIronParts, 1, i), 25));
		}

	}

}
