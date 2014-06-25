package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.entities.projectile.EntityBullet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFirearm extends BaseItem
{
	private int damage;
	private Item ammo;
	private Item ammo2;
	private int fireMax;
	private boolean twoAmmo;
	private String fireSound;
	private String reloadSound;

	public ItemFirearm(int damage, int delay, Item ammo, Item ammo2, boolean twoAmmo, String fireSound, String reloadSound)
	{
		super();
		this.damage = damage;
		this.fireMax = delay;
		this.ammo = ammo;
		this.ammo2 = ammo2;
		this.twoAmmo = twoAmmo;
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
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		/*
		 *
		 * Also implemented the reload.The weapon fires first then reloads, but if you do not hold the right mouse button for long enough, it won't
		 * play the reload sound, which is a small problem.To track if it is reloaded or not, it needs to be implemented through NBT and onUpdate()
		 *
		 */
		if(!twoAmmo)
		{
			if (player.inventory.hasItem(ammo) && player.inventory.hasItem(Items.gunpowder))
			{
				World world = player.worldObj;

				if (count == 1)
				world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);

				if (count == this.getMaxItemUseDuration(stack) / 2)
				{
				player.inventory.consumeInventoryItem(ammo);
				player.inventory.consumeInventoryItem(Items.gunpowder);

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 1));
				}

				world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
			}
			}
		}
		else
		{
			if (player.inventory.hasItem(ammo) && player.inventory.hasItem(ammo2) && player.inventory.hasItem(Items.gunpowder))
			{
				World world = player.worldObj;

				if (count == 1)
				world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);

				if (count == this.getMaxItemUseDuration(stack) / 2)
				{
				player.inventory.consumeInventoryItem(ammo);
				player.inventory.consumeInventoryItem(ammo2);
				player.inventory.consumeInventoryItem(Items.gunpowder);

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 1));
				}

				world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
			}
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return this.fireMax;
	}
}
