package steamcraft.common.items;

import java.util.List;

import boilerplate.common.utils.StringUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import steamcraft.client.ClientHelper;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseItem extends Item
{
	boolean descNeedsShift = true;
	public BaseItem()
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
	}
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
            itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
    	if(!StatCollector.translateToLocal(getUnlocalizedName() + ".desc").contains("item."))
    	{
    		if(descNeedsShift)
    		{
    		if(ClientHelper.isShiftKeyDown())
    		{
    		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 30);
    		for(int i = 0; i<wrappedDesc.length; i++)
    			list.add(wrappedDesc[i].trim());
    		}
    		else
    		list.add(ClientHelper.shiftForInfo);
    		}
    		else
    		{
        		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 30);
        		for(int i = 0; i<wrappedDesc.length; i++)
        			list.add(wrappedDesc[i].trim());
    		}

    	}
    }
}
