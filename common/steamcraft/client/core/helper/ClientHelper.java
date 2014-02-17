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
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.client.core.helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
/**
 * @author MrArcane111 & decabeldecabel
 *
 */
public class ClientHelper {
	public static Minecraft mc() {
		return Minecraft.getMinecraft();
	}

	public static FontRenderer fontRenderer() {
		return mc().fontRenderer;
	}

	public static EntityClientPlayerMP clientPlayer() {
		return mc().thePlayer;
	}

	public static String shiftForInfo = EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.GREEN + "SHIFT" + EnumChatFormatting.GRAY + " for more info.";

	public static boolean isShiftKeyDown() {
		return (Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54));
	}
}
