/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;

import boilerplate.common.baseclasses.items.RootItem;

/**
 * @author decebaldecebal
 *
 */
public abstract class UniversalWrench extends RootItem implements IUniversalWrench
{
	@Override
	@Optional.Method(modid = "BuildCraft|Core")
	public boolean canWrench(EntityPlayer player, int x, int y, int z)
	{
		return true;
	}

	@Override
	@Optional.Method(modid = "BuildCraft|Core")
	public void wrenchUsed(EntityPlayer player, int x, int y, int z)
	{

	}

	@Override
	public boolean isUsable(ItemStack item, EntityLivingBase user, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void toolUsed(ItemStack item, EntityLivingBase user, int x, int y, int z)
	{

	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public boolean canWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z)
	{
		return true;
	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public void onWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public boolean canLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		return player.isSneaking();
	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public void onLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public boolean canBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		return !this.canLink(player, crowbar, cart);
	}

	@Override
	@Optional.Method(modid = "Railcraft")
	public void onBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}
}
