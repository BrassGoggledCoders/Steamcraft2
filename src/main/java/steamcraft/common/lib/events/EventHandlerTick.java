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
 * File created @ [Mar 13, 2014, 7:46:47 PM]
 */
package steamcraft.common.lib.events;


/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EventHandlerTick
{
	/*
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		if (event.phase == Phase.START)
		{
			//WorldServer serverWorld = (WorldServer) event.world;
			World world = event.world;
		}
	}

	@SubscribeEvent
	public void onGuiTick(GuiOpenEvent event)
	{	
		if ((event.gui != null))// && (event.gui instanceof GuiMainMenu))
		{
			System.out.println("adsfd");
		}
		else
		{
			event.setCanceled(true);
		}


		/*
		if (event.gui == null) // TODO: should check if enabled in config file
			return;
		if (event.gui instanceof GuiMainMenu)
			SplashesHelper.hackSplashes((GuiMainMenu)event.gui);
		System.out.println("Woof");

		// Text at the bottom of the screen
		if (event.gui instanceof GuiMainMenu)
			event.gui.drawString(event.gui.mc.fontRenderer, ", Steamcraft 2 " + LibInfo.VERSION, 
					2 + event.gui.mc.fontRenderer.getStringWidth("Minecraft 1.7.2"), event.gui.height - 10, 0xffffffff);

		System.out.println("GuiMainMenu");
		}*/
}
