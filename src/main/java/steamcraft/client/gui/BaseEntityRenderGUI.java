
package steamcraft.client.gui;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;

import boilerplate.client.BaseContainerGui;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by Skylar on 8/29/2015.
 */
public abstract class BaseEntityRenderGUI extends BaseContainerGui
{
	public BaseEntityRenderGUI(Container container)
	{
		super(container);
	}

	public static void renderEntity(int startX, int startY, int scale, float offsetX, float offsetY, EntityLivingBase entityLiving)
	{
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(startX, startY, 50.0F);
		GL11.glScalef((-scale), scale, scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = entityLiving.renderYawOffset;
		float f3 = entityLiving.rotationYaw;
		float f4 = entityLiving.rotationPitch;
		float f5 = entityLiving.prevRotationYawHead;
		float f6 = entityLiving.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan(offsetY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
		entityLiving.renderYawOffset = (float) Math.atan(offsetX / 40.0F) * 20.0F;
		entityLiving.rotationYaw = (float) Math.atan(offsetX / 40.0F) * 40.0F;
		entityLiving.rotationPitch = -((float) Math.atan(offsetY / 40.0F)) * 20.0F;
		entityLiving.rotationYawHead = entityLiving.rotationYaw;
		entityLiving.prevRotationYawHead = entityLiving.rotationYaw;
		GL11.glTranslatef(0.0F, entityLiving.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(entityLiving, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		entityLiving.renderYawOffset = f2;
		entityLiving.rotationYaw = f3;
		entityLiving.rotationPitch = f4;
		entityLiving.prevRotationYawHead = f5;
		entityLiving.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
