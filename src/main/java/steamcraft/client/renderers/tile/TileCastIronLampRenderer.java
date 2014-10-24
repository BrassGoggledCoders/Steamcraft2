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

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelCastIronLampSide;
import steamcraft.client.renderers.models.ModelCastIronLampTop;
import steamcraft.common.InitBlocks;
import steamcraft.common.lib.LibInfo;

/**
 * @author Surseance
 * 
 */
public class TileCastIronLampRenderer extends TileEntitySpecialRenderer
{
	private final ModelCastIronLampTop lampModelTop;

	private final ModelCastIronLampSide lampModelSide;

	public TileCastIronLampRenderer()
	{
		this.lampModelTop = new ModelCastIronLampTop();
		this.lampModelSide = new ModelCastIronLampSide();
	}

	@Override
	public void renderTileEntityAt(final TileEntity te, final double dx, final double dy, final double dz, final float scale)
	{
		Block block = te.getBlockType();
		GL11.glPushMatrix();
		float f1 = 0.6666667F;
		int metadata = te.getBlockMetadata();
		float f3 = 0.0F;
		float f2 = 1.0F;

		if(metadata == 2)
		{
			f3 = 180F;
		}

		if(metadata == 4)
		{
			f3 = 90F;
		}

		if(metadata == 3)
		{
			f3 = -90F;
		}

		if(metadata == 6)
		{
			f2 = 180F;
		}

		GL11.glTranslatef((float) dx + 0.5F, (float) dy + (0.75F * f1), (float) dz + 0.5F);
		GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);

		if(metadata == 6)
		{
			GL11.glRotatef(f2, 0.0F, 0.0F, 1.0F);
		}

		GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);

		this.lampModelTop.bracketWide.showModel = true;
		this.lampModelTop.crossbarLeft.showModel = true;
		this.lampModelTop.crossbarRight.showModel = true;
		this.lampModelSide.crossbarLeft.showModel = true;
		this.lampModelSide.crossbarRight.showModel = true;

		// Renders the textures based on torch state
		ResourceLocation lampOn = (new ResourceLocation(LibInfo.PREFIX + "textures/models/lampon.png"));
		ResourceLocation lampOff = (new ResourceLocation(LibInfo.PREFIX + "textures/models/lampoff.png"));

		if(block == InitBlocks.blockCastIronLampOn)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(lampOn);
		}
		else if(block == InitBlocks.blockCastIronLamp)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(lampOff);
		}

		GL11.glPushMatrix();
		GL11.glScalef(f1, -f1, -f1);

		if((metadata == 5) || (metadata == 6))
		{
			this.lampModelTop.renderSign();
		}
		else
		{
			this.lampModelSide.renderSign();
		}

		GL11.glPopMatrix();
		float f4 = 0.01666667F * f1;
		GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
		GL11.glScalef(f4, -f4, f4);
		GL11.glNormal3f(0.0F, 0.0F, -1F * f4);
		GL11.glDepthMask(false);
		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static class TileCastIronLamp extends TileEntity
	{
		public TileCastIronLamp()
		{

		}
	}
}
