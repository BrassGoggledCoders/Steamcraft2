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
 * File created @ [3/18/14, 12:06]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class ItemMechanical extends Item
{
	//public static Item brassWatch;
	//public static Item canisterSteam;
	//public static Item canisterGas;
	//public static Item steamCanister;
	//public static Item gasCanister;
    //public static Item emptyCanister;
	
    public Icon[] icon = new Icon[6];

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int itemDamage)
    {
        return this.icon[itemDamage];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {

    }

    public ItemMechanical(int id)
    {
    	super(id);
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Steamcraft.tabSC2);
    }
}
