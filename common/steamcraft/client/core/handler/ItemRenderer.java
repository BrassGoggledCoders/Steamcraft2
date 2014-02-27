package common.steamcraft.client.core.handler;

import common.steamcraft.common.lib2.LibInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderer implements IItemRenderer
{
	private static RenderItem renderItem = new RenderItem();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) 
	{
		 FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
         Icon icon = stack.getIconIndex();
         renderItem.renderIcon(0, 0, icon, 16, 16);
         GL11.glDisable(GL11.GL_TEXTURE_2D);
         GL11.glEnable(GL11.GL_BLEND);
         GL11.glDepthMask(false);
         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         Tessellator tessellator = Tessellator.instance;
         tessellator.startDrawing(GL11.GL_QUADS);
         tessellator.setColorRGBA(0, 0, 0, 128); // RGB and Alpha: values between 0 and 255
         tessellator.addVertex(0, 0, 0);
         tessellator.addVertex(0, 8, 0);
         tessellator.addVertex(8, 8, 0);
         tessellator.addVertex(8, 0, 0);
         tessellator.draw();
         GL11.glDepthMask(true);
         GL11.glDisable(GL11.GL_BLEND);
         GL11.glEnable(GL11.GL_TEXTURE_2D);
         String text = LibInfo.MOD_NAME;
         fontRenderer.drawStringWithShadow(text, 1, 1, 0xFFFFFF);
	}
}
