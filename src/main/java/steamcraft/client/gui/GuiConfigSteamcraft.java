
package steamcraft.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import steamcraft.common.config.ConfigBalance;
import steamcraft.common.lib.ModInfo;

/**
 *
 * @author decebaldecebal
 *
 */
public class GuiConfigSteamcraft extends GuiConfig
{
	@SuppressWarnings("all")
	public GuiConfigSteamcraft(GuiScreen parent)
	{
		super(parent, new ConfigElement(ConfigBalance.config.getCategory(ConfigBalance.CATEGORY_RECIPE_OPTIONS)).getChildElements(), ModInfo.ID,
				ConfigBalance.configId, false, false, GuiConfig.getAbridgedConfigPath(ConfigBalance.config.toString()));
	}
}