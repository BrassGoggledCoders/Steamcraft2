package common.steamcraft.client.core.handler;

import common.steamcraft.client.lib2.ClientResources;
import common.steamcraft.common.item.ModArmors;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.EnumSet;

public class HUDHandler implements ITickHandler
{
	private static final ResourceLocation overlay = new ResourceLocation(ClientResources.PREFIX_MOD, "/textures/misc/goggles.png");

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		Minecraft mc = Minecraft.getMinecraft();

		if(mc.thePlayer == null || mc.currentScreen != null)
		{
			return;
		} 

		ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);

		if(mc.gameSettings.thirdPersonView == 0 && helmet != null && helmet.itemID == ModArmors.brassGoggles.itemID)// && SC_KeyHandler.keyPressed)
		{
			mc.getTextureManager().bindTexture(this.overlay);
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scale = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
			int width = scale.getScaledWidth();
			int height = scale.getScaledHeight();
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // Does cool effects with the brightness
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // Changes the rendering color; values must be between 0.0F-1.0F
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glClearDepth(1.0);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0.0D, (double)height, 90.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV((double)width, (double)height, 90.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV((double)width, 0.0D, 90.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
			tessellator.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopAttrib();

			if(!mc.gameSettings.hideGUI || mc.currentScreen != null)
			{
				int k = (Mouse.getX() * width) / mc.displayWidth;
				int l = height - (Mouse.getY() * height) / mc.displayHeight - 1;
				mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen != null, k, l);
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		return "SC_HUDHandler";
	}
}