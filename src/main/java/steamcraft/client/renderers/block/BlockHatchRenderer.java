
package steamcraft.client.renderers.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import steamcraft.client.lib.RenderIDs;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;

/**
 * @author Decebaldecebal
 *
 */
public class BlockHatchRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		TileHatch te = new TileHatch();

		te.blockType = block;
		te.blockMetadata = metadata;

		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);
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
		return RenderIDs.blockHatchRI;
	}
}
