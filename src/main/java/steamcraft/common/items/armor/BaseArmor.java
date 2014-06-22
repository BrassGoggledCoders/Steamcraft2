/*
 * 
 */
package steamcraft.common.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseArmor.
 */
public class BaseArmor extends ItemArmor
{

	/** The desc needs shift. */
	boolean descNeedsShift = true;

	/**
	 * Instantiates a new base armor.
	 * 
	 * @param p_i45325_1_
	 *            the p_i45325_1_
	 * @param p_i45325_2_
	 *            the p_i45325_2_
	 * @param p_i45325_3_
	 *            the p_i45325_3_
	 */
	public BaseArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_)
	{
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		setCreativeTab(Steamcraft.tabSC2);
		setMaxStackSize(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.ItemArmor#registerIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#addInformation(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack parO1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (!StatCollector.translateToLocal(getUnlocalizedName() + ".desc").contains("item."))
		{
			if (descNeedsShift)
			{
				if (ClientHelper.isShiftKeyDown())
				{
					getWrappedDesc(list);
				}
				else
					list.add(ClientHelper.shiftForInfo);
			}
			else
			{
				getWrappedDesc(list);
			}

		}
	}

	/**
	 * Gets the wrapped desc.
	 * 
	 * @param list
	 *            the list
	 * @return the wrapped desc
	 */
	public void getWrappedDesc(List list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 30);
		for (int i = 0; i < wrappedDesc.length; i++)
			list.add(wrappedDesc[i].trim());
	}
}
