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

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import steamcraft.common.InitBiomes;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitItems;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.items.compat.ItemSteamcraftCluster;
import steamcraft.common.lib.LibInfo;
import vazkii.botania.api.wiki.WikiHooks;
import boilerplate.common.utils.helpers.IMCHelper;
import boilerplate.common.utils.helpers.OreDictHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author warlordjones
 * 
 */
public class CompatabilityLayer
{
	public static Item itemSteamcraftCluster;

	public static void postInit()
	{
		registerOreDictionaryEntries();
		sendIMCMessages();
		ForgeHooks.init();
		// if(Loader.isModLoaded("MineFactoryReloaded"))
		// MFRCompat.init();

		if(Loader.isModLoaded("Botania"))
		{
			WikiHooks.registerModWiki(LibInfo.ID, new BotaniaWikiProvider("sc2.wikia.com/wiki/", "%20"));
		}
		if(Loader.isModLoaded("Thaumcraft"))
		{
			GameRegistry.registerItem(InitItems.itemThaumicMonocle, "ItemThaumicMonocle");
			Item thaumometer = GameRegistry.findItem("Thaumcraft", "ItemThaumometer");
			GameRegistry.addRecipe(new ShapedOreRecipe(InitItems.itemThaumicMonocle, new Object[] { " I ", "ITI", " I ", 'I', "ingotBrass", 'T',
					thaumometer }));
		}
	}

	private static void sendIMCMessages()
	{
		FMLInterModComms.sendRuntimeMessage(LibInfo.ID, "VersionChecker", "addVersionCheck", LibInfo.VERSION_URL);
		sendTiConIMC();
	}

	private static void sendTiConIMC()
	{
		// TODO Zinc Tools & Melting. Metal Block melting, without TiCon dep

		IMCHelper.addNewToolMaterial(ConfigGeneral.etheriumMaterialID, "Etherium", 2000, 500, 5, 0.1F, 1, EnumChatFormatting.RED.toString(), 16711935);

		IMCHelper.addNewPartBuilderMaterial(ConfigGeneral.etheriumMaterialID, new ItemStack(InitItems.itemResource, 1, 0), new ItemStack(
				InitItems.itemResource, 1, 6), 2);
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

		String[] partType = new String[] { "Gear", "Sprocket", "Spring", "Thread",
				"Nut", "Bolt", "Washer", "Bearing", "Screw", "Nail" };

		for(int i = 0; i < 10; i++)
		{
			OreDictHelper.registerOreWithAlts(new String[] { "partCopper" + partType[i], partType[i].toLowerCase() + "Copper" }, new ItemStack(
					InitItems.itemCopperParts, 1, i));
			OreDictHelper.registerOreWithAlts(new String[] { "partIron" + partType[i], partType[i].toLowerCase() + "Copper" }, new ItemStack(
					InitItems.itemIronParts, 1, i));
			OreDictHelper.registerOreWithAlts(new String[] { "partBrass" + partType[i], partType[i].toLowerCase() + "Copper" }, new ItemStack(
					InitItems.itemBrassParts, 1, i));
			OreDictHelper.registerOreWithAlts(new String[] { "partSteel" + partType[i], partType[i].toLowerCase() + "Copper" }, new ItemStack(
					InitItems.itemSteelParts, 1, i));
		}
		// Part Utils
		for(int i = 0; i < 10; i++)
		{
			OreDictionary.registerOre("partTierOne", new ItemStack(InitItems.itemCopperParts, 1, i));
			OreDictionary.registerOre("partTierOne", new ItemStack(InitItems.itemIronParts, 1, i));
			OreDictionary.registerOre("partTierTwo", new ItemStack(InitItems.itemBrassParts, 1, i));
			OreDictionary.registerOre("partTierTwo", new ItemStack(InitItems.itemSteelParts, 1, i));
		}
		registerBiomeTypes();
	}

	private static void registerBiomeTypes()
	{
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepths, BiomeDictionary.Type.HILLS);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsF, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsM, BiomeDictionary.Type.HILLS);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST);
	}

	public static void init()
	{
		// Thaumcraft
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(InitBlocks.blockTeaPlant, 1, 1));
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster",
				Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 0 + "," + Item.getIdFromItem(CompatabilityLayer.itemSteamcraftCluster) + "," + 0
						+ ",2.0");
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster",
				Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 2 + "," + Item.getIdFromItem(CompatabilityLayer.itemSteamcraftCluster) + "," + 1
						+ ",2.0");
	}

	public static void initCompatItems()
	{
		itemSteamcraftCluster = new ItemSteamcraftCluster().setUnlocalizedName("itemSteamcraftCluster");

		if(Loader.isModLoaded("Thaumcraft"))
		{
			GameRegistry.registerItem(itemSteamcraftCluster, "ItemSteamcraftCluster");
		}
	}
}
