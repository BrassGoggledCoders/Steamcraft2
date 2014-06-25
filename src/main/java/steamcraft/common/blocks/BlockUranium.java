package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import boilerplate.client.utils.EffectUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUranium extends BaseBlock
{
	public BlockUranium(Material p_i45394_1_)
	{
		super(p_i45394_1_);

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
}
