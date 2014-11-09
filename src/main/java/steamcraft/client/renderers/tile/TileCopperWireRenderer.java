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
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCopperWire;

/**
 * @author Decebaldecebal
 * 
 */
public class TileCopperWireRenderer extends TileEntitySpecialRenderer
{
	ResourceLocation texture = new ResourceLocation(LibInfo.PREFIX + "textures/blocks/blockCopperWire.png");
	ResourceLocation texture1 = new ResourceLocation(LibInfo.PREFIX + "textures/blocks/blockInsulatedWire.png");

	public static float pixel = 1F / 16F / 2F;
	public static float tPixel = 1F / 32F;

	@Override
	public void renderTileEntityAt(TileEntity tile, double transX, double transY, double transZ, float f)
	{
		GL11.glPushMatrix();

		GL11.glTranslated(transX, transY, transZ);
		GL11.glDisable(GL11.GL_LIGHTING);

		TileCopperWire wire = (TileCopperWire) tile;

		if(tile.getBlockMetadata() == 0)
			this.bindTexture(this.texture);
		else
			this.bindTexture(this.texture1);
		this.drawCore(wire);

		for(ForgeDirection dir : wire.connections)
			if(dir != null)
				if(wire.extract == dir)
					this.drawAlternateConnection(dir, wire);
				else
					this.drawConnection(dir, wire);

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glTranslated(-transX, -transY + -3F, -transZ);

		GL11.glPopMatrix();
	}

	private void drawAlternateConnection(ForgeDirection dir, TileCopperWire wire)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if(dir == ForgeDirection.UP)
			{
				// ROTATE
			}
			else if(dir == ForgeDirection.DOWN)
				GL11.glRotatef(180, 1, 0, 0);
			else if(dir == ForgeDirection.SOUTH)
				GL11.glRotatef(90, 1, 0, 0);
			else if(dir == ForgeDirection.NORTH)
				GL11.glRotatef(270, 1, 0, 0);
			else if(dir == ForgeDirection.WEST)
				GL11.glRotatef(90, 0, 0, 1);
			else if(dir == ForgeDirection.EAST)
				GL11.glRotatef(270, 0, 0, 1);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

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

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(dir == ForgeDirection.UP)
		{
			// ROTATE
		}
		else if(dir == ForgeDirection.DOWN)
			GL11.glRotatef(-180, 1, 0, 0);
		else if(dir == ForgeDirection.SOUTH)
			GL11.glRotatef(-90, 1, 0, 0);
		else if(dir == ForgeDirection.NORTH)
			GL11.glRotatef(-270, 1, 0, 0);
		else if(dir == ForgeDirection.WEST)
			GL11.glRotatef(-90, 0, 0, 1);
		else if(dir == ForgeDirection.EAST)
			GL11.glRotatef(-270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawConnection(ForgeDirection dir, TileCopperWire wire)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if(dir == ForgeDirection.UP)
			{
				// ROTATE
			}
			else if(dir == ForgeDirection.DOWN)
				GL11.glRotatef(180, 1, 0, 0);
			else if(dir == ForgeDirection.SOUTH)
				GL11.glRotatef(90, 1, 0, 0);
			else if(dir == ForgeDirection.NORTH)
				GL11.glRotatef(270, 1, 0, 0);
			else if(dir == ForgeDirection.WEST)
				GL11.glRotatef(90, 0, 0, 1);
			else if(dir == ForgeDirection.EAST)
				GL11.glRotatef(270, 0, 0, 1);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

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

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(dir == ForgeDirection.UP)
		{
			// ROTATE
		}
		else if(dir == ForgeDirection.DOWN)
			GL11.glRotatef(-180, 1, 0, 0);
		else if(dir == ForgeDirection.SOUTH)
			GL11.glRotatef(-90, 1, 0, 0);
		else if(dir == ForgeDirection.NORTH)
			GL11.glRotatef(-270, 1, 0, 0);
		else if(dir == ForgeDirection.WEST)
			GL11.glRotatef(-90, 0, 0, 1);
		else if(dir == ForgeDirection.EAST)
			GL11.glRotatef(-270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawCore(TileCopperWire tile)
	{
		Tessellator tess = Tessellator.instance;

		tess.startDrawingQuads();
		{
			if(tile.connections[0] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if(tile.connections[1] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if(tile.connections[2] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
			}

			if(tile.connections[3] == null)
			{
				tess.addVertexWithUV(1 - (12 * pixel), 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(1 - (12 * pixel), 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 0 * tPixel, 6 * tPixel);
			}

			if(tile.connections[4] == null)
			{
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 1 - (12 * pixel), 6 * tPixel, 6 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 1 - (12 * pixel), 6 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 1 - (12 * pixel), 12 * pixel, 0 * tPixel, 0 * tPixel);
				tess.addVertexWithUV(12 * pixel, 12 * pixel, 12 * pixel, 0 * tPixel, 6 * tPixel);
			}

			if(tile.connections[5] == null)
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
