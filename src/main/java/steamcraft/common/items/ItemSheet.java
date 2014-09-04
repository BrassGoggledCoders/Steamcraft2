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
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.InitItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.item.IArmorModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemSheet extends BaseItemWithMetadata implements IArmorModule
{
	IIcon[] itemIcon = new IIcon[8];

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for(int var4 = 0; var4 < 8; ++var4)
			list.add(new ItemStack(InitItems.itemSheet, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for(int i = 0; i < this.itemIcon.length; i++)
			this.itemIcon[i] = ir.registerIcon(LibInfo.PREFIX + "metals/" + "itemSheet" + LibInfo.metals[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}

	@Override
	public int getApplicablePiece()
	{
		return -1;
	}

	@Override
	public String getName()
	{
		return "Plate";
	}

	@Override
	public void getArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		System.out.print("Test!");
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}
}
