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
package xyz.brassgoggledcoders.steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;
import xyz.brassgoggledcoders.steamcraft.client.lib.GuiIDs;
import xyz.brassgoggledcoders.steamcraft.client.lib.RenderIDs;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.init.InitBlocks;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileBattery;

/**
 * @author decebaldecebal
 *
 */
public class BlockBattery extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockBattery()
	{
		super(Material.iron);
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockBatteryRI;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileBattery tile = (TileBattery) world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.BATTERY, world, par2, par3, par4);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileBattery();
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5)
	{
		TileBattery tile = (TileBattery) world.getTileEntity(par2, par3, par4);
		return Math.min(tile.getEnergyStored(ForgeDirection.UNKNOWN) / 10000, 15);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "/metal/blockCastIron");
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return InitBlocks.blockMetal.getIcon(0, 7);
	}
}
