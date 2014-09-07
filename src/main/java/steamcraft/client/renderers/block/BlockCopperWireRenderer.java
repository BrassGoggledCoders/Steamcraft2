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
package steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileCopperWire;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * @author Surseance
 *
 */
public class BlockCopperWireRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		final TileCopperWire te = new TileCopperWire();
		
		for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++)
			te.connections[i] = ForgeDirection.VALID_DIRECTIONS[i];
		
		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(32826);
	}

	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelID,
			final RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(final int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderIDs.blockCopperWireRI;
	}
}
