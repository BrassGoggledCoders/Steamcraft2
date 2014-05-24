/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/15/14, 14:11]
 */
package steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.tiles.TileCrystal;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCrystalRenderer.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCrystalRenderer implements ISimpleBlockRenderingHandler
{
	
	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler#renderInventoryBlock(net.minecraft.block.Block, int, int, net.minecraft.client.renderer.RenderBlocks)
	 */
	@Override
	public void renderInventoryBlock(final Block block, final int metadata,
			final int modelID, final RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		final TileCrystal te = new TileCrystal();
		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D,
				0.0D, 0.0D, 0.0F);
		GL11.glEnable(32826);
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler#renderWorldBlock(net.minecraft.world.IBlockAccess, int, int, int, net.minecraft.block.Block, int, net.minecraft.client.renderer.RenderBlocks)
	 */
	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x,
			final int y, final int z, final Block block, final int modelID,
			final RenderBlocks renderer)
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler#shouldRender3DInInventory(int)
	 */
	@Override
	public boolean shouldRender3DInInventory(final int i)
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler#getRenderId()
	 */
	@Override
	public int getRenderId()
	{
		return ConfigBlocks.blockCrystalRI;
	}
}
