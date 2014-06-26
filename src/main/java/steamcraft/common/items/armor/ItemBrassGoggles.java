/*
 *
 */
package steamcraft.common.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


// TODO: Auto-generated Javadoc
/**
 * The Class ItemBrassGoggles.
 */
public class ItemBrassGoggles extends BaseArmor
{

	/**
	 * Instantiates a new item brass goggles.
	 *
	 * @param mat
	 *            the mat
	 * @param p_i45325_2_
	 *            the p_i45325_2_
	 * @param p_i45325_3_
	 *            the p_i45325_3_
	 */
	public ItemBrassGoggles(ArmorMaterial mat, int p_i45325_2_, int p_i45325_3_)
	{
		super(mat, p_i45325_2_, p_i45325_3_);
		setMaxStackSize(1);
		setUnlocalizedName("itemBrassGoggles");
	}
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return LibInfo.PREFIX + "textures/armor/goggles.png";
	}
}
