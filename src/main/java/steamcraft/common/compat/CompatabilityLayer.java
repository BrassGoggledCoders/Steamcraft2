/*
 *
 */
package steamcraft.common.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.OreDictHelper;
import cpw.mods.fml.common.event.FMLInterModComms;

// TODO: Auto-generated Javadoc
/**
 * The Class CompatabilityLayer.
 */
public class CompatabilityLayer
{

	/**
	 * Inits the.
	 */
	public static void init()
	{
		registerOreDictionaryEntries();
		sendIMCMessages();
	}

	private static void sendIMCMessages()
	{
		FMLInterModComms.sendRuntimeMessage(LibInfo.ID, "VersionChecker", "addVersionCheck", LibInfo.VERSION_URL);
	}

	/**
	 * Register ore dictionary entries.
	 */
	private static void registerOreDictionaryEntries()
	{
		OreDictHelper.registerOreWithAlts(new String[] { "oreAluminum", "oreAluminium" }, new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
		OreDictionary.registerOre("oreCopper", new ItemStack(ConfigBlocks.blockCustomOre, 1, 1));
		OreDictionary.registerOre("oreTin", new ItemStack(ConfigBlocks.blockCustomOre, 1, 2));
		OreDictionary.registerOre("oreZinc", new ItemStack(ConfigBlocks.blockCustomOre, 1, 3));
		OreDictionary.registerOre("oreBrass", new ItemStack(ConfigBlocks.blockCustomOre, 1, 4));
		OreDictionary.registerOre("oreBronze", new ItemStack(ConfigBlocks.blockCustomOre, 1, 5));
		OreDictionary.registerOre("oreCastIron", new ItemStack(ConfigBlocks.blockCustomOre, 1, 6));

		OreDictHelper.registerOreWithAlts(new String[] { "ingotAluminum", "ingotAluminium" }, new ItemStack(ConfigItems.itemIngot, 1, 0));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ConfigItems.itemIngot, 1, 1));
		OreDictionary.registerOre("ingotTin", new ItemStack(ConfigItems.itemIngot, 1, 2));
		OreDictionary.registerOre("ingotZinc", new ItemStack(ConfigItems.itemIngot, 1, 3));
		OreDictionary.registerOre("ingotBrass", new ItemStack(ConfigItems.itemIngot, 1, 4));
		OreDictionary.registerOre("ingotBronze", new ItemStack(ConfigItems.itemIngot, 1, 5));
		OreDictionary.registerOre("ingotCastIron", new ItemStack(ConfigItems.itemIngot, 1, 6));

		OreDictHelper.registerOreWithAlts(new String[] { "plateAluminum", "plateAluminium" }, new ItemStack(ConfigItems.itemSheet, 1, 0));
		OreDictionary.registerOre("plateCopper", new ItemStack(ConfigItems.itemSheet, 1, 1));
		OreDictionary.registerOre("plateTin", new ItemStack(ConfigItems.itemSheet, 1, 2));
		OreDictionary.registerOre("plateZinc", new ItemStack(ConfigItems.itemSheet, 1, 3));
		OreDictionary.registerOre("plateBrass", new ItemStack(ConfigItems.itemSheet, 1, 4));
		OreDictionary.registerOre("plateBronze", new ItemStack(ConfigItems.itemSheet, 1, 5));
		OreDictionary.registerOre("plateCastIron", new ItemStack(ConfigItems.itemSheet, 1, 6));

		OreDictHelper.registerOreWithAlts(new String[] { "nuggetAluminum", "nuggetAluminium" }, new ItemStack(ConfigItems.itemNugget, 1, 0));
		OreDictionary.registerOre("nuggetCopper", new ItemStack(ConfigItems.itemNugget, 1, 1));
		OreDictionary.registerOre("nuggetTin", new ItemStack(ConfigItems.itemNugget, 1, 2));
		OreDictionary.registerOre("nuggetZinc", new ItemStack(ConfigItems.itemNugget, 1, 3));
		OreDictionary.registerOre("nuggetBrass", new ItemStack(ConfigItems.itemNugget, 1, 4));
		OreDictionary.registerOre("nuggetBronze", new ItemStack(ConfigItems.itemNugget, 1, 5));
		OreDictionary.registerOre("nuggetCastIron", new ItemStack(ConfigItems.itemNugget, 1, 6));

		OreDictHelper.registerOreWithAlts(new String[] { "dustAluminum", "dustAluminium" }, new ItemStack(ConfigItems.itemPowder, 1, 0));
		OreDictionary.registerOre("dustCopper", new ItemStack(ConfigItems.itemPowder, 1, 1));
		OreDictionary.registerOre("dustTin", new ItemStack(ConfigItems.itemPowder, 1, 2));
		OreDictionary.registerOre("dustZinc", new ItemStack(ConfigItems.itemPowder, 1, 3));
		OreDictionary.registerOre("dustBrass", new ItemStack(ConfigItems.itemPowder, 1, 4));
		OreDictionary.registerOre("dustBronze", new ItemStack(ConfigItems.itemPowder, 1, 5));
		OreDictionary.registerOre("dustCastIron", new ItemStack(ConfigItems.itemPowder, 1, 6));

		OreDictHelper.registerOreWithAlts(new String[] { "oreAluminum", "oreAluminium" }, new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
		OreDictionary.registerOre("oreCopper", new ItemStack(ConfigBlocks.blockCustomOre, 1, 1));
		OreDictionary.registerOre("oreTin", new ItemStack(ConfigBlocks.blockCustomOre, 1, 2));
		OreDictionary.registerOre("oreZinc", new ItemStack(ConfigBlocks.blockCustomOre, 1, 3));
		OreDictionary.registerOre("oreUranium", new ItemStack(ConfigBlocks.blockCustomOre, 1, 4));
		OreDictionary.registerOre("oreBrimstone", new ItemStack(ConfigBlocks.blockCustomOre, 1, 5));
		OreDictionary.registerOre("orePhosphate", new ItemStack(ConfigBlocks.blockCustomOre, 1, 6));

		OreDictionary.registerOre("crystalEtherium", new ItemStack(ConfigItems.itemResource, 1, 0));
		OreDictHelper.registerOreWithAlts(new String[] { "powderSulpher", "sulpher", "powderSulfur", "sulfur" }, new ItemStack(
				ConfigItems.itemResource, 1, 1));
		OreDictHelper.registerOreWithAlts(new String[] { "shardObsidian", "slateObsidian" }, new ItemStack(ConfigItems.itemResource, 1, 2));
		OreDictionary.registerOre("powderPhosphorus", new ItemStack(ConfigItems.itemResource, 1, 3));
		OreDictionary.registerOre("ingotUranium", new ItemStack(ConfigItems.itemResource, 1, 4));
		OreDictionary.registerOre("pelletUranium", new ItemStack(ConfigItems.itemResource, 1, 5));
	}
}
