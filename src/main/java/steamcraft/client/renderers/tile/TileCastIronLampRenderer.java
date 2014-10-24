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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelCastIronLampSide;
import steamcraft.client.renderers.models.ModelCastIronLampTop;
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
		final int metadata = te.getBlockMetadata();
		float rot = 0.0F;
		if(metadata == 0)
			rot = 180.0F;
		if(metadata == 1)
			rot = 180.0F;
		if(metadata == 2 || metadata == 12)
			rot = 180.0F;
		if(metadata == 4 || metadata == 14)
			rot = 90.0F;
		if(metadata == 3 || metadata == 13)
			rot = -90.0F;
		if(metadata == 6 || metadata == 16)
			rot = 180.0F;
		GL11.glPushMatrix();
		final float height = 0.6666667F;
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + (0.75F * height), (float) dz + 0.5F);
		GL11.glRotatef(rot, 0.0F, 1.0F,
				0.0F);
		if(metadata == 6 || metadata == 12)
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
		final ResourceLocation tex = (new ResourceLocation(LibInfo.PREFIX.replace(":", ""), "textures/models/lampon.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(tex);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.lampModelTop.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.lampModelSide.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public static class TileCastIronLamp extends TileEntity
	{
		public TileCastIronLamp()
		{

		}
	}
}
