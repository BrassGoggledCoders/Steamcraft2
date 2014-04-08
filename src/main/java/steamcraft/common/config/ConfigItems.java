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

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemCustomArmor;
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

    public static Item coldKettle;
    public static Item hotKettle;
    public static Item emptyTeacup;
    public static Item fullTeacup;
    public static Item emptyKettle;

    public static Item itemSteamCanister;
    public static Item canisterGas;
    public static Item steamCanister;
    public static Item gasCanister;
    public static Item emptyCanister;

    public static void init()
	{
		initializeItems();
	}
    
    public static void postInit()
    {

    }
	
	public static void initializeItems()
	{
		itemMechanical = new ItemMechanical().setUnlocalizedName("ItemMechanical");
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", "Steamcraft");
		itemResource = new ItemResource().setUnlocalizedName("ItemResource");
		GameRegistry.registerItem(itemResource, "ItemResource", "Steamcraft");
		itemWatch = new ItemWatch().setUnlocalizedName("ItemWatch");
		GameRegistry.registerItem(itemWatch, "ItemWatch", "Steamcraft");
		
		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("ItemTeaSeed");
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", "Steamcraft");
		itemChisel = new ItemChisel().setUnlocalizedName("ItemChisel");
		GameRegistry.registerItem(itemChisel, "ItemChisel", "Steamcraft");
		
		itemBrassGoggles = new ItemCustomArmor(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("ItemCustomArmor");
		GameRegistry.registerItem(itemBrassGoggles, "ItemCustomArmor", "Steamcraft");
	}
}
