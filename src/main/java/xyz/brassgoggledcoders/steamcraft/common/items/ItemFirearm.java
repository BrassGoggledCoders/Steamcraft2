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
package xyz.brassgoggledcoders.steamcraft.common.items;

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
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityBullet;
import xyz.brassgoggledcoders.steamcraft.common.init.InitItems;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseFirearm;

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
