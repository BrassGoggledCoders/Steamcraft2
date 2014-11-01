package steamcraft.common.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigGeneral;

public class WorldProviderDeeps extends WorldProvider
{
	@Override
	public String getDimensionName()
	{
		return "The Deeps";
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.dimensionId = ConfigGeneral.deepsDimensionID;
		// TODO
		this.worldChunkMgr = new WorldChunkManagerHell(Steamcraft.biomeDepths, this.dimensionId);
		this.hasNoSky = false;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderDeeps(this.worldObj, this.worldObj.getSeed());
	}
}
