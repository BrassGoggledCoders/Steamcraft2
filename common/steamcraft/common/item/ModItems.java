/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 4, 2014, 5:13:56 PM]
 */
package common.steamcraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.lib2.ItemIDs;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author MrArcane111, general3214, decebaldecebal
 *
 */
public class ModItems {
	public static Item etherium;
	public static Item chemicSalt;
	public static Item bornite;
	public static Item obsidianSlate;
	public static Item ingotsMetal, nuggetsMetal, dustsMetal, platesMetal; 
	public static Item lightBulb;
	public static Item phosphorus;
	public static Item uraniumOre;
	public static Item uraniumPellet;
	public static Item reactorCore;
	public static Item teaSeed;
	public static Item teaLeaves;
	public static Item coldKettle;
	public static Item hotKettle;
	public static Item emptyTeacup;
	public static Item fullTeacup;
	public static Item emptyKettle;
	public static Item copperWire;
	public static Item brassWatch;
	public static Item canisterSteam;
	public static Item canisterGas;
    public static Item canisterEmpty;
    public static Item guideBook;
    public static Item partsBrass, partsSteel, partsCopper, partsIron, pileParts;
    public static Item steamBucket;
    public static Item topHat;
	
	public static void initItems() {
		etherium = new ItemMod(ItemIDs.etheriumID).setUnlocalizedName("etherium");
		chemicSalt = new ItemMod(ItemIDs.chemicSaltID).setUnlocalizedName("sulphur");
		bornite = new ItemMod(ItemIDs.borniteID).setUnlocalizedName("copper");
		obsidianSlate = new ItemMod(ItemIDs.obsidianSlateID).setUnlocalizedName("obsidianslate");
		ingotsMetal = new ItemMetalIngots(ItemIDs.ingotsMetalID).setUnlocalizedName("ingotsMetal");  
		 /* nuggetsMetals
		 * dustsMetals
		 * platesMetals
		 */
		lightBulb = new ItemMod(ItemIDs.lightBulbID).setUnlocalizedName("lightbulb");
		phosphorus = new ItemMod(ItemIDs.phosphorusID).setUnlocalizedName("phosphorus");
		uraniumOre = new ItemMod(ItemIDs.uraniumStoneID).setUnlocalizedName("uraniumstone");
		uraniumPellet = new ItemMod(ItemIDs.uraniumID).setUnlocalizedName("uraniumpellet");
		reactorCore = new ItemMod(ItemIDs.reactorCoreID).setUnlocalizedName("reactorcore");
		teaSeed = new ItemTeaSeeds(ItemIDs.teaSeedID, ModBlocks.teaPlant.blockID, Block.tilledField.blockID).setUnlocalizedName("teaseed"); 
		teaLeaves = new ItemMod(ItemIDs.teaLeavesID).setUnlocalizedName("tealeaves");
		//coldKettle = new ItemKettle(SC_ItemIDs.coldKettleID, 300).setUnlocalizedName("kettle");
		//hotKettle = new ItemKettle(SC_ItemIDs.hotKettleID, 300).setUnlocalizedName("kettle");
		//emptyKettle = new ItemKettle(SC_ItemIDs.emptyKettleID, 300).setUnlocalizedName("kettle");
		//fullTeacup = new ItemTeacup(SC_ItemIDs.fullTeacupID, 4, 0.6F, false).setUnlocalizedName("teacupfull");
		//emptyTeacup = new ItemTeacup(SC_ItemIDs.emptyTeacupID, 0, 0.0F, false).setUnlocalizedName("teacupempty");
		copperWire = new ItemCopperWire(ItemIDs.copperWireID).setUnlocalizedName("copperwire");
		brassWatch = new ItemBrassWatch(ItemIDs.brassWatchID);
		canisterSteam = new ItemCanister(ItemIDs.canisterSteamID, "steam").setUnlocalizedName("canisterSteam");
		canisterGas = new ItemCanister(ItemIDs.canisterGasID, "compressedgas").setUnlocalizedName("canisterGas");
		canisterEmpty = new ItemMod(ItemIDs.canisterEmptyID).setUnlocalizedName("canisterEmpty");
		guideBook = new ItemGuideBook(ItemIDs.guideBookID).setUnlocalizedName("guidebook");
		partsBrass = new ItemBrassParts(ItemIDs.partsBrassID);
		partsSteel = new ItemSteelParts(ItemIDs.partsSteelID);
		partsCopper = new ItemCopperParts(ItemIDs.partsCopperID);
		partsIron = new ItemIronParts(ItemIDs.partsIronID);
		pileParts = new ItemPartsPile(ItemIDs.pilePartsID);
		topHat = new ItemHat(ItemIDs.topHatID).setUnlocalizedName("topHat");
		
		ModTools.initTools();
		ModArmors.initArmors();
		ModGuns.initGuns();
		
		steamBucket = new ItemBucket(ItemIDs.steamBucketID, 1000);
		steamBucket.setUnlocalizedName("steamBucket").setContainerItem(Item.bucketEmpty);
		GameRegistry.registerItem(steamBucket, "steamBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("steam", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(steamBucket), new ItemStack(Item.bucketEmpty));
	}
}
