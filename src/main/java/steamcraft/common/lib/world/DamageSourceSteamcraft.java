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
 * File created @ [Apr 8, 2014, 6:31:52 PM]
 */
package steamcraft.common.lib.world;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class DamageSourceSteamcraft extends DamageSource
{
	public static DamageSource bullet = new DamageSourceSteamcraft("bullet").setProjectile();

	protected DamageSourceSteamcraft(String name)
	{
		super(name);
	}

	@Override
	public ChatMessageComponent getDeathMessage(EntityLivingBase entityLiving)
	{
		String message = "";
		ChatMessageComponent chat = ChatMessageComponent.createFromText(message);
		
		if (this.damageType == "bullet")
		{
			message = "Shot by an assailant!"; 
		}
		
		return chat;
	}
}
