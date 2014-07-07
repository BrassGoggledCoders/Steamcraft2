package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeacup extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[2];

	public ItemTeacup()
	{
		super();
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setFull3D();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		itemIcon[0] = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Empty");
		itemIcon[1] = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Full");
	}
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItemDamage() > 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 50, 100));
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
		return stack;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 11; ++var4)
		l.add(new ItemStack(ConfigItems.itemTeacup, 1, var4));
	}
	@Override
	// TODO: Make module-sensitive
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean flag)
	{
		if(stack.getItemDamage() == 0)
		{
			l.add("Empty");
		}
		else
		{
			l.add("Filled with Tea");
			l.add(stack.getItemDamage() + " sips remaining");
		}
	}
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName();
	}

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
    	if(meta == 0)
        return itemIcon[0];
    	else return itemIcon[1];
    }
}
