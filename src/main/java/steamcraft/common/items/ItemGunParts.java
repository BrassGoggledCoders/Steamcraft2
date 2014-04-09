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
 * File created @ [Apr 8, 2014, 8:13:58 PM]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemGunParts extends Item
{
	private Icon[] icon = new Icon[7];
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return this.icon[damage];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "smoothbarrel");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "rifledbarrel");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "woodenstock");
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "percussionlock");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "musketcartridge");
		this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "percussioncap");
		this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "rifleround");
	}
	
	public ItemGunParts(int id)
	{
		super(id);
		this.setCreativeTab(Steamcraft.tabSC2);
	}
}
