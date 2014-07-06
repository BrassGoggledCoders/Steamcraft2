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
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;

/**
 * @author Decebaldecebal
 * 
 */
public class TileCopperPipeRenderer extends TileEntitySpecialRenderer
{
	ResourceLocation texture = new ResourceLocation(LibInfo.PREFIX + "textures/blocks/blockCopperPipe.png");
	
	static float pixel = 1F/16F/2F;
	static float tPixel = 1F/32F;
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double transX, double transY, double transZ,  float f)
	{		
		GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		this.bindTexture(texture);
		
		drawCore(tile);

		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(-transX, -transY, -transZ);
	}
	
	public void drawCore(TileEntity tile)
	{
		Tessellator tess = Tessellator.instance;
		
		tess.startDrawingQuads();
		{
			tess.addVertexWithUV(1-8*pixel, 8*pixel, 1-8*pixel, 8*tPixel, 8*tPixel);
			tess.addVertexWithUV(1-8*pixel, 1-8*pixel, 1-8*pixel, 8*tPixel, 0*tPixel);
			tess.addVertexWithUV(8*pixel, 1-8*pixel, 1-8*pixel, 0*tPixel, 0*tPixel);
			tess.addVertexWithUV(8*pixel, 8*pixel, 1-8*pixel, 0*tPixel, 8*tPixel);
		}
		tess.draw();
	}
}
