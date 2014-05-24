package steamcraft.common;

import steamcraft.client.lib.GuiIDs;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.lib.network.PacketOpenServerGui;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler
{
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

		if(KeyBindings.keyOpenVanityGUI.isPressed())
		{
		if (player.openContainer != null && player.openContainer instanceof ContainerVanity)
		player.closeScreen();
		else if (FMLClientHandler.instance().getClient().inGameHasFocus)
		{
		((EntityClientPlayerMP) player).sendQueue.addToSendQueue(PacketOpenServerGui.getPacket(GuiIDs.GUI_ID_VANITY));
		}
		}
	}
}
