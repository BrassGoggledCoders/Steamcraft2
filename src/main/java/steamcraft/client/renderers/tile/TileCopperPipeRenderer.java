
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author Decebaldecebal
 *
 */
public class TileCopperPipeRenderer extends TileEntitySpecialRenderer
{
	protected ResourceLocation texture = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockCopperPipe.png");

	private static float pixel = LibInfo.pixel;
	private static float tPixel = LibInfo.tPixel;

	@Override
	public void renderTileEntityAt(TileEntity tile, double transX, double transY, double transZ, float f)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);

		this.bindTexture(this.texture);

		TileCopperPipe pipe = (TileCopperPipe) tile;

		boolean isStraight = true;
		for (ForgeDirection dir : pipe.extractions)
			if (dir != null)
			{
				isStraight = false;
				break;
			}

		ForgeDirection opposite = null;

		if (isStraight && ((opposite = pipe.onlyOneOpposite()) != null) && pipe.isPipe(opposite) && pipe.isPipe(opposite.getOpposite()))
			this.drawStraightConnection(opposite, pipe);
		else
		{
			this.drawCore(pipe);

			for (int i = 0;i < 6;i++)
			{
				ForgeDirection dir = pipe.connections[i];
				if (dir != null)
					if (pipe.extractions[i] != null)
						this.drawAlternateConnection(dir, pipe);
					else
						this.drawConnection(dir, pipe);
			}
		}

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glTranslated(-transX, -transY, -transZ);

		GL11.glPopMatrix();
	}

	private void drawStraightConnection(ForgeDirection dir, TileCopperPipe pipe)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);

			if ((dir == ForgeDirection.SOUTH) || (dir == ForgeDirection.NORTH))
			{
				GL11.glRotatef(90, 1, 0, 0);
			}
			else if ((dir == ForgeDirection.WEST) || (dir == ForgeDirection.EAST))
			{
				GL11.glRotatef(90, 0, 0, 1);
			}

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			tess.addVertexWithUV(1 - (12 * pixel), 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 27 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 0, 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);

			tess.addVertexWithUV(12 * pixel, 0, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 27 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 0, 12 * pixel, 11 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(1 - (12 * pixel), 0, 12 * pixel, 11 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(12 * pixel, 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 0, 12 * pixel, 11 * tPixel, 0 * tPixel);

			if (ConfigGeneral.drawInside)
			{
				tess.addVertexWithUV(12 * pixel, 0, 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 27 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);

				tess.addVertexWithUV(1 - (12 * pixel), 0, 12 * pixel, 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 27 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 0, 12 * pixel, 11 * tPixel, 0 * tPixel);

				tess.addVertexWithUV(1 - (12 * pixel), 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 0, 12 * pixel, 11 * tPixel, 0 * tPixel);

				tess.addVertexWithUV(12 * pixel, 0, 12 * pixel, 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 27 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 27 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 0, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
			}
		}
		tess.draw();

		if (ConfigGeneral.drawFluid)
		{
			float level = pipe.fluidScaled;

			if ((pipe.fluidInPipe != null) && (level > 0))
			{
				IIcon icon = pipe.fluidInPipe.getIcon();

				if (icon == null)
					icon = pipe.fluidInPipe.getBlock().getIcon(0, 0);
				if (icon != null)
				{
					this.bindTexture(TextureMap.locationBlocksTexture);

					tess.startDrawingQuads();
					{

						tess.addVertexWithUV((1 - (15 * pixel)) + level, 0, (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 0, (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMinV());

						tess.addVertexWithUV((15 * pixel) - level, 0, (15 * pixel) - level, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 0, (15 * pixel) - level, icon.getMinU(), icon.getMaxV());

						tess.addVertexWithUV((1 - (15 * pixel)) + level, 0, (15 * pixel) - level, icon.getMinU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 0, (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());

						tess.addVertexWithUV((15 * pixel) - level, 0, (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 0, (15 * pixel) - level, icon.getMinU(), icon.getMinV());
					}
					tess.draw();

					this.bindTexture(this.texture);
				}
			}
		}

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		if ((dir == ForgeDirection.SOUTH) || (dir == ForgeDirection.NORTH))
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		else if ((dir == ForgeDirection.WEST) || (dir == ForgeDirection.EAST))
		{
			GL11.glRotatef(-90, 0, 0, 1);
		}

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawAlternateConnection(ForgeDirection dir, TileCopperPipe pipe)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if (dir == ForgeDirection.UP)
			{
				// ROTATE
			}
			else if (dir == ForgeDirection.DOWN)
			{
				GL11.glRotatef(180, 1, 0, 0);
			}
			else if (dir == ForgeDirection.SOUTH)
			{
				GL11.glRotatef(90, 1, 0, 0);
			}
			else if (dir == ForgeDirection.NORTH)
			{
				GL11.glRotatef(270, 1, 0, 0);
			}
			else if (dir == ForgeDirection.WEST)
			{
				GL11.glRotatef(90, 0, 0, 1);
			}
			else if (dir == ForgeDirection.EAST)
			{
				GL11.glRotatef(270, 0, 0, 1);
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
			tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);

			// The actual new connection
			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 8 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 7 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 14 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);

			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);

			tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
			tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);

			if (ConfigGeneral.drawInside)
			{
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);

				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);

				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}
		}
		tess.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == ForgeDirection.UP)
		{
			// ROTATE
		}
		else if (dir == ForgeDirection.DOWN)
		{
			GL11.glRotatef(-180, 1, 0, 0);
		}
		else if (dir == ForgeDirection.SOUTH)
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		else if (dir == ForgeDirection.NORTH)
		{
			GL11.glRotatef(-270, 1, 0, 0);
		}
		else if (dir == ForgeDirection.WEST)
		{
			GL11.glRotatef(-90, 0, 0, 1);
		}
		else if (dir == ForgeDirection.EAST)
		{
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawConnection(ForgeDirection dir, TileCopperPipe pipe)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if (dir == ForgeDirection.UP)
			{
				// ROTATE
			}
			else if (dir == ForgeDirection.DOWN)
			{
				GL11.glRotatef(180, 1, 0, 0);
			}
			else if (dir == ForgeDirection.SOUTH)
			{
				GL11.glRotatef(90, 1, 0, 0);
			}
			else if (dir == ForgeDirection.NORTH)
			{
				GL11.glRotatef(270, 1, 0, 0);
			}
			else if (dir == ForgeDirection.WEST)
			{
				GL11.glRotatef(90, 0, 0, 1);
			}
			else if (dir == ForgeDirection.EAST)
			{
				GL11.glRotatef(270, 0, 0, 1);
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			if ((pipe.blockType == null) || pipe.isPipe(dir))
			{
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

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);

					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1, 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1, 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}

			}
			else
			{
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);

				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);

				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);

				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);

					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);

					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 12 * pixel, 11 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (2 * pixel), 1 - (12 * pixel), 11 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}

				// The actual new connection
				tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 8 * tPixel, 14 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 7 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);

				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 7 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 7 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 14 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 14 * tPixel);

				tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 8 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 8 * tPixel, 8 * tPixel);

				tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 0 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 0 * tPixel, 8 * tPixel);

				tess.addVertexWithUV(10 * pixel, 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(10 * pixel, 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);

				tess.addVertexWithUV(1 - (10 * pixel), 1, 1 - (10 * pixel), 8 * tPixel, 8 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 1 - (10 * pixel), 8 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1 - (2 * pixel), 10 * pixel, 0 * tPixel, 10 * tPixel);
				tess.addVertexWithUV(1 - (10 * pixel), 1, 10 * pixel, 0 * tPixel, 8 * tPixel);
			}
		}
		tess.draw();

		if (ConfigGeneral.drawFluid)
		{
			float level = pipe.fluidScaled;

			if ((pipe.fluidInPipe != null) && (level > 0))
			{
				IIcon icon = pipe.fluidInPipe.getIcon();
				if (icon == null)
					icon = pipe.fluidInPipe.getBlock().getIcon(0, 0);
				if (icon != null)
				{
					this.bindTexture(TextureMap.locationBlocksTexture);

					tess.startDrawingQuads();
					{
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(),
								icon.getMaxV());
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
						tess.addVertexWithUV((1 - (15 * pixel)) + level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(),
								icon.getMaxV());

						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (1 - (15 * pixel)) + level, icon.getMinU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (1 - (15 * pixel)) + level, icon.getMaxU(), icon.getMaxV());
						tess.addVertexWithUV((15 * pixel) - level, 1, (15 * pixel) - level, icon.getMaxU(), icon.getMinV());
						tess.addVertexWithUV((15 * pixel) - level, 1 - (15 * pixel), (15 * pixel) - level, icon.getMinU(), icon.getMinV());
					}
					tess.draw();

					this.bindTexture(this.texture);
				}
			}
		}

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == ForgeDirection.UP)
		{
			// ROTATE
		}
		else if (dir == ForgeDirection.DOWN)
		{
			GL11.glRotatef(-180, 1, 0, 0);
		}
		else if (dir == ForgeDirection.SOUTH)
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		else if (dir == ForgeDirection.NORTH)
		{
			GL11.glRotatef(-270, 1, 0, 0);
		}
		else if (dir == ForgeDirection.WEST)
		{
			GL11.glRotatef(-90, 0, 0, 1);
		}
		else if (dir == ForgeDirection.EAST)
		{
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawCore(TileCopperPipe tile)
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

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}
			}

			if (tile.connections[1] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}
			}

			if (tile.connections[2] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				}
			}

			if (tile.connections[3] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}
			}

			if (tile.connections[4] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				}
			}

			if (tile.connections[5] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);

				if (ConfigGeneral.drawInside)
				{
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
					tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				}
			}
		}
		tess.draw();
	}
}