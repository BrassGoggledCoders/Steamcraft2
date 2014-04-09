package steamcraft.common.lib.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class CustomWorldGenerator extends WorldGenMinable 
{
	// Block to Generate in
	private final int block;
	//Meta of Generated Block
	private final int metadata;
	/** The block ID of the ore to be placed using this generator. */
	private final int minableBlockId;
	/** Max Vein Size */
	private final int numberOfBlocks;

	public CustomWorldGenerator(final int id, final int meta, final int number, final int base_block)
	{
		super(id,meta,number,base_block);
		minableBlockId = id;
		metadata = meta;
		numberOfBlocks = number;
		block = base_block;
	}
	
	@Override
	public boolean generate(final World world, final Random random, final int xCoord, final int yCoord, final int zCoord) 
	{
		for (int rare = 0; rare < yCoord; rare++) 
		{
			final float f = random.nextFloat() * (float) Math.PI;
			final double d = xCoord + 8 + MathHelper.sin(f) * numberOfBlocks / 8F;
			final double d1 = xCoord + 8 - MathHelper.sin(f) * numberOfBlocks / 8F;
			final double d2 = zCoord + 8 + MathHelper.cos(f) * numberOfBlocks / 8F;
			final double d3 = zCoord + 8 - MathHelper.cos(f) * numberOfBlocks / 8F;
			final double d4 = yCoord + random.nextInt(3) - 2;
			final double d5 = yCoord + random.nextInt(3) - 2;
			
			for (int i = 0; i <= numberOfBlocks; i++) 
			{
				final double d6 = d + (d1 - d) * i / numberOfBlocks;
				final double d7 = d4 + (d5 - d4) * i / numberOfBlocks;
				final double d8 = d2 + (d3 - d2) * i / numberOfBlocks;
				final double d9 = random.nextDouble() * numberOfBlocks / 16D;
				final double d10 = (MathHelper.sin(i * (float) Math.PI / numberOfBlocks) + 1.0F) * d9 + 1.0D;
				final double d11 = (MathHelper.sin(i * (float) Math.PI / numberOfBlocks) + 1.0F) * d9 + 1.0D;
				final int j = MathHelper.floor_double(d6 - d10 / 2D);
				final int k = MathHelper.floor_double(d7 - d11 / 2D);
				final int l = MathHelper.floor_double(d8 - d10 / 2D);
				final int i1 = MathHelper.floor_double(d6 + d10 / 2D);
				final int j1 = MathHelper.floor_double(d7 + d11 / 2D);
				final int k1 = MathHelper.floor_double(d8 + d10 / 2D);
				
				for (int l1 = j; l1 <= i1; l1++)
				{
					final double d12 = (l1 + 0.5D - d6) / (d10 / 2D);
					
					if (d12 * d12 >= 1.0D)
						continue;
					
					for (int i2 = k; i2 <= j1; i2++) 
					{
						final double d13 = (i2 + 0.5D - d7) / (d11 / 2D);
						
						if (d12 * d12 + d13 * d13 >= 1.0D)
							continue;
						
						for (int j2 = l; j2 <= k1; j2++)
						{
							final double d14 = (j2 + 0.5D - d8) / (d10 / 2D);
							
							if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlockId(l1, i2, j2) == block) 
								world.setBlock(l1, i2, j2, minableBlockId, metadata, 2);
						}
					}
				}
			}
		}
		
		return true;
	}
}
