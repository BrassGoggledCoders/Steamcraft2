package steamcraft.common;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

import net.minecraftforge.common.ChestGenHooks;

public class InitMisc
{
	public static void initDungeonLoot()
	{
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(InitItems.itemLoreBook), 1, 1, 30));
	}
}
