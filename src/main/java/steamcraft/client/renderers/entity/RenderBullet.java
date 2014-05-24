/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 3:32:46 PM]
 */
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.entities.projectile.EntityBullet;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class RenderBullet.
 *
 * @author Surseance (Johnny Eatmon)
 */
@SideOnly(Side.CLIENT)
public class RenderBullet extends Render
{
	
	/** The Constant bulletTex. */
	private static final ResourceLocation bulletTex = new ResourceLocation(
			LibInfo.PREFIX + "textures/projectiles/musketball.png");

	/**
	 * Render bullet.
	 *
	 * @param bullet the bullet
	 * @param dx the dx
	 * @param dy the dy
	 * @param dz the dz
	 * @param frotY the frot y
	 * @param frotP the frot p
	 */
	public void renderBullet(final EntityBullet bullet, final double dx,
			final double dy, final double dz, final float frotY,
			final float frotP)
	{
		bindEntityTexture(bullet);

		if (bullet.prevRotationYaw == 0.0F && bullet.prevRotationPitch == 0.0F)
		{
			return;
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx, (float) dy, (float) dz);
		GL11.glRotatef(
				(bullet.prevRotationYaw + (bullet.rotationYaw - bullet.prevRotationYaw)
						* frotP) - 90F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(bullet.prevRotationPitch
				+ (bullet.rotationPitch - bullet.prevRotationPitch) * frotP,
				0.0F, 0.0F, 1.0F);
		final Tessellator tessellator = Tessellator.instance;
		final int i = 0;
		final float f2 = 0.0F;
		final float f3 = 0.5F;
		final float f4 = (0 + i * 10) / 32F;
		final float f5 = (5 + i * 10) / 32F;
		final float f6 = 0.0F;
		final float f7 = 0.15625F;
		final float f8 = (5 + i * 10) / 32F;
		final float f9 = (10 + i * 10) / 32F;
		final float f10 = 0.05625F;
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		final float f11 = 1.0F - frotP;

		if (f11 > 0.0F)
		{
			final float f12 = -MathHelper.sin(f11 * 3F) * f11;
			GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
		}

		GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(f10, f10, f10);
		GL11.glTranslatef(-4F, 0.0F, 0.0F);
		GL11.glNormal3f(f10, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7D, -2D, -2D, f6, f8);
		tessellator.addVertexWithUV(-7D, -2D, 2D, f7, f8);
		tessellator.addVertexWithUV(-7D, 2D, 2D, f7, f9);
		tessellator.addVertexWithUV(-7D, 2D, -2D, f6, f9);
		tessellator.draw();
		GL11.glNormal3f(-f10, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7D, 2D, -2D, f6, f8);
		tessellator.addVertexWithUV(-7D, 2D, 2D, f7, f8);
		tessellator.addVertexWithUV(-7D, -2D, 2D, f7, f9);
		tessellator.addVertexWithUV(-7D, -2D, -2D, f6, f9);
		tessellator.draw();

		for (int j = 0; j < 4; j++)
		{
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			GL11.glNormal3f(0.0F, 0.0F, f10);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-8D, -2D, 0.0D, f2, f4);
			tessellator.addVertexWithUV(8D, -2D, 0.0D, f3, f4);
			tessellator.addVertexWithUV(8D, 2D, 0.0D, f3, f5);
			tessellator.addVertexWithUV(-8D, 2D, 0.0D, f2, f5);
			tessellator.draw();
		}

		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glPopMatrix();
	}

	/**
	 * Gets the bullet texture.
	 *
	 * @param bullet the bullet
	 * @return the bullet texture
	 */
	protected ResourceLocation getBulletTexture(final EntityBullet bullet)
	{
		return bulletTex;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.renderer.entity.Render#getEntityTexture(net.minecraft.entity.Entity)
	 */
	@Override
	protected ResourceLocation getEntityTexture(final Entity entity)
	{
		return getBulletTexture((EntityBullet) entity);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.renderer.entity.Render#doRender(net.minecraft.entity.Entity, double, double, double, float, float)
	 */
	@Override
	public void doRender(final Entity entity, final double dx, final double dy,
			final double dz, final float frotY, final float frotP)
	{
		renderBullet((EntityBullet) entity, dx, dy, dz, frotY, frotP);
	}
}
