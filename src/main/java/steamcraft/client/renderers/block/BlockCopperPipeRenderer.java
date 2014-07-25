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

import org.lwjgl.opengl.GL11;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileCopperPipe;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCrystalRenderer.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCopperPipeRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelID, final RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(final int i)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return RenderIDs.blockCopperPipeRI;
	}

}
