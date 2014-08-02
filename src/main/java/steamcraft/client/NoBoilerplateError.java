package steamcraft.client;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import cpw.mods.fml.client.CustomModLoadingErrorDisplayException;

public class NoBoilerplateError extends CustomModLoadingErrorDisplayException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer)
	{
		errorScreen.allowUserInput = false;
		fontRenderer.drawString("The Libary mod Boilerplate must be installed to use this mod.", 10, 10, 10);
		fontRenderer.drawString("Get it from here: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2120327-1-7-2-steamcraft2-30-6-14-beta1-the-beginning", 10, 10, 10);
	}
	@Override
	public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime)
	{
		errorScreen.allowUserInput = false;
		fontRenderer.drawString("The Libary mod Boilerplate must be installed to use this mod.", 10, 10, 10);
		fontRenderer.drawString("Get it from here: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2120327-1-7-2-steamcraft2-30-6-14-beta1-the-beginning", 10, 10, 10);
	}

}
