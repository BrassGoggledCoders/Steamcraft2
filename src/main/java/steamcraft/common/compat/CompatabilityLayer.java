
package steamcraft.common.compat;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import boilerplate.common.baseclasses.items.BaseItemBlockWithMetadata;
import boilerplate.common.utils.helpers.IMCHelper;
import boilerplate.common.utils.helpers.OreDictHelper;
import steamcraft.common.init.InitBiomes;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.ModInfo;

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
	}

	private static void sendIMCMessages()
	{
		// Version Checker
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("curseProjectName", "224017-steamcraft-2-beta-2-fluxian-storm");
		tag.setString("curseFilenameParser", "steamcraft2-1.7.10-[].jar");
		FMLInterModComms.sendRuntimeMessage(ModInfo.ID, "VersionChecker", "addCurseCheck", tag);

		if (Loader.isModLoaded("TConstruct"))
			sendTiConIMC();
		if (Loader.isModLoaded("Thaumcraft"))
			sendThaumcraftIMC();
		if (Loader.isModLoaded("AquaTweaks"))
		{
			String[] blockNames = new String[] { "BlockCastIronFence", "BlockCastIronGate", "BlockCastIronRailing", "BlockLightningRod",
					"BlockTeslaCoil", "BlockCopperPipe", "BlockCopperWire", "BlockRedwoodFence", "blockMangroveFence", "blockWillowFence",
					"blockPetrifiedFence", "BlockCharger" };
			for (String blockName : blockNames)
			{
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setString("modid", ModInfo.ID);
				tag1.setString("block", blockName);
				FMLInterModComms.sendMessage("AquaTweaks", "registerAquaConnectable", tag1);
			}
		}
	}

	private static void sendThaumcraftIMC()
	{
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(InitBlocks.blockTeaPlant, 1, 1));
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster", Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 0 + ","
				+ Item.getIdFromItem(InitItems.itemSteamcraftCluster) + "," + 0 + ",2.0");
		FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster", Block.getIdFromBlock(InitBlocks.blockCustomOre) + "," + 2 + ","
				+ Item.getIdFromItem(InitItems.itemSteamcraftCluster) + "," + 1 + ",2.0");
	}

	private static void sendTiConIMC()
	{
		LoggerSteamcraft.info("TiCon Detected, adding Etherium Tool Material");
		// Aluminum, Copper, Tin
		for (int i = 0; i < 3; i++)
		{
			BlockFluidClassic block_fluid = (BlockFluidClassic) GameRegistry.findBlock("TConstruct",
					"fluid.molten." + LibInfo.metals[i].toLowerCase());
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), blockLiquidValue), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockCustomOre, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue * 2), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue), 300);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), nuggetLiquidValue), 150);
		}
		// Skip Zinc and Brass. Bronze, Steel.
		for (int i = 5; i < 7; i++)
		{
			String metalname = LibInfo.metals[i].toLowerCase();
			BlockFluidClassic block_fluid = (BlockFluidClassic) GameRegistry.findBlock("TConstruct", "fluid.molten." + metalname);
			IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), blockLiquidValue), 600);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), ingotLiquidValue), 300);
			IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, i), InitBlocks.blockMetal,
					new FluidStack(block_fluid.getFluid(), nuggetLiquidValue), 150);
		}
		/*
		 * Zinc IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockMetal,
		 * 1, 3), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenZincFluid, blockLiquidValue), 600);
		 * IMCHelper.addNewSmeltable(new ItemStack(InitBlocks.blockCustomOre, 1,
		 * 3), InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid,
		 * ingotLiquidValue * 2), 600); IMCHelper.addNewSmeltable(new
		 * ItemStack(InitItems.itemIngot, 1, 3), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenZincFluid, ingotLiquidValue), 300);
		 * IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemNugget, 1, 3),
		 * InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenZincFluid,
		 * nuggetLiquidValue), 150); // Brass IMCHelper.addNewSmeltable(new
		 * ItemStack(InitBlocks.blockMetal, 1, 4), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenBrassFluid, blockLiquidValue), 600);
		 * IMCHelper.addNewSmeltable(new ItemStack(InitItems.itemIngot, 1, 4),
		 * InitBlocks.blockMetal, new FluidStack(InitBlocks.moltenBrassFluid,
		 * ingotLiquidValue), 300); IMCHelper.addNewSmeltable(new
		 * ItemStack(InitItems.itemNugget, 1, 4), InitBlocks.blockMetal, new
		 * FluidStack(InitBlocks.moltenBrassFluid, nuggetLiquidValue), 150);
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
		OreDictHelper.registerOre("nuggetSteel", InitItems.itemNugget, 6);
		OreDictHelper.registerOre("nuggetCastIron", InitItems.itemNugget, 7);
		OreDictHelper.registerOre("nuggetIron", InitItems.itemNuggetIron, 0);

		OreDictHelper.registerOreWithAlts(InitItems.itemPowder, "dustAluminum", "dustAluminium");
		OreDictHelper.registerOre("dustCopper", InitItems.itemPowder, 1);
		OreDictHelper.registerOre("dustTin", InitItems.itemPowder, 2);
		OreDictHelper.registerOre("dustZinc", InitItems.itemPowder, 3);
		OreDictHelper.registerOre("dustBrass", InitItems.itemPowder, 4);
		OreDictHelper.registerOre("dustBronze", InitItems.itemPowder, 5);
		OreDictHelper.registerOre("dustSteel", InitItems.itemPowder, 6);
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

		OreDictHelper.registerOreWithAlts(InitBlocks.blockMetal, "blockAluminum", "blockAluminium");
		OreDictHelper.registerOre("blockCopper", InitBlocks.blockMetal, 1);
		OreDictHelper.registerOre("blockTin", InitBlocks.blockMetal, 2);
		OreDictHelper.registerOre("blockZinc", InitBlocks.blockMetal, 3);
		OreDictHelper.registerOre("blockUranium", InitBlocks.blockMetal, 4);
		OreDictHelper.registerOre("blockBrimstone", InitBlocks.blockMetal, 5);
		OreDictHelper.registerOre("blockPhosphate", InitBlocks.blockMetal, 6);

		OreDictHelper.registerOreWithAlts(InitItems.itemResource, 0, "powderSulfur", "sulfur", "dustSulfur");
		OreDictHelper.registerOre("powderPhosphorus", InitItems.itemResource, 1);
		OreDictHelper.registerOreWithAlts(InitItems.itemResource, 2, "sulfuricAcid", "bottleSulfuricAcid");
		OreDictHelper.registerOre("ingotUranium", InitItems.itemResource, 3);
		OreDictHelper.registerOre("pelletUranium", InitItems.itemResource, 4);
		OreDictHelper.registerOreWithAlts(InitItems.itemSlimeRubber, "itemRubber", "barRubber", "rawRubber");

		OreDictionary.registerOre("partCastIronRod", InitItems.itemMachinePart);
		OreDictHelper.registerOre("partClockworkMechanism", InitItems.itemMachinePart, 1);
		OreDictHelper.registerOre("partGrating", InitItems.itemMachinePart, 2);
		OreDictHelper.registerOre("partMagnet", InitItems.itemMachinePart, 3);
		OreDictHelper.registerOre("partGenerator", InitItems.itemMachinePart, 4);
		OreDictHelper.registerOre("partFan", InitItems.itemMachinePart, 5);
		OreDictHelper.registerOre("partWireCoil", InitItems.itemMachinePart, 6);

		String[] partType = new String[] { "Gear", "Sprocket", "Spring", "Thread", "Nut", "Bolt", "Washer", "Bearing", "Screw", "Nail" };

		for (int i = 0; i < partType.length; i++)
		{
			OreDictHelper.registerOreWithAlts(InitItems.itemIronParts, i, "partIron", partType[i], partType[i].toLowerCase() + "Iron");
			OreDictHelper.registerOreWithAlts(InitItems.itemSteelParts, i, "partSteel", partType[i], partType[i].toLowerCase() + "Steel");

			OreDictHelper.registerOre("partTierOne", InitItems.itemIronParts, i);
			OreDictHelper.registerOre("partTierTwo", InitItems.itemSteelParts, i);
		}
		OreDictionary.registerOre("logWood", InitBlocks.blockRedwoodLog);
		OreDictionary.registerOre("logWood", InitBlocks.blockWillowLog);
		OreDictionary.registerOre("logWood", InitBlocks.blockMangroveLog);
		OreDictionary.registerOre("logWood", InitBlocks.blockPetrifiedLog);
		OreDictionary.registerOre("plankWood", InitBlocks.blockRedwoodPlanks);
		OreDictionary.registerOre("plankWood", InitBlocks.blockWillowPlanks);
		OreDictionary.registerOre("plankWood", InitBlocks.blockMangrovePlanks);
		OreDictionary.registerOre("plankWood", InitBlocks.blockPetrifiedPlanks);
		OreDictionary.registerOre("treeLeaves", InitBlocks.blockRedwoodLeaves);
		OreDictionary.registerOre("treeLeaves", InitBlocks.blockWillowLeaves);
		OreDictionary.registerOre("treeLeaves", InitBlocks.blockMangroveLeaves);
		OreDictionary.registerOre("treeLeaves", InitBlocks.blockDeadLeaves);

		OreDictionary.registerOre("craftingHammer", new ItemStack(InitItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingHammer", new ItemStack(InitItems.itemBugHammer, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockSlate, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockSlate, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockSlate, 1, 2));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockSlate, 1, 3));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockSlate, 1, 4));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockSlate, 1, 5));

		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockLightSlate, 1, 0));
		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockLightSlate, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(InitBlocks.blockLightSlate, 1, 2));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockLightSlate, 1, 3));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockLightSlate, 1, 4));
		OreDictionary.registerOre("cobblestone", new ItemStack(InitBlocks.blockLightSlate, 1, 5));

		for (int i = 1; i < 10; i++)
			OreDictionary.registerOre("foodTea", new ItemStack(InitItems.itemTeacup, 1, i));

		OreDictionary.registerOre("blockUranium", new ItemStack(InitBlocks.blockUranium));
	}

	private static void registerBiomeTypes()
	{
		LoggerSteamcraft.info("Registering Biome Dictionary entries");
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepths, BiomeDictionary.Type.HILLS);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsF, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsM, BiomeDictionary.Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SPOOKY,
				BiomeDictionary.Type.FOREST);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsI, BiomeDictionary.Type.WASTELAND);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsJ, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSC, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSCH, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsSW, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP);
		BiomeDictionary.registerBiomeType(InitBiomes.biomeDepthsTF, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE);
	}

	public static void initCompatItems()
	{
		if (Loader.isModLoaded("Thaumcraft"))
		{
			LoggerSteamcraft.info("Thaumcraft Detected. Loading Wizarding Module");

			GameRegistry.registerItem(InitItems.itemSteamcraftCluster, "ItemSteamcraftCluster");
			GameRegistry.addSmelting(new ItemStack(InitItems.itemSteamcraftCluster, 1, 0), new ItemStack(InitItems.itemIngot, 2, 0), 0);
			GameRegistry.addSmelting(new ItemStack(InitItems.itemSteamcraftCluster, 1, 1), new ItemStack(InitItems.itemIngot, 2, 3), 0);

			GameRegistry.registerItem(InitItems.itemThaumicMonocle, "ItemThaumicMonocle");
			Item thaumometer = GameRegistry.findItem("Thaumcraft", "ItemThaumometer");
			GameRegistry.addRecipe(new ShapedOreRecipe(InitItems.itemThaumicMonocle, " I ", "ITI", " I ", 'I', "ingotBrass", 'T', thaumometer));
		}
		if (Loader.isModLoaded("TConstruct"))
		{
			// GameRegistry.registerBlock(InitBlocks.blockMoltenZinc,
			// "blockMoltenZinc");
			// GameRegistry.registerBlock(InitBlocks.blockMoltenBrass,
			// "blockMoltenBrass");
			/*
			 * TConstructRegistry.getTableCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 3), new
			 * FluidStack(InitBlocks.moltenZincFluid, ingotLiquidValue),
			 * TConstructRegistry.getItemStack("castingot"), 20);
			 * TConstructRegistry.getTableCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 4), new
			 * FluidStack(InitBlocks.moltenBrassFluid, ingotLiquidValue),
			 * TConstructRegistry.getItemStack("castingot"), 20);
			 * TConstructRegistry.getBasinCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 3), new
			 * FluidStack(InitBlocks.moltenZincFluid, blockLiquidValue), 20 *
			 * 9); TConstructRegistry.getBasinCasting().addCastingRecipe(new
			 * ItemStack(InitItems.itemIngot, 1, 4), new
			 * FluidStack(InitBlocks.moltenBrassFluid, blockLiquidValue), 20 *
			 * 9);
			 */
		}
		if (Loader.isModLoaded("BrassUtils"))
		{
			InitBlocks.registerBlock(InitBlocks.blockEngraved, BaseItemBlockWithMetadata.class, "BlockEngravedSolid", 10);
		}
	}
}
