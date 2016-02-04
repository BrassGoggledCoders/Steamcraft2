
package steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author Surseance
 *
 */
public class BlockCopperPipeRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		TileCopperPipe te = new TileCopperPipe();

		System.arraycopy(ForgeDirection.VALID_DIRECTIONS, 0, te.connections, 0, ForgeDirection.VALID_DIRECTIONS.length);

		te.blockMetadata = metadata;

		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(32826);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderIDs.blockCopperPipeRI;
	}
}
