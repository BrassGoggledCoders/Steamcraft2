package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import steamcraft.common.items.armor.BaseArmor;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBrassGoggles extends BaseArmor
{
	public ItemBrassGoggles(ArmorMaterial mat, int p_i45325_2_, int p_i45325_3_)
	{
		super(mat, p_i45325_2_, p_i45325_3_);
		setMaxStackSize(1);
		setUnlocalizedName("itemBrassGoggles");
	}
}
