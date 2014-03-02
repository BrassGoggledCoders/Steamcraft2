/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [2 Mar 2014, 21:23:44]
 */
package common.steamcraft.common.core.handler;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author warlordjones
 *
 * 2 Mar 201421:23:44
 */
public class LocalizationHandler {
public void handleLocalization()
{
	LanguageRegistry.instance().loadLocalization("assets/steamcraft/lang/en_US.lang", "en_US", false);
}
}
