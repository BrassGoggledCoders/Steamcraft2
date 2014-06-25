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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class EventHandlerDrawHighlight.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class EventHandlerDrawHighlight
{

	/** The block. */
	Block block;

	/** The z. */
	int x, y, z;

	/**
	 * Render overlay.
	 * 
	 * @param event
	 *            the event
	 */
	@SubscribeEvent(priority = EventPriority.HIGH)
	@SideOnly(Side.CLIENT)
	public void renderOverlay(RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		FontRenderer fontRenderer = mc.fontRenderer;
		res.getScaledWidth();
		res.getScaledHeight();
		mc.entityRenderer.setupOverlayRendering();
		String text = "Name: " + this.block.getLocalizedName();
		int posX = 5;
		int posY = 5;
		int posY2 = 15;
		int color = 0xCCFF00;
		fontRenderer.drawString(text, posX, posY, color);
		fontRenderer.drawString("Block: " + this.block.getUnlocalizedName(), posX, posY2, color);
	}

	/**
	 * On draw block selection box.
	 * 
	 * @param event
	 *            the event
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onDrawBlockSelectionBox(DrawBlockHighlightEvent event)
	{
		if ((event.player.inventory.armorItemInSlot(3) != null)
				&& (event.player.inventory.armorItemInSlot(3).getItem() == ConfigItems.brassGoggles))
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}

		Minecraft mc = Minecraft.getMinecraft();
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-1D, 1.0D, -1D, 1.0D, 1.0D, 40D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
		GL11.glClear(17664);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0.0F, 1.0F);
		GL11.glVertex3f(-1F, 1.0F, -1F);
		GL11.glTexCoord2f(0.0F, 0.0F);
		GL11.glVertex3f(-1F, -1F, -1F);
		GL11.glTexCoord2f(1.0F, 0.0F);
		GL11.glVertex3f(1.0F, -1F, -1F);
		GL11.glTexCoord2f(1.0F, 1.0F);
		GL11.glVertex3f(1.0F, 1.0F, -1F);
		GL11.glEnd();

		this.block = event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
	}

	/**
	 * Draw selection box.
	 * 
	 * @param player
	 *            the player
	 * @param mop
	 *            the mop
	 * @param i
	 *            the i
	 * @param is
	 *            the is
	 * @param partialTicks
	 *            the partial ticks
	 */
	private void drawSelectionBox(EntityPlayer player, MovingObjectPosition mop, int i, ItemStack is, float partialTicks)
	{
		if ((i == 0) && (mop.typeOfHit == MovingObjectType.BLOCK))
		{
			GL11.glEnable(GL11.GL_BLEND);
			// OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			Block block = player.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);

			if (block != Blocks.air)
			{
				block.setBlockBoundsBasedOnState(player.worldObj, mop.blockX, mop.blockY, mop.blockZ);
				double dx = player.lastTickPosX + ((player.posX - player.lastTickPosX) * partialTicks);
				double dy = player.lastTickPosY + ((player.posY - player.lastTickPosY) * partialTicks);
				double dz = player.lastTickPosZ + ((player.posZ - player.lastTickPosZ) * partialTicks);
				this.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(player.worldObj, mop.blockX, mop.blockY, mop.blockZ)
						.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		if ((i == 0) && (mop.typeOfHit == MovingObjectType.ENTITY))
		{
			GL11.glEnable(GL11.GL_BLEND);
			// OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			Entity entity = mop.entityHit;

			if (entity != null)
			{
				entity.setPosition(entity.posX, entity.posY, entity.posZ);
				double dx = player.lastTickPosX + ((player.posX - player.lastTickPosX) * partialTicks);
				double dy = player.lastTickPosY + ((player.posY - player.lastTickPosY) * partialTicks);
				double dz = player.lastTickPosZ + ((player.posZ - player.lastTickPosZ) * partialTicks);
				this.drawOutlinedBoundingBox(entity.boundingBox.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	/**
	 * Draw outlined bounding box.
	 * 
	 * @param aaBB
	 *            the aa bb
	 */
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
