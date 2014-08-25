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
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileTeslaCoil;

/**
 * @author warlordjones
 * 
 */
public class BlockTeslaCoil extends BaseContainerBlock
{
	public BlockTeslaCoil(Material mat)
	{
		super(mat);
		// setBlockBounds(0.3F, 0, 0.3F, 0.7F, 2.0F, 0.7F);
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
}
