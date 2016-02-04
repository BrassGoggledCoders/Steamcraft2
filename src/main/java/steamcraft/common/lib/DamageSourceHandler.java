
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
	public static DamageSource boulder = new DamageSource(ModInfo.ID + ".boulder").setDamageBypassesArmor().setDifficultyScaled()
			.setDamageIsAbsolute();
	public static DamageSource radioactive = new DamageSource(ModInfo.ID + ".radioactive").setDamageBypassesArmor().setDamageIsAbsolute()
			.setDifficultyScaled();
}