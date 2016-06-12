
package steamcraft.common.blocks;

import java.util.Random;

import boilerplate.client.utils.EffectUtils;
import boilerplate.common.baseclasses.blocks.BaseBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author warlordjones
 *
 */
public class BlockUranium extends BaseBlock
{
	public BlockUranium(Material mat)
	{
		super(mat);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.magic, 5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		EffectUtils.sparkle(world, x, y, z, "reddust");
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}
}
