
package steamcraft.client.renderers.tile;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;
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
		if (ConfigGeneral.drawFluid)
			this.drawFluid(tank);

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
			tess.addVertexWithUV(1 - (4 * pixel), 0, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);

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

			if (ConfigGeneral.drawInside)
			{
				tess.addVertexWithUV(1 - (4 * pixel), pixel, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), pixel, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, pixel, 4 * pixel, 24 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(4 * pixel, pixel, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);

				tess.addVertexWithUV(4 * pixel, 1 - pixel, 1 - (4 * pixel), 12 * tPixel, 12 * tPixel);
				tess.addVertexWithUV(4 * pixel, 1 - pixel, 4 * pixel, 12 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1 - pixel, 4 * pixel, 24 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (4 * pixel), 1 - pixel, 1 - (4 * pixel), 24 * tPixel, 12 * tPixel);

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
	}

	private void drawFluid(TileCopperTank tank)
	{
		FluidTank ft = tank.tank;

		if ((ft != null) && (ft.getFluid() != null) && (tank.fluidScaled > 0))
		{
			float level = tank.fluidScaled;
			IIcon icon = ft.getFluid().getFluid().getIcon();

			if (icon == null)
				icon = ft.getFluid().getFluid().getBlock().getIcon(0, 0);
			if (icon != null)
			{
				Tessellator tess = Tessellator.instance;
				this.bindTexture(TextureMap.locationBlocksTexture);

				tess.startDrawingQuads();
				{
					tess.addVertexWithUV(6 * pixel, pixel, 1 - (6 * pixel), icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(6 * pixel, pixel, 6 * pixel, icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel, 6 * pixel, icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel, 1 - (6 * pixel), icon.getMinU(), icon.getMaxV());

					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 1 - (6 * pixel), icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 6 * pixel, icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 6 * pixel, icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 1 - (6 * pixel), icon.getMinU(), icon.getMaxV());

					tess.addVertexWithUV(6 * pixel, pixel, 6 * pixel, icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 6 * pixel, icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 6 * pixel, icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel, 6 * pixel, icon.getMinU(), icon.getMaxV());

					tess.addVertexWithUV(1 - (6 * pixel), pixel, 1 - (6 * pixel), icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 1 - (6 * pixel), icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 1 - (6 * pixel), icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel, 1 - (6 * pixel), icon.getMinU(), icon.getMaxV());

					tess.addVertexWithUV(6 * pixel, pixel, 1 - (6 * pixel), icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 1 - (6 * pixel), icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel + (15 * level), 6 * pixel, icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(6 * pixel, pixel, 6 * pixel, icon.getMinU(), icon.getMaxV());

					tess.addVertexWithUV(1 - (6 * pixel), pixel, 6 * pixel, icon.getMaxU(), icon.getMaxV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 6 * pixel, icon.getMaxU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel + (15 * level), 1 - (6 * pixel), icon.getMinU(), icon.getMinV());
					tess.addVertexWithUV(1 - (6 * pixel), pixel, 1 - (6 * pixel), icon.getMinU(), icon.getMaxV());
				}
				tess.draw();

				this.bindTexture(TileCopperTankRenderer.texture);
			}
		}
	}
}