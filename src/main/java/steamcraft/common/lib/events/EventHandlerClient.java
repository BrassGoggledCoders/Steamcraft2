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

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import org.lwjgl.opengl.GL11;
import steamcraft.api.vanity.IVanityItem;
import steamcraft.client.ClientProxy;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import steamcraft.common.init.InitPackets;
import steamcraft.common.packets.OpenContainerFromClientPacket;
import boilerplate.client.ClientHelper;

public class EventHandlerClient
{

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void zoom(FOVUpdateEvent event)
	{
		if((event.entity.inventory.getCurrentItem() != null) && (event.entity.inventory.getCurrentItem().getItem() == InitItems.itemSpyglass))
		{
			event.newfov = 0.3F;
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(KeyInputEvent event)
	{
		HashMap<String, KeyBinding> keyBindings = ClientProxy.keyBindings;

		if(keyBindings.get("vanity").isPressed())
		{
			EntityPlayer player = ClientHelper.player();
			InitPackets.network.sendToServer(new OpenContainerFromClientPacket(player.getEntityId(), GuiIDs.VANITY, player.dimension));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(receiveCanceled = true)
	public void onPlayerRender(RenderPlayerEvent.Post.Specials event)
	{
		EntityPlayerExtended props = ((EntityPlayerExtended) event.entityPlayer.getExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME));
		InventoryVanity inventory = props.getInventory();
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if(inventory.getStackInSlot(i) != null)
			{
				IVanityItem item = (IVanityItem) inventory.getStackInSlot(i).getItem();
				ModelBase model = item.getVanityItemModel();
				ClientHelper.textureManager().bindTexture(item.getItemTextureLocation());
				GL11.glPushMatrix();
				float yaw = event.entityPlayer.prevRotationYawHead + ((event.entityPlayer.rotationYawHead - event.entityPlayer.prevRotationYawHead)
						* event.partialRenderTick);
				float yawOffset = event.entityPlayer.prevRenderYawOffset + ((event.entityPlayer.renderYawOffset - event.entityPlayer.prevRenderYawOffset)
						* event.partialRenderTick);
				float pitch = event.entityPlayer.prevRotationPitch + ((event.entityPlayer.rotationPitch - event.entityPlayer.prevRotationPitch)
						* event.partialRenderTick);
				GL11.glRotatef(yawOffset, 0, -1, 0);
				GL11.glRotatef(yaw - 270, 0, 1, 0);
				GL11.glRotatef(pitch, 0, 0, 1);
				GL11.glTranslatef(item.getModelOffsetX(), item.getModelOffsetY(), item.getModelOffsetZ());
				GL11.glRotatef(180, 1, 0, 0);
				model.render(event.entity, 0, 0, 0, 1, 0, 0.0625F);
				GL11.glPopMatrix();
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	// hacky hack to make the texture work in BC tanks.
	public void textureHook(TextureStitchEvent.Post event)
	{
		if(event.map.getTextureType() == 0)
		{
			InitBlocks.steamFluid.setIcons(InitBlocks.blockSteam.getBlockTextureFromSide(1));
			InitBlocks.boilingWaterFluid.setIcons(InitBlocks.blockBoilingWater.getBlockTextureFromSide(1));
			InitBlocks.boilingMudFluid.setIcons(InitBlocks.blockBoilingMud.getBlockTextureFromSide(1));
			InitBlocks.whaleOilFluid.setIcons(InitBlocks.blockWhaleOil.getBlockTextureFromSide(1));
		}
	}
}