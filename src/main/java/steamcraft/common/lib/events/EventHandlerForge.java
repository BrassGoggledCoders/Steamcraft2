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
package steamcraft.common.lib.events;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import org.lwjgl.opengl.GL11;

import steamcraft.common.InitItems;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.Config;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class EventHandlerForge
{
	@SubscribeEvent
	public void updatePlayer(LivingEvent.LivingUpdateEvent event)
	{
		/*
		 * if (event.entityLiving instanceof EntityPlayer) { EntityPlayer player
		 * = (EntityPlayer) event.entityLiving;
		 *
		 * ItemStack legsSlot = player.inventory.armorItemInSlot(1);
		 *
		 * if (legsSlot != null) { if (legsSlot.getItem() ==
		 * ConfigItems.itemLegBraces) { float distToFall = player.fallDistance;
		 *
		 * if (distToFall > 3.0F) { player.fallDistance = distToFall * 0.888F;
		 * legsSlot.damageItem(1, player); } } }
		 *
		 * ItemStack bootsSlot = player.inventory.armorItemInSlot(0);
		 *
		 * if (bootsSlot != null) { if (!player.isInWater() && player.onGround
		 * && bootsSlot.getItem() == ConfigItems.itemRollerSkates) {
		 * player.moveEntityWithHeading(player.moveStrafing, player.moveForward
		 * * 0.8F); player.jumpMovementFactor = 0.03F; player.stepHeight = 0.0F;
		 * } } else if (bootsSlot == null || bootsSlot.getItem() !=
		 * ConfigItems.itemRollerSkates) { player.stepHeight = 0.5F; } }
		 */
	}


	@SubscribeEvent
	public void entityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
			EntityPlayerExtended.register((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void onItemDrop(ItemTossEvent event)
	{
		NBTTagCompound thrower = event.entityItem.getEntityData();
		thrower.setString("thrower", event.player.getCommandSenderName());
	}

	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack is = player.inventory.armorItemInSlot(3);

			if ((is != null) && (is.getItem() == InitItems.brassGoggles))
			{
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 0, true));
			}
		}
	}
	Block block;
	Entity entity;
	int x, y, z;

	@SubscribeEvent(priority = EventPriority.HIGH)
	@SideOnly(Side.CLIENT)
	public void renderOverlay(RenderGameOverlayEvent.Text event)
	{
		//if (event.player.inventory.armorItemInSlot(3) != null && event.player.inventory.armorItemInSlot(3).getItem() == ConfigItems.brassGoggles)
		//{
			Minecraft mc = Minecraft.getMinecraft();
			ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);
			if(helmet != null && helmet.getItem() == InitItems.itemMonocle)
			{
				ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				FontRenderer fontRenderer = mc.fontRenderer;
				res.getScaledWidth();
				res.getScaledHeight();
				mc.entityRenderer.setupOverlayRendering();
				//String text = "Name: " + this.block.getLocalizedName().substring(5);
				int posX = 5;
				int posY = 5;
				int posY2 = 15;
				int posY3 = 25;
				int posY4 = 35;
				//int posY5 = 45;
				int color = 0xCCFF00;
				//fontRenderer.drawString(text, posX, posY, color);
				if(block != null && block != Blocks.air)
				{
					fontRenderer.drawString("Block: " + this.block.getUnlocalizedName().substring(5), posX, posY, color);
					fontRenderer.drawString("Metadata: " + this.block.getDamageValue(mc.theWorld, x, y, z), posX, posY2, color);
					fontRenderer.drawString("Hardness: " + this.block.getBlockHardness(mc.theWorld, x, y, z), posX, posY3, color);
					fontRenderer.drawString("Light Value: " + this.block.getLightValue(), posX, posY4, color);
				}
				if(entity != null)
				{
					String text = entity.getCommandSenderName();
					fontRenderer.drawString("Entity: ", mc.displayWidth - 5 - text.length(), posY, color);
					String text1 = Integer.toString(entity.getEntityId());
					fontRenderer.drawString("Entity: ", mc.displayWidth - 5 - text1.length(), posY, color);
				}
				//fontRenderer.drawString("Material: " + this.block.getMaterial(), posX, posY5, color);
			}
		//}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onDrawBlockSelectionBox(DrawBlockHighlightEvent event)
	{
		if (event.player.inventory.armorItemInSlot(3) != null && event.player.inventory.armorItemInSlot(3).getItem() == InitItems.brassGoggles)
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}
		else if(event.player.inventory.armorItemInSlot(3) != null && event.player.inventory.armorItemInSlot(3).getItem() == InitItems.itemMonocle)
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}

		/*Minecraft mc = Minecraft.getMinecraft();
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
		GL11.glEnd();*/

		this.block = event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
		this.entity = event.player;
	}

	private void drawSelectionBox(EntityPlayer player, MovingObjectPosition mop, int i, ItemStack is, float partialTicks)
	{
		if (i == 0 && mop.typeOfHit == MovingObjectType.BLOCK)
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
				double dx = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
				double dy = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
				double dz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
				this.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(player.worldObj, mop.blockX, mop.blockY, mop.blockZ)
						.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		if (i == 0 && mop.typeOfHit == MovingObjectType.ENTITY)
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
				double dx = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
				double dy = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
				double dz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
				this.drawOutlinedBoundingBox(entity.boundingBox.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
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
	   @SubscribeEvent
	    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
	        if(eventArgs.modID.equals(LibInfo.ID))
	            Config.initialise(Steamcraft.configPath);
	    }
	   @SubscribeEvent
		public void onLivingSpawn(LivingSpawnEvent event)
		{
			double chance = event.world.rand.nextDouble();
			int armorType = event.world.rand.nextInt(2);

			if(chance < 0.2)
			{
				if(event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton)
				{
					int sword = event.world.rand.nextInt(100);
					int helmet = event.world.rand.nextInt(100);
					int chestplate = event.world.rand.nextInt(100);
					int leggings = event.world.rand.nextInt(100);
					int boots = event.world.rand.nextInt(100);

					if(armorType == 0)
					{
						if(event.entityLiving instanceof EntityZombie && sword < 50) event.entityLiving.setCurrentItemOrArmor(0, new ItemStack(InitItems.swordEtherium));
						if(helmet < 50) event.entityLiving.setCurrentItemOrArmor(1, new ItemStack(InitItems.helmetEtherium));
						if(chestplate < 50) event.entityLiving.setCurrentItemOrArmor(2, new ItemStack(InitItems.chestplateEtherium));
						if(leggings < 50) event.entityLiving.setCurrentItemOrArmor(3, new ItemStack(InitItems.legsEtherium));
						if(boots < 50) event.entityLiving.setCurrentItemOrArmor(4, new ItemStack(InitItems.bootsEtherium));
					}
					else if(armorType == 1)
					{
						if(event.entityLiving instanceof EntityZombie && sword < 50) event.entityLiving.setCurrentItemOrArmor(0, new ItemStack(InitItems.swordObsidian));
						if(helmet < 50) event.entityLiving.setCurrentItemOrArmor(1, new ItemStack(InitItems.helmetObsidian));
						if(chestplate < 50) event.entityLiving.setCurrentItemOrArmor(2, new ItemStack(InitItems.chestplateObsidian));
						if(leggings < 50) event.entityLiving.setCurrentItemOrArmor(3, new ItemStack(InitItems.legsObsidian));
						if(boots < 50) event.entityLiving.setCurrentItemOrArmor(4, new ItemStack(InitItems.bootsObsidian));
					}
				}
			}
		}
}
