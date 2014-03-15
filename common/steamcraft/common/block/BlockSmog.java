/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [17 Feb 2014, 23:08:00]
 */
package common.steamcraft.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author warlordjones
 *
 * 17 Feb 201423:08:00
 */
public class BlockSmog extends BlockMod{

	/**
	 * @param id
	 * @param mat
	 */
	public BlockSmog(int id, Material mat) {
		super(id, mat);
		setTickRandomly(true);
	}

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World,
	    final int par2, final int par3, final int par4) {
	return null;
    }

    @Override
    public int getRenderBlockPass() {
	return 1;
    }

    @Override
    public int quantityDropped(final Random par1Random) {
	return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    //Should be a 60% chance to dissipate, 40% chance to spread
    @Override
    public void updateTick(final World par1World, final int par2,
	    final int par3, final int par4, final Random par5Random) {
	if (!par1World.isRemote)
	    for (int l = 0; l < 1; ++l) {
		final int wx = par2 + par5Random.nextInt(3) - 1;
		final int wy = par3 + par5Random.nextInt(5) - 3;
		final int wz = par4 + par5Random.nextInt(3) - 1;
		par1World.getBlockId(wx, wy + 1, wz);
		if (par1World.getBlockId(wx, wy, wz) == 0)
		if(Math.random() > 0.4)
			par1World.setBlock(wx, wy, wz, blockID);
		}
		else
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
	    }
  @Override
    public void onEntityCollidedWithBlock(final World par1World,
	    final int par2, final int par3, final int par4,
	    final Entity par5Entity) 
    {
	par5Entity.setInWeb();
	if(Math.random() < 0.7)
	{
	par5Entity.attackEntityFrom(DamageSource.wither, 10);
	}
	else
	{
		par5Entity.attackEntityFrom(DamageSource.drown, 10);
	}
    }
}
