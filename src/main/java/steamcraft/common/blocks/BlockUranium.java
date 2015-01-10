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

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.InitBlocks;
import boilerplate.client.utils.EffectUtils;
import boilerplate.steamapi.block.IChiselable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 * 
 */
public class BlockUranium extends BaseBlock implements IChiselable
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
