package steamcraft.common.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.client.IRenderHandler;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigGeneral;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderDeeps extends WorldProvider
{
	IRenderHandler skyRenderer;

	@Override
	public String getDimensionName()
	{
		return "The Deeps";
	}

	/**
	 * A message to display to the user when they transfer to this dimension.
	 * 
	 * @return The message to be displayed
	 */
	@Override
	public String getWelcomeMessage()
	{
		return "Digging down to the Deeps";
	}

	/**
	 * A Message to display to the user when they transfer out of this dismension.
	 * 
	 * @return The message to be displayed
	 */
	@Override
	public String getDepartMessage()
	{
		return "Building up to the surface";
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.dimensionId = ConfigGeneral.deepsDimensionID;
		// TODO?
		this.worldChunkMgr = new WorldChunkManagerHell(Steamcraft.biomeDepths, 0F);
		this.generateLightBrightnessTable();
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderGenerate(this.worldObj, this.worldObj.getSeed(), false);
		// return new ChunkProviderDeeps(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public int getActualHeight()
	{
		return 256;
	}

	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_)
	{
		return 1F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer()
	{
		return new DeepsSkyRenderer();
	}

	@Override
	public float getCloudHeight()
	{
		return 3F;
	}

	@Override
	protected void generateLightBrightnessTable()
	{
		float var1 = 0.1F;

		for(int var2 = 0; var2 <= 15; ++var2)
		{
			float var3 = 1.0F - var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}
	}
}
