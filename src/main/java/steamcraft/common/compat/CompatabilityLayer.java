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
	public static int ingotLiquidValue = 144;
	public static int nuggetLiquidValue = ingotLiquidValue / 9;
	public static int blockLiquidValue = ingotLiquidValue * 9;

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
		{
			LoggerSteamcraft.info("Poking VersionChecker");
			FMLInterModComms.sendRuntimeMessage(ModInfo.ID, "VersionChecker", "addVersionCheck", ModInfo.VERSION_URL);
		}
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

		IMCHelper.addNewPartBuilderMaterial(ConfigGeneral.etheriumMaterialID, new ItemStack(InitItems.itemResource), new ItemStack(
				InitItems.itemResource, 1, 6), 2);
		// Aluminum, Copper, Tin
		for(int i = 0; i < 3; i++)
		{
			BlockFluidClassic block_fluid = (BlockFluidClassic) GameRegistry.findBlock("TConstruct", "fluid.molten." + LibInfo.metals[i].toLowerCase());
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					blockLiquidValue), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockCustomOre, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					ingotLiquidValue * 2), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue), 300);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, i), InitBlocks.blockMetal, new FluidStack(block_fluid.getFluid(),
					nuggetLiquidValue), 150);
		}
		// Skip Zinc and Brass. Bronze, Steel.
		for(int i = 5; i < 7; i++)
		{
			String metalname = LibInfo.metals[i].toLowerCase();
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
		/*
		 * Zinc IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, 3), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid,
		 * blockLiquidValue), 600); IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockCustomOre, 1, 3), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenZincFluid, ingotLiquidValue * 2), 600); IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, 3),
		 * InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid, ingotLiquidValue), 300); IMCHelper.addNewSmeltable(new
		 * ItemStack(InitItems.itemNugget, 1, 3), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid, nuggetLiquidValue), 150); // Brass
		 * IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, 4), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenBrassFluid,
		 * blockLiquidValue), 600); IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, 4), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenBrassFluid, ingotLiquidValue), 300); IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, 4),
		 * InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenBrassFluid, nuggetLiquidValue), 150);
		 */
		IMCHelper.addNewFluxBattery(InitItems.itemElectricJarSmall);
		IMCHelper.addNewFluxBattery(InitItems.itemElectricJarMedium);
		IMCHelper.addNewFluxBattery(InitItems.itemElectricJarLarge);
		IMCHelper.addNewFluxBattery(InitItems.itemElectricJarHuge);
	}

	private static void registerOreDictionaryEntries()
	{
		LoggerSteamcraft.info("Registering Thingies in OreDictionary");
		OreDictHelper.registerOreWithAlts(InitItems.itemIngot, "ingotAluminum", "ingotAluminium");
		OreDictHelper.registerOre("ingotCopper", InitItems.itemIngot, 1);
		OreDictHelper.registerOre("ingotTin", InitItems.itemIngot, 2);
		OreDictHelper.registerOre("ingotZinc", InitItems.itemIngot, 3);
		OreDictHelper.registerOre("ingotBrass", InitItems.itemIngot, 4);
		OreDictHelper.registerOre("ingotBronze", InitItems.itemIngot, 5);
		OreDictHelper.registerOre("ingotSteel", InitItems.itemIngot, 6);
		OreDictHelper.registerOre("ingotCastIron", InitItems.itemIngot, 7);

		OreDictHelper.registerOreWithAlts(InitItems.itemSheet, "plateAluminum", "plateAluminium");
		OreDictHelper.registerOre("plateCopper", InitItems.itemSheet, 1);
		OreDictHelper.registerOre("plateTin", InitItems.itemSheet, 2);
		OreDictHelper.registerOre("plateZinc", InitItems.itemSheet, 3);
		OreDictHelper.registerOre("plateBrass", InitItems.itemSheet, 4);
		OreDictHelper.registerOre("plateBronze", InitItems.itemSheet, 5);
		OreDictHelper.registerOre("plateSteel", InitItems.itemSheet, 6);
		OreDictHelper.registerOre("plateCastIron", InitItems.itemSheet, 7);
		OreDictionary.registerOre("plateIron", InitItems.itemVanillaSheet);
		OreDictHelper.registerOre("plateGold", InitItems.itemVanillaSheet, 1);

		OreDictHelper.registerOreWithAlts(InitItems.itemNugget, "nuggetAluminum", "nuggetAluminium");
		OreDictHelper.registerOre("nuggetCopper", InitItems.itemNugget, 1);
		OreDictHelper.registerOre("nuggetTin", InitItems.itemNugget, 2);
		OreDictHelper.registerOre("nuggetZinc", InitItems.itemNugget, 3);
		OreDictHelper.registerOre("nuggetBrass", InitItems.itemNugget, 4);
		OreDictHelper.registerOre("nuggetBronze", InitItems.itemNugget, 5);
		OreDictHelper.registerOre("nuggetSteel", InitItems.itemIngot, 6);
		OreDictHelper.registerOre("nuggetCastIron", InitItems.itemNugget, 7);

		OreDictHelper.registerOreWithAlts(InitItems.itemPowder, "dustAluminum", "dustAluminium");
		OreDictHelper.registerOre("dustCopper", InitItems.itemPowder, 1);
		OreDictHelper.registerOre("dustTin", InitItems.itemPowder, 2);
		OreDictHelper.registerOre("dustZinc", InitItems.itemPowder, 3);
		OreDictHelper.registerOre("dustBrass", InitItems.itemPowder, 4);
		OreDictHelper.registerOre("dustBronze", InitItems.itemPowder, 5);
		OreDictHelper.registerOre("dustSteel", InitItems.itemIngot, 6);
		OreDictHelper.registerOre("dustCastIron", InitItems.itemPowder, 7);
		OreDictionary.registerOre("dustIron", InitItems.itemVanillaPowder);
		OreDictHelper.registerOre("dustGold", InitItems.itemVanillaPowder, 1);

		OreDictHelper.registerOreWithAlts(InitBlocks.blockCustomOre, "oreAluminum", "oreAluminium");
		OreDictHelper.registerOre("oreCopper", InitBlocks.blockCustomOre, 1);
		OreDictHelper.registerOre("oreTin", InitBlocks.blockCustomOre, 2);
		OreDictHelper.registerOre("oreZinc", InitBlocks.blockCustomOre, 3);
		OreDictHelper.registerOre("oreUranium", InitBlocks.blockCustomOre, 4);
		OreDictHelper.registerOre("oreBrimstone", InitBlocks.blockCustomOre, 5);
		OreDictHelper.registerOre("orePhosphate", InitBlocks.blockCustomOre, 6);

		OreDictionary.registerOre("crystalEtherium", InitItems.itemResource);
		OreDictHelper.registerOreWithAlts(InitItems.itemResource, 1, "powderSulpher", "sulpher", "powderSulfur", "sulfur");
		OreDictHelper.registerOreWithAlts(InitItems.itemResource, 2, "shardObsidian", "slateObsidian");
		OreDictHelper.registerOre("powderPhosphorus", InitItems.itemResource, 3);
		OreDictHelper.registerOre("ingotUranium", InitItems.itemResource, 4);
		OreDictHelper.registerOre("pelletUranium", InitItems.itemResource, 5);
		OreDictHelper.registerOreWithAlts(InitItems.itemSlimeRubber, "itemRubber", "barRubber", "rawRubber");

		OreDictionary.registerOre("partCastIronRod", InitItems.itemMachinePart);
		OreDictHelper.registerOre("partClockworkMechanism", InitItems.itemMachinePart, 1);
		OreDictHelper.registerOre("partGrating", InitItems.itemMachinePart, 2);
		OreDictHelper.registerOre("partMagnet", InitItems.itemMachinePart, 3);
		OreDictHelper.registerOre("partGenerator", InitItems.itemMachinePart, 4);
		OreDictHelper.registerOre("partFan", InitItems.itemMachinePart, 5);
		OreDictHelper.registerOre("partWireCoil", InitItems.itemMachinePart, 6);

		String[] partType = new String[] { "Gear", "Sprocket", "Spring", "Thread",
				"Nut", "Bolt", "Washer", "Bearing", "Screw", "Nail" };

		for(int i = 0; i < partType.length; i++)
		{
			OreDictHelper.registerOreWithAlts(InitItems.itemCopperParts, i, "partCopper" + partType[i], partType[i].toLowerCase() + "Copper");
			OreDictHelper.registerOreWithAlts(InitItems.itemIronParts, i, "partIron" + partType[i], partType[i].toLowerCase() + "Iron");
			OreDictHelper.registerOreWithAlts(InitItems.itemBrassParts, i, "partBrass" + partType[i], partType[i].toLowerCase() + "Brass");
			OreDictHelper.registerOreWithAlts(InitItems.itemSteelParts, i, "partSteel" + partType[i], partType[i].toLowerCase() + "Steel");

			OreDictHelper.registerOre("partTierOne", InitItems.itemCopperParts, i);
			OreDictHelper.registerOre("partTierOne", InitItems.itemIronParts, i);
			OreDictHelper.registerOre("partTierTwo", InitItems.itemBrassParts, i);
			OreDictHelper.registerOre("partTierTwo", InitItems.itemSteelParts, i);
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
			GameRegistry.addRecipe(new ShapedOreRecipe(InitItems.itemThaumicMonocle, " I ", "ITI", " I ", 'I', "ingotBrass", 'T', thaumometer));
		}
		if(Loader.isModLoaded("TConstruct"))
		{
			GameRegistry.registerBlock(InitBlocks.blockMoltenZinc, "blockMoltenZinc");
			GameRegistry.registerBlock(InitBlocks.blockMoltenBrass, "blockMoltenBrass");
			/*
			 * TConstructRegistry.getTableCasting().addCastingRecipe(new ItemStack(InitItems.itemIngot, 1, 3), new FluidStack(InitBlocks.moltenZincFluid,
			 * ingotLiquidValue), TConstructRegistry.getItemStack("castingot"), 20); TConstructRegistry.getTableCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 4), new FluidStack(InitBlocks.moltenBrassFluid, ingotLiquidValue),
			 * TConstructRegistry.getItemStack("castingot"), 20); TConstructRegistry.getBasinCasting().addCastingRecipe(new ItemStack(InitItems.itemIngot, 1,
			 * 3), new FluidStack(InitBlocks.moltenZincFluid, blockLiquidValue), 20 * 9); TConstructRegistry.getBasinCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 4), new FluidStack(InitBlocks.moltenBrassFluid, blockLiquidValue), 20 * 9);
			 */
		}
	}
}
