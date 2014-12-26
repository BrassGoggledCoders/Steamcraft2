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
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.entities.EntityGrapplingHook;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrapplingHook extends Render
{
	private static final ResourceLocation bulletTex = new ResourceLocation(LibInfo.PREFIX + "textures/projectiles/musketball.png");

	public void renderBullet(EntityGrapplingHook entity, double dx, double dy, double dz, float frotY, float frotP)
	{
		this.bindEntityTexture(entity);

		if((entity.prevRotationYaw == 0.0F) && (entity.prevRotationPitch == 0.0F))
			return;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx, (float) dy, (float) dz);
		GL11.glRotatef((entity.prevRotationYaw + ((entity.rotationYaw - entity.prevRotationYaw) * frotP)) - 90F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.prevRotationPitch + ((entity.rotationPitch - entity.prevRotationPitch) * frotP), 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		int i = 0;
		float f2 = 0.0F;
		float f3 = 0.5F;
		float f4 = (0 + (i * 10)) / 32F;
		float f5 = (5 + (i * 10)) / 32F;
		float f6 = 0.0F;
		float f7 = 0.15625F;
		float f8 = (5 + (i * 10)) / 32F;
		float f9 = (10 + (i * 10)) / 32F;
		float f10 = 0.3F;
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		float f11 = 1.0F - frotP;

		if(f11 > 0.0F)
		{
			float f12 = -MathHelper.sin(f11 * 3F) * f11;
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

		for(int j = 0; j < 4; j++)
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

	protected ResourceLocation getBulletTexture(EntityGrapplingHook bullet)
	{
		return bulletTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getBulletTexture((EntityGrapplingHook) entity);
	}

	@Override
	public void doRender(Entity entity, double dx, double dy, double dz, float frotY, float frotP)
	{
		this.renderBullet((EntityGrapplingHook) entity, dx, dy, dz, frotY, frotP);
	}
}
