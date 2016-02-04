
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.utils.EffectUtils;
import brassutils.api.IEngravable;
import steamcraft.common.init.InitBlocks;

/**
 * @author warlordjones
 *
 */
@Optional.Interface(iface = "brassutils.api.IEngravable", modid = "brassutils")
public class BlockUranium extends BaseBlock implements IEngravable
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
	public Block getChiseledVariant()
	{
		return InitBlocks.blockEngraved;
	}

	@Override
	public int getChiseledVariantMeta()
	{
		return 8;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}
}
