
package steamcraft.common.blocks.machines;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileLightningRod;

public class BlockLightningRod extends BaseContainerBlock
{

	public BlockLightningRod(Material mat)
	{
		super(mat);
		this.setBlockBounds(0.3F, 0, 0.3F, 0.7F, 2.0F, 0.7F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileLightningRod();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockLightningRodRI;
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
