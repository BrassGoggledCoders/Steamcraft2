
package steamcraft.common.items;

import boilerplate.common.baseclasses.items.BaseFirearm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * @author warlordjones & Decebaldecebal
 *
 */
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.entities.projectile.EntityBullet;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

public class ItemFirearm extends BaseFirearm
{
	public ItemFirearm(int damage, int reloadTime, Item ammo, Item ammo2, String fireSound, String reloadSound)
	{
		super(damage, reloadTime, ammo, ammo2, fireSound, reloadSound);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	protected void shotBullet(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();
		tag.setShort("reloadTime", this.reloadTime);
		tag.setBoolean("canFire", false);
		stack.setTagCompound(tag);

		player.inventory.consumeInventoryItem(this.ammo);
		player.inventory.consumeInventoryItem(Items.gunpowder);

		if (!world.isRemote)
		{
			if (this.ammo == InitItems.itemMusketBall)
				world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 8));
			else
				world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 15));
		}

		world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
	}
}
