package steamcraft.common.lib;

import net.minecraft.util.DamageSource;

public class DamageSourceHandler
{
	public static DamageSource electrocution = new DamageSource(LibInfo.ID + ".electrocution").setDamageBypassesArmor().setDifficultyScaled();
}
