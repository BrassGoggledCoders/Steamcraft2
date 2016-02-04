
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.entities.living.EntityGhostSpider;
import steamcraft.common.entities.living.EntityGiantSpider;

public class BlockSpiderEgg extends BaseBlock
{

	public BlockSpiderEgg(Material mat)
	{
		super(mat);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Called right before the block is destroyed by a player. Args: world, x,
	 * y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World world, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
	{
		if (!world.isRemote)
		{
			int randNum = world.rand.nextInt(10);
			EntitySpider spider;
			if (randNum < 3)
				spider = new EntityGhostSpider(world);
			else if (randNum == 1)
				spider = new EntityGiantSpider(world);
			else
				spider = new EntitySpider(world);
			spider.setLocationAndAngles(p_149664_2_ + 0.5D, p_149664_3_, p_149664_4_ + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(spider);
			spider.spawnExplosionParticle();
		}

		super.onBlockDestroyedByPlayer(world, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if
	 * the adjacent block is at the given coordinates. Args: blockAccess, x, y,
	 * z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockSpiderEggRI;
	}
}
