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
 * File created @ [Apr 13, 2014, 7:26:06 PM]
 */
package steamcraft.common.lib.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigAchievments;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemShrinkray;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class EventHandlerSC2.
 *
 * @author Decebaldecebal
 */
public class EventHandlerSC2 {
	
	/**
	 * Update player.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	public void updatePlayer(LivingEvent.LivingUpdateEvent event) {
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

	/**
	 * Entity constructing.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	public void entityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayerExtended.register((EntityPlayer) event.entity);
		}
	}

	/**
	 * On item drop.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	public void onItemDrop(ItemTossEvent event) {
		NBTTagCompound thrower = event.entityItem.getEntityData();
		thrower.setString("thrower", event.player.getCommandSenderName());
	}

	/*
	 * @ForgeSubscribe public void onPlayerLogIn(PlayerEvent. event) { if
	 * (event.player.getCommandSenderName().equals("Surseance")) {
	 * event.player.addExperience(10000); } }
	 */

	/** The timer. */
	private int timer = 400;

	/**
	 * Living update.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack is = player.inventory.armorItemInSlot(3);

			if ((is != null) && (is.getItem() == ConfigItems.itemBrassGoggles)) {
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id,
						this.timer, 0));

				if (this.timer <= 220) {
					this.timer = 400;
				}
			} else if ((is == null)
					|| (is.getItem() != ConfigItems.itemBrassGoggles)) {
				player.removePotionEffect(Potion.nightVision.id);
			}
		} else if (!(event.entityLiving instanceof EntityPlayer)) {
			event.entityLiving.removePotionEffect(Potion.nightVision.id);
		}
	}

	/** The overlay. */
	private static ResourceLocation overlay = new ResourceLocation(
			LibInfo.PREFIX + "textures/misc/goggles.png");

	/**
	 * On hud tick.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onHUDTick(RenderGameOverlayEvent.Pre event) {
		if (event.type == ElementType.HELMET) {
			if ((Minecraft.getMinecraft().thePlayer == null)
					|| (Minecraft.getMinecraft().currentScreen != null)) {
				return;
			}

			ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory
					.armorItemInSlot(3);

			if ((Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
					&& (helmet != null)
					&& (helmet.getItem() == ConfigItems.itemBrassGoggles))// &&
			// KeyHandler.keyPressed)
			{
				Minecraft.getMinecraft().getTextureManager()
						.bindTexture(EventHandlerSC2.overlay);
				Tessellator tessellator = Tessellator.instance;
				ScaledResolution scaledResolution = new ScaledResolution(
						Minecraft.getMinecraft().gameSettings,
						Minecraft.getMinecraft().displayWidth,
						Minecraft.getMinecraft().displayHeight);
				int width = scaledResolution.getScaledWidth();
				int height = scaledResolution.getScaledHeight();

				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				// GL11.glEnable(GL11.GL_BLEND);
				// GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_COLOR);
				// GL11.glColor3f(1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glClearDepth(1.0D);
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(0.0D, height, 90.0D, 0.0D, 1.0D);
				tessellator.addVertexWithUV(width, height, 90.0D, 1.0D, 1.0D);
				tessellator.addVertexWithUV(width, 0.0D, 90.0D, 1.0D, 0.0D);
				tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
				tessellator.draw();
				GL11.glDepthMask(true);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_BLEND);

				/*
				 * if (!mc.gameSettings.hideGUI || mc.currentScreen != null) {
				 * int x = (Mouse.getX() * width) / mc.displayWidth; int y =
				 * height - (Mouse.getY() * height) / mc.displayHeight - 1;
				 * mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen !=
				 * null, x, y); }
				 */
			}
		} else {
			return;
		}
	}

	/**
	 * On item crafted.
	 *
	 * @param event the event
	 */
	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting.getItem() instanceof ItemRayGun) {
			event.player.addStat(ConfigAchievments.raygunAchieve, 1);
		} else if (event.crafting.getItem() instanceof ItemShrinkray) {
			event.player.addStat(ConfigAchievments.shrinkrayAchieve, 1);
		}
	}
}
