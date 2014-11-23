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
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.entities.EntityGrub;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfestedDirt extends BaseBlock
{
	public BlockInfestedDirt(Material mat)
	{
		super(mat);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.dirt.getBlockTextureFromSide(side);
	}

	/**
	 * Called right before the block is destroyed by a player. Args: world, x, y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World world, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
	{
		if(!world.isRemote && (world.rand.nextInt(10) == 0))
		{
			EntityGrub grub = new EntityGrub(world);
			grub.setLocationAndAngles(p_149664_2_ + 0.5D, p_149664_3_, p_149664_4_ + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(grub);
			grub.spawnExplosionParticle();
		}

		super.onBlockDestroyedByPlayer(world, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
	}
}
