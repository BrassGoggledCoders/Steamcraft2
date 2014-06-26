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
 * File created @ [Jun 23, 2014, 10:51:48 PM]
 */
package steamcraft.common.items;

/**
 *
 * @author warlordjones
 */
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.entities.projectile.EntityBullet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFirearm extends BaseItem
{
	private int damage;
	private short reloadTime;
	private boolean twoAmmo;

	private Item ammo;
	private Item ammo2;

	private String fireSound;
	private String reloadSound;

	public ItemFirearm(int damage, int reloadTime, Item ammo, Item ammo2, String fireSound, String reloadSound)
	{
		super();
		this.damage = damage;
		this.reloadTime = (short)reloadTime;
		this.twoAmmo = ammo2 != null;

		this.ammo = ammo;
		this.ammo2 = ammo2;

		this.fireSound = fireSound;
		this.reloadSound = reloadSound;
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if(entity instanceof EntityPlayer && ((EntityPlayer) entity).getCurrentEquippedItem() == stack)
		{
			EntityPlayer player = (EntityPlayer) entity;

			if(!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = stack.getTagCompound();

			if(tag.getShort("reloadTime") > 0)
			{
				tag.setShort("reloadTime", (short)(tag.getShort("reloadTime")-1));
				stack.setTagCompound(tag);

				if(tag.getShort("reloadTime") == 10)
					world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);

			}
			else if(tag.getBoolean("canFire"))
			{
				if (stack.getTagCompound().getShort("reloadTime") == 0 && player.inventory.hasItem(Items.gunpowder) && player.inventory.hasItem(ammo))
					if(twoAmmo)
					{
						if(player.inventory.hasItem(ammo2))
							shotBullet(stack, world, player);
							player.inventory.consumeInventoryItem(ammo2);
					}
					else
						shotBullet(stack, world, player);
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();

		if (tag.getShort("reloadTime") == 0 && player.inventory.hasItem(Items.gunpowder) && player.inventory.hasItem(ammo))
			if(twoAmmo)
			{
				if(player.inventory.hasItem(ammo2))
					shotBullet(stack, world, player);
					player.inventory.consumeInventoryItem(ammo2);
			}
			else
				shotBullet(stack, world, player);
		return stack;
	}

	private void shotBullet(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();
		tag.setShort("reloadTime", reloadTime);
		tag.setBoolean("canFire", false);
		stack.setTagCompound(tag);

		player.inventory.consumeInventoryItem(ammo);
		player.inventory.consumeInventoryItem(Items.gunpowder);

		if(twoAmmo)
			player.inventory.consumeInventoryItem(ammo2);


		if (!world.isRemote)
			if(this.ammo == ConfigItems.itemMusketBall)
			world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 8));
			else
			world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 15));

		world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
	}
}
