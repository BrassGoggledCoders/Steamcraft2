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
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileCopperTank;

/**
 * @author Decebaldecebal
 *
 */
public class TileCopperTankRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockCopperTank.png");

	private static float pixel = LibInfo.pixel;
	private static float tPixel = LibInfo.tPixel;

	@Override
	public void renderTileEntityAt(TileEntity tile, double transX, double transY, double transZ, float f)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);

		this.bindTexture(TileCopperTankRenderer.texture);

		TileCopperTank tank = (TileCopperTank) tile;

		this.drawTank(tank);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-transX, -transY, -transZ);

		GL11.glPopMatrix();
	}

	private void drawTank(TileCopperTank tank)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
				tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);
				tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 24 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 0 , 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);

				tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 24 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);

				tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 12 * tPixel, 16 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 0 * tPixel, 16 * tPixel);

				tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 12 * tPixel, 16 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 0 * tPixel, 16 * tPixel);

				tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 12 * tPixel, 16 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 0 * tPixel, 16 * tPixel);

				tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 12 * tPixel, 16 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 0 * tPixel, 16 * tPixel);

				if(ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 24 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);
					
					tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);
					tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 24 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);
					
					tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 0 * tPixel, 16 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 12 * tPixel, 16 * tPixel);
					
					tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 0 * tPixel, 16 * tPixel);
					tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 12 * tPixel, 16 * tPixel);
					
					tess.addVertexWithUV(4 * pixel, 0, 4 * pixel, 0 * tPixel, 16 * tPixel);
					tess.addVertexWithUV(4 * pixel, 1, 4 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 1, 1 - (4 * pixel), 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(4 * pixel, 0, 1 - (4 * pixel), 12 * tPixel, 16 * tPixel);
					
					tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 0 * tPixel, 16 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 1 - (4 * pixel), 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 1, 4 * pixel, 12 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (4 * pixel), 0, 4 * pixel, 12 * tPixel, 16 * tPixel);
				}
		}
		tess.draw();

		if(ConfigGeneral.drawFluid)
		{
			FluidTank ft = tank.tank;
			
			if((ft != null) && ft.getFluid() != null && tank.fluidScaled > 0)
			{
				float level = tank.fluidScaled;

				tess.startDrawingQuads();
				{
					IIcon icon = ft.getFluid().getFluid().getIcon();
					if (icon == null)
						icon = ft.getFluid().getFluid().getBlock().getIcon(0, 0);
					if (icon != null)
					{
						this.bindTexture(TextureMap.locationBlocksTexture);
	
						tess.addVertexWithUV(8 * pixel, 0, 1 - (8 * pixel), icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV(8 * pixel, 0, 8 * pixel, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV(1 - (8 * pixel), 0, 8 * pixel, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV(1 - (8 * pixel), 0 , 1 - (8 * pixel), icon.getMinU(), icon.getMaxV());
						
						tess.addVertexWithUV(1 - (8 * pixel), 16 * level, 1 - (8 * pixel), icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV(1 - (8 * pixel), 16 * level, 8 * pixel, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV(8 * pixel, 16 * level, 8 * pixel, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV(8 * pixel, 16 * level , 1 - (8 * pixel), icon.getMinU(), icon.getMaxV());
						
						/*
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMinV());
	
						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (15 * pixel) - level, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (15 * pixel) - level, icon.getMinU(), icon.getMaxV());
	
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (15 * pixel) - level, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
	
						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (15 * pixel) - level, icon.getMinU(), icon.getMinV());
						*/
					}
				}
				tess.draw();

				this.bindTexture(TileCopperTankRenderer.texture);
			}
		}
	}
}