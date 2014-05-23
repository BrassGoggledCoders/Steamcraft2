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
 * File created @ [3/18/14, 12:17]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class ItemResource extends Item
{
	public IIcon[] icon = new IIcon[12];

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(final int itemDamage)
	{
		return icon[itemDamage];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "itemCrystal");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "itemChemSalt");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "itemBornite");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "itemSlate");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "itemBulb");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "itemPhosphorus");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "itemUranium");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "itemPellet");
		icon[8] = ir.registerIcon(LibInfo.PREFIX + "itemReactorCore");
		icon[9] = ir.registerIcon(LibInfo.PREFIX + "itemTeaLeaves");
	}

	public ItemResource()
	{
		super();
		setMaxStackSize(64);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(Steamcraft.tabSC2);
	}
}
