
package steamcraft.client.renderers.tile;

import org.lwjgl.opengl.GL11;

import boilerplate.client.utils.RenderUtils;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileCopperWire;

/**
 * @author Decebaldecebal
 *
 */
public class TileCopperWireRenderer extends TileEntitySpecialRenderer
{
	private static float pixel = LibInfo.pixel;
	private static float tPixel = LibInfo.tPixel;

	protected ResourceLocation texture = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockCopperWire.png");
	protected ResourceLocation texture1 = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockInsulatedCopperWire.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double transX, double transY, double transZ, float f)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);

		TileCopperWire wire = (TileCopperWire) tile;

		if (tile.getBlockMetadata() == 0)
			this.bindTexture(this.texture);
		else
			this.bindTexture(this.texture1);
		this.drawCore(wire);

		for (int i = 0; i < 6; i++)
		{

			ForgeDirection dir = wire.connections[i];
			if (dir != null)
			{
				if (wire.extractions[i] != null)
				{
					this.drawAlternateConnection(dir, wire);
				}
				else
					this.drawConnection(dir, wire);
			}
		}

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glTranslated(-transX, -transY, -transZ);

		GL11.glPopMatrix();
	}

	private void drawAlternateConnection(ForgeDirection dir, TileCopperWire wire)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			RenderUtils.doWireRenderRotation(dir);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

			// The actual new connection
			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 8 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 10 * pixel, 8 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 10 * pixel, 8 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);

			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 10 * pixel, 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 1 - (10 * pixel), 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (4 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (4 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
		}
		tess.draw();

		RenderUtils.doWireRenderNegRotation(dir);
	}

	private void drawConnection(ForgeDirection dir, TileCopperWire wire)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			RenderUtils.doWireRenderRotation(dir);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
		}
		tess.draw();

		RenderUtils.doWireRenderNegRotation(dir);
	}

	private void drawCore(TileCopperWire tile)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			if (tile.connections[0] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if (tile.connections[1] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if (tile.connections[2] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
			}

			if (tile.connections[3] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if (tile.connections[4] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
			}

			if (tile.connections[5] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}
		}
		tess.draw();
	}
}
