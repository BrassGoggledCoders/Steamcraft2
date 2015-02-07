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

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.init.InitBiomes;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.ModInfo;
import vazkii.botania.api.wiki.WikiHooks;
import boilerplate.common.utils.helpers.IMCHelper;
import boilerplate.common.utils.helpers.OreDictHelper;

/**
 * @author warlordjones
 * 
 */
public class CompatabilityLayer
{

	public static void init()
	{
		registerOreDictionaryEntries();
	}

	public static void postInit()
	{
		registerBiomeTypes();
		sendIMCMessages();
		ForgeHooks.init();

		if(Loader.isModLoaded("Botania"))
		{
			LoggerSteamcraft.info("Botania Detected. Loading Flower Power Module");
			WikiHooks.registerModWiki(ModInfo.ID, new BotaniaWikiProvider());
		}
	}

	private static void sendIMCMessages()
	{
		if(Loader.isModLoaded("VersionChecker"))
			LoggerSteamcraft.info("Poking VersionChecker");
		FMLInterModComms.sendRuntimeMessage(ModInfo.ID, "VersionChecker", "addVersionCheck", ModInfo.VERSION_URL);
		if(Loader.isModLoaded("TConstruct"))
			sendTiConIMC();
		if(Loader.isModLoaded("Thaumcraft"))
			sendThaumcraftIMC();

	}

	private static void sendThaumcraftIMC()
	{
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(InitBlocks.blockTeaPlant, 1, 1));
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster",
				Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 0 + "," + Item.getIdFromItem(InitItems.itemSteamcraftCluster) + "," + 0
						+ ",2.0");
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster",
				Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 2 + "," + Item.getIdFromItem(InitItems.itemSteamcraftCluster) + "," + 1
						+ ",2.0");
	}

	private static void sendTiConIMC()
	{
		LoggerSteamcraft.info("TiCon Detected, adding Etherium Tool Material");
		IMCHelper.addNewToolMaterial(ConfigGeneral.etheriumMaterialID, "Etherium", 2000, 500, 5, 0.1F, 1, EnumChatFormatting.RED.toString(), 16711935);

		IMCHelper.addNewPartBuilderMaterial(ConfigGeneral.etheriumMaterialID, new ItemStack(InitItems.itemResource, 1, 0), new ItemStack(
				InitItems.itemResource, 1, 6), 2);
		int ingotLiquidValue = 144;
		int nuggetLiquidValue = ingotLiquidValue / 9;
		int blockLiquidValue = ingotLiquidValue * 9;
		// Aluminum, Copper, Tin
		for(int i = 0; i < 3; i++)
		{
			BlockFluidClassic block_fluid = (BlockFluidClassic) GameRegistry.findBlock("TConstruct", "fluid.molten." + LibInfo.metals[i].toLowerCase());
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					blockLiquidValue), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue), 300);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					nuggetLiquidValue), 150);
		}
		// Skip Zinc. Brass, Bronze, Steel.
		for(int i = 4; i < 7; i++)
		{
			String metalname = LibInfo.metals[i].toLowerCase().replace("brass", "alubrass");
			BlockFluidClassic block_fluid = (BlockFluidClassic) GameRegistry.findBlock("TConstruct", "fluid.molten." + metalname);
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					blockLiquidValue),
					600);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue), 300);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					nuggetLiquidValue),
					150);
		}
		// Zinc
		IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, 3), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid,
				blockLiquidValue),
				600);
		IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, 3), InitBlocks.blockMetal,
				new FluidStack(InitBlocks.moltenZincFluid, ingotLiquidValue), 300);
		IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, 3), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid,
				nuggetLiquidValue),
				150);
	}

	private static void registerOreDictionaryEntries()
	{
		LoggerSteamcraft.info("Registering Thingies in OreDictionary");
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
		OreDictionary.registerOre("plateSteel", new ItemStack(InitItems.itemSheet, 1, 6));
		OreDictionary.registerOre("plateCastIron", new ItemStack(InitItems.itemSheet, 1, 7));
		OreDictionary.registerOre("plateIron", new ItemStack(InitItems.itemVanillaSheet, 1, 0));
		OreDictionary.registerOre("plateGold", new ItemStack(InitItems.itemVanillaSheet, 1, 1));

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
		OreDictionary.registerOre("dustIron", new ItemStack(InitItems.itemVanillaPowder, 1, 0));
		OreDictionary.registerOre("dustGold", new ItemStack(InitItems.itemVanillaPowder, 1));

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
	}

	private static void registerBiomeTypes()
	{
		LoggerSteamcraft.info("Registering Biome Dictionary entries");
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepths, BiomeDictionary.Type.HILLS);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsF, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsM, BiomeDictionary.Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsI, BiomeDictionary.Type.WASTELAND);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsJ, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSC, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSCH, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSW, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsTF, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE);
	}

	public static void initCompatItems()
	{
		if(Loader.isModLoaded("Thaumcraft"))
		{
			LoggerSteamcraft.info("Thaumcraft Detected. Loading Wizarding Module");

			GameRegistry.registerItem(InitItems.itemSteamcraftCluster, "ItemSteamcraftCluster");

			GameRegistry.registerItem(InitItems.itemThaumicMonocle, "ItemThaumicMonocle");
			Item thaumometer = GameRegistry.findItem("Thaumcraft", "ItemThaumometer");
			GameRegistry.addRecipe(new ShapedOreRecipe(InitItems.itemThaumicMonocle, new Object[] { " I ", "ITI", " I ", 'I', "ingotBrass", 'T',
					thaumometer }));
		}
		if(Loader.isModLoaded("TConstruct"))
		{
			GameRegistry.registerBlock(InitBlocks.blockMoltenZinc, "blockMoltenZinc");
		}
	}
}
