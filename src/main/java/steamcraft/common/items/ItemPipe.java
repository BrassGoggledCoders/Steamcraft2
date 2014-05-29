package steamcraft.common.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPipe extends Item
{
	public ItemPipe()
	{
		super();
		setUnlocalizedName("itemPipe");
		setHasSubtypes(true);
		setCreativeTab(Steamcraft.tabSC2);
		setMaxDamage(100);
		setMaxStackSize(1);
	}
	@Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if(is.getItemDamage() < 1)
		{
		player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 100, 1));
		player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
		player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 1));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 10, 1));
		player.addPotionEffect(new PotionEffect(Potion.blindness.id, 10, 1));
		setDamage(is, 1);
        return true;
		}
		else
		return false;
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs tab,
			final List l)
	{
		for (int var4 = 0; var4 < 1; ++var4)
		{
			l.add(new ItemStack(ConfigItems.itemPipe, 1, var4));
		}
	}
}
