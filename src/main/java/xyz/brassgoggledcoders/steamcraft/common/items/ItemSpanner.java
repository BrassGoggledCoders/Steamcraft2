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

import boilerplate.api.IOpenableGUI;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.api.item.IUniversalWrench;
import xyz.brassgoggledcoders.steamcraft.api.tile.ISpannerTile;
import xyz.brassgoggledcoders.steamcraft.client.lib.GuiIDs;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemSpanner extends BaseItem implements IUniversalWrench
{
	public ItemSpanner()
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tile = world.getTileEntity(x, y, z);

			if (tile instanceof ISpannerTile)
			{
				ISpannerTile spannerTile = (ISpannerTile) tile;

				if (player.isSneaking() && tile instanceof IOpenableGUI)
					player.openGui(Steamcraft.instance, GuiIDs.PIPES, world, x, y, z);
				else
					spannerTile.changeExtraction();
			}
		}
		player.swingItem();
		return true;

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

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
