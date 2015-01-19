package steamcraft.common.worldgen.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeDepthsBase extends BiomeGenBase
{

	public BiomeDepthsBase(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityLostMiner.class, 20, 1, 2));
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityGrub.class, 40, 3, 6));
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = false;
		this.setHeight(new Height(1F, 1.2F));
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.name"));
		this.rootHeight = 1.5F;
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
}
