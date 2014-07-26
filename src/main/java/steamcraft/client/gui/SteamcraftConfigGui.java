package steamcraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import steamcraft.common.config.ConfigGeneral;
import cpw.mods.fml.client.config.GuiConfig;

public class SteamcraftConfigGui extends GuiConfig {
    public SteamcraftConfigGui(GuiScreen parent) {
        super(parent,
                new ConfigElement(ConfigGeneral.config.getCategory(ConfigGeneral.CATEGORY_GENERAL)).getChildElements(),
                "Steamcraft 2", false, false, GuiConfig.getAbridgedConfigPath(ConfigGeneral.config.toString()));
    }
}