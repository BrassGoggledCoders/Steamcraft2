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
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.entities.living.EntityAbandonedGolem;
import steamcraft.common.entities.living.EntityBoar;
import steamcraft.common.entities.living.EntityGhostSpider;
import steamcraft.common.entities.living.EntityGiantSpider;
import steamcraft.common.entities.living.EntityLostMiner;
import steamcraft.common.entities.living.EntityShroomSkeleton;
import steamcraft.common.entities.living.EntityShroomZombie;
import steamcraft.common.entities.living.EntityVampireBat;

public class BiomeDepthsBase extends BiomeGenBase
{
	private DepthsBiomeDecorator decorator = (DepthsBiomeDecorator) this.theBiomeDecorator;

	public BiomeDepthsBase(int p_i1971_1_)
	{
		super(p_i1971_1_);
		// Cave only
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityLostMiner.class, 10, 1, 3));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityShroomSkeleton.class, 10, 1, 3));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityShroomZombie.class, 10, 1, 3));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAbandonedGolem.class, 1, 1, 1));
		// this.spawnableMonsterList.add(new
		// BiomeGenBase.SpawnListEntry(EntityGrub.class, 10, 3, 6));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityVampireBat.class, 10, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhostSpider.class, 100, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCaveSpider.class, 100, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGiantSpider.class, 10, 1, 4));
		// Animals
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 10, 4, 4));
		// this.spawnableWaterCreatureList.add(new
		// BiomeGenBase.SpawnListEntry(EntityWhale.class, 5, 1, 5));

		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = false;
		this.setHeight(new Height(0.5F, 0.8F));
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.name"));
		this.rootHeight = 0.6F;
		this.func_76733_a(8045877);
		this.setColor(8045877);
		this.waterColorMultiplier = 6860222;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 8045877;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
	{
		return 8045877;
	}

	@Override
	public BiomeDecorator getModdedBiomeDecorator(BiomeDecorator original)
	{
		return new DepthsBiomeDecorator(original);
	}

	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		this.decorator.decorateChunk(world, random, this, x, z);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return this.worldGeneratorTrees;
	}
}
