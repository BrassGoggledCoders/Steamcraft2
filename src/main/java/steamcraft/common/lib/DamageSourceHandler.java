/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.lib;

import net.minecraft.util.DamageSource;

/**
 * @author warlordjones
 *
 */
public class DamageSourceHandler
{
	public static DamageSource electrocution = new DamageSource(ModInfo.ID + ".electrocution").setDamageBypassesArmor().setDifficultyScaled();
	public static DamageSource bullet = new DamageSource(ModInfo.ID + ".bullet").setProjectile();
	public static DamageSource rocket = new DamageSource(ModInfo.ID + ".rocket").setDamageBypassesArmor().setProjectile();
	public static DamageSource boulder = new DamageSource(ModInfo.ID + ".boulder").setDamageBypassesArmor().setDifficultyScaled().setDamageIsAbsolute();
	public static DamageSource radioactive = new DamageSource(ModInfo.ID + ".radioactive").setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled();
}