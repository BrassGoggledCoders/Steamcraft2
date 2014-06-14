package steamcraft.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;

public class CompatabilityLayer
{
	public static void init()
	{
		registerOreDictionaryEntries();
	}

	private static void registerOreDictionaryEntries()
	{
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ConfigItems.itemIngot, 1, 0));
		OreDictionary.registerOre("ingotAluminium", new ItemStack(ConfigItems.itemIngot, 1, 0));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ConfigItems.itemIngot, 1, 1));
		OreDictionary.registerOre("ingotTin", new ItemStack(ConfigItems.itemIngot, 1, 2));
		OreDictionary.registerOre("ingotZinc", new ItemStack(ConfigItems.itemIngot, 1, 3));
		OreDictionary.registerOre("ingotBrass", new ItemStack(ConfigItems.itemIngot, 1, 4));
		OreDictionary.registerOre("ingotBronze", new ItemStack(ConfigItems.itemIngot, 1, 5));
		OreDictionary.registerOre("ingotCastIron", new ItemStack(ConfigItems.itemIngot, 1, 6));

		OreDictionary.registerOre("plateAluminum", new ItemStack(ConfigItems.itemSheet, 1, 0));
		OreDictionary.registerOre("plateAluminium", new ItemStack(ConfigItems.itemSheet, 1, 0));
		OreDictionary.registerOre("plateCopper", new ItemStack(ConfigItems.itemSheet, 1, 1));
		OreDictionary.registerOre("plateTin", new ItemStack(ConfigItems.itemSheet, 1, 2));
		OreDictionary.registerOre("plateZinc", new ItemStack(ConfigItems.itemSheet, 1, 3));
		OreDictionary.registerOre("plateBrass", new ItemStack(ConfigItems.itemSheet, 1, 4));
		OreDictionary.registerOre("plateBronze", new ItemStack(ConfigItems.itemSheet, 1, 5));
		OreDictionary.registerOre("plateCastIron", new ItemStack(ConfigItems.itemSheet, 1, 6));

		OreDictionary.registerOre("nuggetAluminum", new ItemStack(ConfigItems.itemNugget, 1, 0));
		OreDictionary.registerOre("nuggetAluminium", new ItemStack(ConfigItems.itemNugget, 1, 0));
		OreDictionary.registerOre("nuggetCopper", new ItemStack(ConfigItems.itemNugget, 1, 1));
		OreDictionary.registerOre("nuggetTin", new ItemStack(ConfigItems.itemNugget, 1, 2));
		OreDictionary.registerOre("nuggetZinc", new ItemStack(ConfigItems.itemNugget, 1, 3));
		OreDictionary.registerOre("nuggetBrass", new ItemStack(ConfigItems.itemNugget, 1, 4));
		OreDictionary.registerOre("nuggetBronze", new ItemStack(ConfigItems.itemNugget, 1, 5));
		OreDictionary.registerOre("nuggetCastIron", new ItemStack(ConfigItems.itemNugget, 1, 6));

		OreDictionary.registerOre("oreAluminum", new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
		OreDictionary.registerOre("oreAluminium", new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
		OreDictionary.registerOre("oreCopper", new ItemStack(ConfigBlocks.blockCustomOre, 1, 1));
		OreDictionary.registerOre("oreTin", new ItemStack(ConfigBlocks.blockCustomOre, 1, 2));
		OreDictionary.registerOre("oreZinc", new ItemStack(ConfigBlocks.blockCustomOre, 1, 3));
		OreDictionary.registerOre("oreUranium", new ItemStack(ConfigBlocks.blockCustomOre, 1, 4));
		OreDictionary.registerOre("oreBrimstone", new ItemStack(ConfigBlocks.blockCustomOre, 1, 5));
		OreDictionary.registerOre("orePhosphate", new ItemStack(ConfigBlocks.blockCustomOre, 1, 6));
	}
}
