
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileTeslaCoil;

/**
 * @author warlordjones
 *
 */
public class BlockTeslaCoil extends BaseContainerBlock
{
	public BlockTeslaCoil(Material mat)
	{
		super(mat);
		this.setBlockBounds(0.2F, 0, 0.2F, 0.8F, 1.3F, 0.8F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileTeslaCoil();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockTeslaCoilRI;
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "/metal/blockCastIron");
	}
}
