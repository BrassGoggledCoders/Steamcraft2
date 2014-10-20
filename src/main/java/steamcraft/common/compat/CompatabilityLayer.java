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
import net.minecraftforge.oredict.OreDictionary;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitItems;
import steamcraft.common.items.ItemParts;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.helpers.OreDictHelper;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * @author warlordjones
 *
 */
public class CompatabilityLayer
{
	public static void init()
	{
		registerOreDictionaryEntries();
		sendIMCMessages();
		ForgeHooks.init();
		// if(Loader.isModLoaded("MineFactoryReloaded"))
		// MFRCompat.init();
	}

	private static void sendIMCMessages()
	{
		FMLInterModComms.sendRuntimeMessage(LibInfo.ID, "VersionChecker", "addVersionCheck", LibInfo.VERSION_URL);
	}

	private static void registerOreDictionaryEntries()
	{

		OreDictHelper.registerOreWithAlts(new String[] { "ingotAluminum", "ingotAluminium" }, new ItemStack(InitItems.itemIngot, 1, 0));
		OreDictionary.registerOre("ingotCopper", new ItemStack(InitItems.itemIngot, 1, 1));
		OreDictionary.registerOre("ingotTin", new ItemStack(InitItems.itemIngot, 1, 2));
		OreDictionary.registerOre("ingotZinc", new ItemStack(InitItems.itemIngot, 1, 3));
		OreDictionary.registerOre("ingotBrass", new ItemStack(InitItems.itemIngot, 1, 4));
		OreDictionary.registerOre("ingotBronze", new ItemStack(InitItems.itemIngot, 1, 5));
		OreDictionary.registerOre("ingotSteel", new ItemStack(InitItems.itemIngot, 1, 6));
		OreDictionary.registerOre("ingotCastIron", new ItemStack(InitItems.itemIngot, 1, 7));

		OreDictHelper.registerOreWithAlts(new String[] { "plateAluminum", "plateAluminium" }, new ItemStack(InitItems.itemSheet, 1, 0));
		OreDictionary.registerOre("plateCopper", new ItemStack(InitItems.itemSheet, 1, 1));
		OreDictionary.registerOre("plateTin", new ItemStack(InitItems.itemSheet, 1, 2));
		OreDictionary.registerOre("plateZinc", new ItemStack(InitItems.itemSheet, 1, 3));
		OreDictionary.registerOre("plateBrass", new ItemStack(InitItems.itemSheet, 1, 4));
		OreDictionary.registerOre("plateBronze", new ItemStack(InitItems.itemSheet, 1, 5));
		OreDictionary.registerOre("plateSteel", new ItemStack(InitItems.itemIngot, 1, 6));
		OreDictionary.registerOre("plateCastIron", new ItemStack(InitItems.itemSheet, 1, 7));

		OreDictHelper.registerOreWithAlts(new String[] { "nuggetAluminum", "nuggetAluminium" }, new ItemStack(InitItems.itemNugget, 1, 0));
		OreDictionary.registerOre("nuggetCopper", new ItemStack(InitItems.itemNugget, 1, 1));
		OreDictionary.registerOre("nuggetTin", new ItemStack(InitItems.itemNugget, 1, 2));
		OreDictionary.registerOre("nuggetZinc", new ItemStack(InitItems.itemNugget, 1, 3));
		OreDictionary.registerOre("nuggetBrass", new ItemStack(InitItems.itemNugget, 1, 4));
		OreDictionary.registerOre("nuggetBronze", new ItemStack(InitItems.itemNugget, 1, 5));
		OreDictionary.registerOre("nuggetSteel", new ItemStack(InitItems.itemIngot, 1, 6));
		OreDictionary.registerOre("nuggetCastIron", new ItemStack(InitItems.itemNugget, 1, 7));

		OreDictHelper.registerOreWithAlts(new String[] { "dustAluminum", "dustAluminium" }, new ItemStack(InitItems.itemPowder, 1, 0));
		OreDictionary.registerOre("dustCopper", new ItemStack(InitItems.itemPowder, 1, 1));
		OreDictionary.registerOre("dustTin", new ItemStack(InitItems.itemPowder, 1, 2));
		OreDictionary.registerOre("dustZinc", new ItemStack(InitItems.itemPowder, 1, 3));
		OreDictionary.registerOre("dustBrass", new ItemStack(InitItems.itemPowder, 1, 4));
		OreDictionary.registerOre("dustBronze", new ItemStack(InitItems.itemPowder, 1, 5));
		OreDictionary.registerOre("dustSteel", new ItemStack(InitItems.itemIngot, 1, 6));
		OreDictionary.registerOre("dustCastIron", new ItemStack(InitItems.itemPowder, 1, 7));

		OreDictHelper.registerOreWithAlts(new String[] { "oreAluminum", "oreAluminium" }, new ItemStack(InitBlocks.blockCustomOre, 1, 0));
		OreDictionary.registerOre("oreCopper", new ItemStack(InitBlocks.blockCustomOre, 1, 1));
		OreDictionary.registerOre("oreTin", new ItemStack(InitBlocks.blockCustomOre, 1, 2));
		OreDictionary.registerOre("oreZinc", new ItemStack(InitBlocks.blockCustomOre, 1, 3));
		OreDictionary.registerOre("oreUranium", new ItemStack(InitBlocks.blockCustomOre, 1, 4));
		OreDictionary.registerOre("oreBrimstone", new ItemStack(InitBlocks.blockCustomOre, 1, 5));
		OreDictionary.registerOre("orePhosphate", new ItemStack(InitBlocks.blockCustomOre, 1, 6));

		OreDictionary.registerOre("crystalEtherium", new ItemStack(InitItems.itemResource, 1, 0));
		OreDictHelper.registerOreWithAlts(new String[] { "powderSulpher", "sulpher", "powderSulfur", "sulfur" }, new ItemStack(
				InitItems.itemResource, 1, 1));
		OreDictHelper.registerOreWithAlts(new String[] { "shardObsidian", "slateObsidian" }, new ItemStack(InitItems.itemResource, 1, 2));
		OreDictionary.registerOre("powderPhosphorus", new ItemStack(InitItems.itemResource, 1, 3));
		OreDictionary.registerOre("ingotUranium", new ItemStack(InitItems.itemResource, 1, 4));
		OreDictionary.registerOre("pelletUranium", new ItemStack(InitItems.itemResource, 1, 5));
		OreDictHelper.registerOreWithAlts(new String[] { "itemRubber", "barRubber", "rawRubber" }, new ItemStack(InitItems.itemSlimeRubber));

		OreDictionary.registerOre("partCastIronRod", new ItemStack(InitItems.itemMachinePart, 1, 0));
		OreDictionary.registerOre("partClockworkMechanism", new ItemStack(InitItems.itemMachinePart, 1, 1));
		OreDictionary.registerOre("partGrating", new ItemStack(InitItems.itemMachinePart, 1, 2));
		OreDictionary.registerOre("partMagnet", new ItemStack(InitItems.itemMachinePart, 1, 3));
		OreDictionary.registerOre("partGenerator", new ItemStack(InitItems.itemMachinePart, 1, 4));
		OreDictionary.registerOre("partFan", new ItemStack(InitItems.itemMachinePart, 1, 5));
		OreDictionary.registerOre("partWireCoil", new ItemStack(InitItems.itemMachinePart, 1, 6));

		String[] partType = new String[]{"Gear", "Sprocket", "Spring", "Thread",
		"Nut", "Bolt", "Washer", "Bearing", "Screw", "Nail"};

		for(int i = 0; i< 10; i++)
		{
			OreDictionary.registerOre("partCopper" + partType[i], new ItemStack(InitItems.itemCopperParts, 1, i));
			OreDictionary.registerOre("partIron" + partType[i], new ItemStack(InitItems.itemIronParts, 1, i));
			OreDictionary.registerOre("partBrass" + partType[i], new ItemStack(InitItems.itemBrassParts, 1, i));
			OreDictionary.registerOre("partSteel" + partType[i], new ItemStack(InitItems.itemSteelParts, 1, i));
		}

	}
}
