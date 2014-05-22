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

import steamcraft.common.config.ConfigItems;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Decebaldecebal
 *
 */
public class EventHandlerSC2
{
	@SubscribeEvent
	public void updatePlayer(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

            ItemStack legsSlot = player.inventory.armorItemInSlot(1);

            if (legsSlot != null) {
                if (legsSlot.getItem() == ConfigItems.itemLegBraces) {
                    float distToFall = player.fallDistance;

                    if (distToFall > 3.0F) {
                        player.fallDistance = distToFall * 0.888F;
                        legsSlot.damageItem(1, player);
                    }
                }
            }

			ItemStack bootsSlot = player.inventory.armorItemInSlot(0);

			if (bootsSlot != null) {
				if (!player.isInWater() && player.onGround && bootsSlot.getItem() == ConfigItems.itemRollerSkates) {	
					player.moveEntityWithHeading(player.moveStrafing, player.moveForward * 0.8F);
					player.jumpMovementFactor = 0.03F;
					player.stepHeight = 0.0F;
				}
			} else if (bootsSlot == null || bootsSlot.getItem() != ConfigItems.itemRollerSkates) {
				player.stepHeight = 0.5F;
			}
		}
	}

	/*
	@SubscribeEvent
	public void onPlayerLogIn(PlayerEvent. event)
	{
		if (event.player.getCommandSenderName().equals("Surseance"))
		{
			event.player.addExperience(10000);
		}
    }*/

	@SubscribeEvent
	public void entityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayerExtended.register((EntityPlayer)event.entity);
		}
	}
	
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
				this.mc.getTextureManager().bindTexture(EventHandlerSC2.overlay);
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
				if (!mc.gameSettings.hideGUI || mc.currentScreen != null)
				{
					int x = (Mouse.getX() * width) / mc.displayWidth;
					int y = height - (Mouse.getY() * height) / mc.displayHeight - 1;
					mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen != null, x, y);
				}*/
			}
		}
		else
		{
			return;
		}
	}
private int timer = 400;

@SubscribeEvent
public void onItemDrop(ItemTossEvent event)
{
	NBTTagCompound thrower = event.entityItem.getEntityData();
	thrower.setString("thrower", event.player.getCommandSenderName());
}

/*
@ForgeSubscribe
public void onPlayerLogIn(PlayerEvent. event)
{
	if (event.player.getCommandSenderName().equals("Surseance"))
	{
		event.player.addExperience(10000);
	}
}*/

@SubscribeEvent
public void livingUpdate(LivingUpdateEvent event)
{
	if (event.entityLiving instanceof EntityPlayer)
	{
		EntityPlayer player = (EntityPlayer)event.entityLiving;
		ItemStack is = player.inventory.armorItemInSlot(3);

		if ((is != null) && is.getItem() == ConfigItems.itemBrassGoggles)
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, this.timer, 0));
			
			if (this.timer <= 220)
			{
				this.timer = 400;
			}
		}
		else if ((is == null) || (is.getItem() != ConfigItems.itemBrassGoggles))
		{
			player.removePotionEffect(Potion.nightVision.id);
		}
	}
	else if (!(event.entityLiving instanceof EntityPlayer))
	{
		event.entityLiving.removePotionEffect(Potion.nightVision.id);
	}
}
}
