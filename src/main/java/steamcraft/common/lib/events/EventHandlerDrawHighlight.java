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
 * File created @ [Apr 5, 2014, 2:40:25 PM]
 */
package steamcraft.common.lib.events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigItems;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EventHandlerDrawHighlight
{
	int blockID, x, y, z;

	@ForgeSubscribe(priority = EventPriority.HIGH)
	public void renderOverlay(RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		FontRenderer fontRenderer = mc.fontRenderer;
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		mc.entityRenderer.setupOverlayRendering();
		String text = "Name: ";// + this.mopBlock.getLocalizedName();
		int posX = 5;
		int posY = 5;
		int posY2 = 15;
		int color = 0xCCFF00;
		fontRenderer.drawString(text, posX, posY, color);
		fontRenderer.drawString("Block ID: " + this.blockID, posX, posY2, color);
	}

	@ForgeSubscribe
	public void onDrawBlockSelectionBox(DrawBlockHighlightEvent event)
	{
		if ((event.player.inventory.armorItemInSlot(3) != null) && (event.player.inventory.armorItemInSlot(3).getItem() == ConfigItems.itemBrassGoggles))
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}
		
		this.blockID = event.player.worldObj.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
	}

	private void drawSelectionBox(EntityPlayer player, MovingObjectPosition mop, int i, ItemStack is, float partialTicks)
	{	
		if ((i == 0) && (mop.typeOfHit == EnumMovingObjectType.TILE))
		{
			GL11.glEnable(GL11.GL_BLEND);
			//OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			int bid = player.worldObj.getBlockId(mop.blockX, mop.blockY, mop.blockZ);

			if (bid != 0)
			{
				Block block = Block.blocksList[bid];
				block.setBlockBoundsBasedOnState(player.worldObj, mop.blockX, mop.blockY, mop.blockZ);
				double dx = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
				double dy = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
				double dz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
				this.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(player.worldObj, mop.blockX, mop.blockY, mop.blockZ).expand((double)offset, (double)offset, (double)offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		if ((i == 0) && (mop.typeOfHit == EnumMovingObjectType.ENTITY))
		{
			GL11.glEnable(GL11.GL_BLEND);
			//OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			Entity entity = mop.entityHit;

			if (entity != null)
			{
				entity.setPosition(entity.posX, entity.posY, entity.posZ);
				double dx = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
				double dy = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
				double dz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
				this.drawOutlinedBoundingBox(entity.boundingBox.expand((double)offset, (double)offset, (double)offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	private void drawOutlinedBoundingBox(AxisAlignedBB aaBB) 
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.maxZ);
		tessellator.draw();
	}
}
