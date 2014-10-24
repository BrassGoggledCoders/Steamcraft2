package steamcraft.common.lib.events;

import net.minecraftforge.client.event.FOVUpdateEvent;
import steamcraft.common.InitItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class EventHandlerClient
{
	@SubscribeEvent
    public void zoom(FOVUpdateEvent event)
    {
		if(event.entity.inventory.getCurrentItem() != null && event.entity.inventory.getCurrentItem().getItem() == InitItems.itemSpyglass)
		{
			event.newfov = 0.3F;
		}
    }
}