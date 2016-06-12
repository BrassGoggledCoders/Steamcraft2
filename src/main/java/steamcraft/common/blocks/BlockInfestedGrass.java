
package steamcraft.common.blocks;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.entities.living.EntityGrub;

public class BlockInfestedGrass extends BlockGrass
{
	public BlockInfestedGrass(Material mat)
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setHardness(0.5F);
		this.setBlockTextureName("grass");
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
	{
		if (!world.isRemote && (world.rand.nextInt(10) == 0))
		{
			EntityGrub grub = new EntityGrub(world);
			grub.setLocationAndAngles(p_149664_2_ + 0.5D, p_149664_3_, p_149664_4_ + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(grub);
			grub.spawnExplosionParticle();
		}

		super.onBlockDestroyedByPlayer(world, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
	}
}
