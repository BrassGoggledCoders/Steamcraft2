package steamcraft.common.lib.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import org.lwjgl.opengl.GL11;

import steamcraft.client.ClientProxy;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitItems;
import steamcraft.common.InitPackets;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.packets.OpenContainerFromClientPacket;
import boilerplate.steamapi.vanity.IVanityItem;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		// DEBUG
		// System.out.println("Key Input Event");

		KeyBinding[] keyBindings = ClientProxy.keyBindings;

		if(keyBindings[0].isPressed())
		{
			// System.out.println("Key binding =" + keyBindings[0].getKeyDescription());
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			InitPackets.network.sendToServer(new OpenContainerFromClientPacket(player.getEntityId(), GuiIDs.VANITY, player.dimension));
			// player.openGui(Steamcraft.instance, GuiIDs.VANITY, Minecraft.getMinecraft().theWorld, player.serverPosX, player.serverPosY, player.serverPosZ);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(receiveCanceled = true)
	public void onPlayerRender(RenderPlayerEvent.Post event)
	{
		// FMLLog.warning("Event Called", "Event Called");
		EntityPlayerExtended props = ((EntityPlayerExtended) event.entityPlayer.getExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME));
		InventoryVanity inventory = props.getInventory();
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if(inventory.getStackInSlot(i) != null)
			{
				IVanityItem item = (IVanityItem) inventory.getStackInSlot(i).getItem();
				ModelBase model = item.getVanityItemModel();
				Minecraft.getMinecraft().renderEngine.bindTexture(item.getItemTextureLocation());
				GL11.glPushMatrix();
				model.render(event.entity, item.getModelOffsetX(), item.getModelOffsetY(), item.getModelOffsetZ(), 0.0625F, 0.0625F, 0.0625F);
				GL11.glPopMatrix();
				Minecraft.getMinecraft().renderEngine.deleteTexture(item.getItemTextureLocation());
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
		}
	}
}