/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 111
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.steamcraft.common.init;

import net.minecraft.entity.EnumCreatureType;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraftforge.common.BiomeDictionary;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.entities.EntityFallingBoulder;
import xyz.brassgoggledcoders.steamcraft.common.entities.EntityTimeBomb;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityAbandonedGolem;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityBoar;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityFleshGolem;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGhostSpider;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGiantSpider;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGrub;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityLostMiner;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityShroomSkeleton;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityShroomZombie;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntitySpiderQueen;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityVampireBat;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityWhale;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityBullet;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityFieldManipulator;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityRocket;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntitySplashLightningBottle;

/**
 * @author Surseance
 *
 */
public class InitEntities
{
	public static void init()
	{
		int entID = 0;

		// Mobs
		EntityRegistry.registerModEntity(EntityFleshGolem.class, "FleshGolem", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityGrub.class, "Grub", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityLostMiner.class, "LostMiner", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityBoar.class, "Boar", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityVampireBat.class, "VampireBat", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityGhostSpider.class, "GhostSpider", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityGiantSpider.class, "GiantSpider", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntitySpiderQueen.class, "SpiderQueen", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityShroomZombie.class, "ShroomZombie", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityShroomSkeleton.class, "ShroomSkeleton", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityAbandonedGolem.class, "AbdandonedGolem", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityWhale.class, "Whale", entID++, Steamcraft.instance, 64, 1, true);
		EntityRegistry.addSpawn(EntityWhale.class, 1, 1, 1, EnumCreatureType.waterCreature,
				BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN));
		// Non-Living
		// Projectile
		EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", entID++, Steamcraft.instance, 40, 1, true);
		EntityRegistry.registerModEntity(EntityRocket.class, "Rocket", entID++, Steamcraft.instance, 40, 1, true);
		EntityRegistry.registerModEntity(EntitySplashLightningBottle.class, "SplashLightningBottle", entID++, Steamcraft.instance, 40, 1, true);
		EntityRegistry.registerModEntity(EntityFieldManipulator.class, "SplashLightningBottle", entID++, Steamcraft.instance, 40, 1, true);
		// Block
		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", entID++, Steamcraft.instance, 40, 1, true);
		EntityRegistry.registerModEntity(EntityFallingBoulder.class, "FallingBoulder", entID++, Steamcraft.instance, 40, 1, true);
	}
}
