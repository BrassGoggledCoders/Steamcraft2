package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import steamcraft.client.ClientHelper;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBrassGoggles extends ItemArmor
{

	public ItemBrassGoggles(ArmorMaterial mat, int p_i45325_2_, int p_i45325_3_)
	{
		super(mat, p_i45325_2_, p_i45325_3_);
		setMaxStackSize(1);
		setCreativeTab(Steamcraft.tabSC2);
		setUnlocalizedName("itemBrassGoggles");
	}
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		super.addInformation(stack, player, list, flag);
		if(!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}
		else
		{
			list.add("It is a violation of");
			list.add("the law of steampunk");
			list.add("to fly without these.");
			// TODO: Make this work to help seeing underwater + at night
			list.add("Helps with seeing things.");
		}
	}
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
            itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "armor/itemBrassGoggles.png");
    }
}
