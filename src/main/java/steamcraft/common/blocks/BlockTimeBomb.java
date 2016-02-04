
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BaseContainerBlock;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileTimeBomb;

/**
 * @author warlordjones
 *
 */
public class BlockTimeBomb extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconEnd;

	public BlockTimeBomb(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileTimeBomb();
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileTimeBomb tile = (TileTimeBomb) world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.TIMEBOMB, world, par2, par3, par4);
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
		case 1:
			return this.iconEnd; // top
		default:
			return this.blockIcon; // sides
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon(ModInfo.PREFIX + "blockTimebomb");
		this.iconEnd = icon.registerIcon(ModInfo.PREFIX + "blockTimebombTop");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack)
	{
		if ((ent != null) && (ent instanceof EntityPlayer))
		{
			EntityPlayer player = (EntityPlayer) ent;
			LoggerSteamcraft.info("Time Bomb placed at X: " + Integer.toString(x) + " Y: " + Integer.toString(y) + " Z: " + Integer.toString(z)
					+ " by player: " + player.getCommandSenderName());
		}
	}

}
