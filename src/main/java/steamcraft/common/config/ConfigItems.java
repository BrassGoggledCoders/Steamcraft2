/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Mar 14, 2014, 1:29:42 PM]
 */
package steamcraft.common.config;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemGunParts;
import steamcraft.common.items.ItemKettle;
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemTeacup;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemCustomArmor;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ConfigItems
{
    public static Item itemMechanical;
    public static Item itemResource;
    public static Item itemWatch;
    public static Item itemTeaSeed;
    public static Item itemChisel;
    
    public static Item itemBrassGoggles;
    
    public static Item itemTeacupEmpty;
    public static Item itemTeacupFull;
    public static Item itemKettleEmpty;
    public static Item itemKettleHot;
    public static Item itemKettleCold;
    public static Item itemGunParts;
    
    public static Item itemSteamCanister;
    public static Item itemCanisterGas;
    public static Item itemEmptyCanister;
    
    public static Item itemMusketRound;
    public static Item itemBucketSteam;

    public static void init()
	{
		initializeItems();
	}
    
    public static void postInit() {}
	
	public static void initializeItems()
	{
		itemMechanical = new ItemMechanical(Config.itemMechanicalId).setUnlocalizedName("itemMechanical");
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", LibInfo.ID);
		itemResource = new ItemResource(Config.itemResourceId).setUnlocalizedName("itemResource");
		GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		itemWatch = new ItemWatch(Config.itemWatchId).setUnlocalizedName("itemWatch");
		GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);
		itemTeaSeed = new ItemTeaSeed(Config.itemTeaSeedId).setUnlocalizedName("itemTeaSeed");
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		itemChisel = new ItemChisel(Config.itemChiselId).setUnlocalizedName("itemChisel");
		GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);
		
		itemBrassGoggles = new ItemCustomArmor(Config.itemGogglesId, EnumArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemGoggles");
		GameRegistry.registerItem(itemBrassGoggles, "ItemCustomArmor", LibInfo.ID);
		
		itemTeacupEmpty = new ItemTeacup(Config.itemTeacupEmptyId, 0, 0.0F, false).setUnlocalizedName("itemTeacupEmpty");
		GameRegistry.registerItem(itemTeacupEmpty, "ItemTeacupEmpty", LibInfo.ID);
		itemTeacupFull = new ItemTeacup(Config.itemTeacupFullId, 4, 0.9F, false).setUnlocalizedName("itemTeacupFull");
		GameRegistry.registerItem(itemTeacupFull, "ItemTeacupFull", LibInfo.ID);
		itemKettleEmpty = new ItemKettle(Config.itemKettleEmptyId, 300).setUnlocalizedName("itemKettleEmpty");
		GameRegistry.registerItem(itemTeacupFull, "ItemKettleEmpty", LibInfo.ID);
		itemKettleHot = new ItemKettle(Config.itemKettleHotId, 300).setUnlocalizedName("itemKettleHot");
		GameRegistry.registerItem(itemTeacupFull, "ItemKettleHot", LibInfo.ID);
		itemKettleCold = new ItemKettle(Config.itemKettleColdId, 300).setUnlocalizedName("itemKettleCold");
		GameRegistry.registerItem(itemTeacupFull, "ItemKettleCold", LibInfo.ID);
		itemGunParts = new ItemGunParts(Config.itemGunPartsId).setUnlocalizedName("itemGunParts");
		GameRegistry.registerItem(itemGunParts, "ItemGunParts", LibInfo.ID);
	}
}
