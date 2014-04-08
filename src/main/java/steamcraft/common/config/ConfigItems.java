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
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemTeaSeed;
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

    public static Item itemColdKettle;
    public static Item itemHotKettle;
    public static Item itemEmptyKettle;
    public static Item itemEmptyTeacup;
    public static Item itemFullTeacup;

    public static Item itemSteamCanister;
    public static Item itemCanisterGas;
    public static Item itemEmptyCanister;

    public static void init()
	{
		initializeItems();
	}
    
    public static void postInit() {}
	
	public static void initializeItems()
	{
		itemMechanical = new ItemMechanical(Config.itemMechanicalId).setUnlocalizedName("ItemMechanical");
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", LibInfo.ID);
		itemResource = new ItemResource(Config.itemResourceId).setUnlocalizedName("ItemResource");
		GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		itemWatch = new ItemWatch(Config.itemWatchId).setUnlocalizedName("ItemWatch");
		GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);
		
		itemTeaSeed = new ItemTeaSeed(Config.itemTeaSeedId).setUnlocalizedName("ItemTeaSeed");
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		itemChisel = new ItemChisel(Config.itemChiselId).setUnlocalizedName("ItemChisel");
		GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);
		
		itemBrassGoggles = new ItemCustomArmor(Config.itemGogglesId, EnumArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("ItemCustomArmor");
		GameRegistry.registerItem(itemBrassGoggles, "ItemCustomArmor", LibInfo.ID);
	}
}
