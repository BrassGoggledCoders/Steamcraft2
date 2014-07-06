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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemPipe extends BaseItem
{
	public ItemPipe()
	{
		super();
		this.setUnlocalizedName("itemPipe");
		this.setHasSubtypes(true);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setMaxDamage(100);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9,
			float par10)
	{
		if (is.getItemDamage() < 1)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 100, 1));
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
			player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 1));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 10, 1));
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 10, 1));
			this.setDamage(is, 1);
			return true;
		}
		else
			return false;
	}

	@SuppressWarnings({"all"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < 1; ++var4)
			l.add(new ItemStack(ConfigItems.itemPipe, 1, var4));
	}
}
