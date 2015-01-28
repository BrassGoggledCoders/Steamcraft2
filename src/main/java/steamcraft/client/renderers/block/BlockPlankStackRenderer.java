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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import steamcraft.client.lib.RenderIDs;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * @author Surseance
 * 
 */
public class BlockPlankStackRenderer implements ISimpleBlockRenderingHandler
{
	private static final ResourceLocation texture = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");

	private static float pixel = 1F / 16F / 2F;
	private static float tPixel = 1F / 32F;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glPushMatrix();

		// GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		Tessellator tess = Tessellator.instance;

		tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
		tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 0 * tPixel);
		tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 0 * tPixel);
		tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		this.renderInventoryBlock(block, world.getBlockMetadata(x, y, z), modelID, renderer);
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
		return RenderIDs.blockPlankStackRI;
	}
}
