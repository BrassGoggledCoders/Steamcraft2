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
 * File created @ [Apr 8, 2014, 2:18:47 PM]
 */
package steamcraft.common.config;

import steamcraft.common.entities.projectile.EntityBullet;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class ConfigEntities
{
	public static void init()
	{
		EntityRegistry.registerGlobalEntityID(EntityBullet.class, "Bullet",
				EntityRegistry.findGlobalUniqueEntityId());
		// EntityRegistry.registerModEntity(EntityBullet.class, "Bullet",
		// Config.entBulletId, Steamcraft.instance, 64, 20, true);
	}

	public static void initEntitySpawns()
	{
	}
}
