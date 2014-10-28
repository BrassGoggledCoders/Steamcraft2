package steamcraft.common.lib.events;

import net.minecraftforge.client.event.FOVUpdateEvent;
import steamcraft.common.InitItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandlerClient
{
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void zoom(FOVUpdateEvent event)
	{
		if(event.entity.inventory.getCurrentItem() != null && event.entity.inventory.getCurrentItem().getItem() == InitItems.itemSpyglass)
		{
			event.newfov = 0.3F;
		}
	}
}