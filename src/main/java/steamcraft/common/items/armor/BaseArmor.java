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

public class BaseArmor extends ItemArmor
{
	boolean descNeedsShift = true;

	  public BaseArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_)
	  {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		setCreativeTab(Steamcraft.tabSC2);
		setMaxStackSize(1);
	  }
	@SideOnly(Side.CLIENT)
	    @Override
	    public void registerIcons(IIconRegister par1IconRegister)
	    {
	            itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	    }
	    @SideOnly(Side.CLIENT)
	    @Override
	    public void addInformation(ItemStack parO1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	    {
	    	if(!StatCollector.translateToLocal(getUnlocalizedName() + ".desc").contains("item."))
	    	{
	    		if(descNeedsShift)
	    		{
	    		if(ClientHelper.isShiftKeyDown())
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
	    public void getWrappedDesc(List list)
	    {
			String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 30);
			for(int i = 0; i<wrappedDesc.length; i++)
				list.add(wrappedDesc[i].trim());
	    }
}
