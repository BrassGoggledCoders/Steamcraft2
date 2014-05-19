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
 * File created @ [Apr 4, 2014, 11:30:24 PM]
 */
package steamcraft.common.lib.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EventHandlerHUD
{
	private Minecraft mc = Minecraft.getMinecraft();
	private static final ResourceLocation overlay = new ResourceLocation(LibInfo.PREFIX + "textures/misc/goggles.png");

	@SubscribeEvent
	public void onHUDTick(RenderGameOverlayEvent.Pre event)
	{
		if (event.type == ElementType.HELMET) 
		{
			if (this.mc.thePlayer == null || this.mc.currentScreen != null)
			{
				return;
			} 

			ItemStack helmet = this.mc.thePlayer.inventory.armorItemInSlot(3);

			if (this.mc.gameSettings.thirdPersonView == 0 && helmet != null && helmet.getItem() == ConfigItems.itemBrassGoggles)// && KeyHandler.keyPressed)
			{
				this.mc.getTextureManager().bindTexture(this.overlay);
				Tessellator tessellator = Tessellator.instance;
				ScaledResolution scaledResolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
				int width = scaledResolution.getScaledWidth();
				int height = scaledResolution.getScaledHeight();

				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				//GL11.glEnable(GL11.GL_BLEND);
				//GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_COLOR);
				//GL11.glColor3f(1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glClearDepth(1.0D);
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(0.0D, (double)height, 90.0D, 0.0D, 1.0D);
				tessellator.addVertexWithUV((double)width, (double)height, 90.0D, 1.0D, 1.0D);
				tessellator.addVertexWithUV((double)width, 0.0D, 90.0D, 1.0D, 0.0D);
				tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
				tessellator.draw();
				GL11.glDepthMask(true);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_BLEND);

				/*
				if (!mc.gameSettings.hideGUI || mc.currentScreen != null)
				{
					int x = (Mouse.getX() * width) / mc.displayWidth;
					int y = height - (Mouse.getY() * height) / mc.displayHeight - 1;
					mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen != null, x, y);
				}*/
			}
		}
	}
}
