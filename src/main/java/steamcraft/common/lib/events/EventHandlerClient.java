package steamcraft.common.lib.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import steamcraft.client.ClientProxy;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.InitItems;
import steamcraft.common.Steamcraft;
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
		System.out.println("Key Input Event");

		KeyBinding[] keyBindings = ClientProxy.keyBindings;

		if(keyBindings[0].isPressed())
		{
			System.out.println("Key binding =" + keyBindings[0].getKeyDescription());
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.openGui(Steamcraft.instance, GuiIDs.VANITY, Minecraft.getMinecraft().theWorld, player.serverPosX, player.serverPosY, player.serverPosZ);
		}
	}
}