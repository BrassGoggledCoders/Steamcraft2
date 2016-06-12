
package steamcraft.client.renderers.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.EmptyTiles;
import steamcraft.common.tiles.EmptyTiles.TileCastIronLamp;

/**
 * @author Surseance
 *
 */
public class BlockCastIronLampRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		EmptyTiles.TileCastIronLamp te = new TileCastIronLamp();
		te.blockMetadata = 0;
		te.blockType = block;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, te.xCoord, te.yCoord, te.zCoord, 0.0F);
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
		return RenderIDs.blockCastIronLampRI;
	}
}
