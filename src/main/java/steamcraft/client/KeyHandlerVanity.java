package steamcraft.client;

import java.util.EnumSet;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyHandlerVanity extends KeyHandler
{
	private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
	public static boolean keyPressed = false;

	public KeyHandlerVanity(KeyBinding[] keyBindings, boolean[] repeating)
	{
		super(keyBindings, repeating);
	}
	
	@Override
	public String getLabel()
	{
		return "SC_KeyHandler";
	}
	
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

		if (kb == keyBindings[RegisterKeyBindings.CUSTOM_INV]) {
		if (player.openContainer != null && player.openContainer instanceof steamcraft.common.container.ContainerVanity)
		player.closeScreen();
		else if (FMLClientHandler.instance().getClient().inGameHasFocus)
		{
		((EntityClientPlayerMP) player).sendQueue.addToSendQueue(steamcraft.common.lib.network.PacketOpenServerGui.getPacket(steamcraft.client.gui.GuiIDs.GUI_ID_VANITY));
		}
		}
	}
	
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		keyPressed = false;
	}
	
	@Override
	public EnumSet<TickType> ticks() {
	return EnumSet.of(TickType.CLIENT);
	}
}