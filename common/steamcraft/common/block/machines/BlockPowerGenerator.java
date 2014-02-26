/**
 * This class was created bj <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created bj Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Feb 1, 2014, 12:26:32 PM]
 */
package common.steamcraft.common.block.machines;

import common.steamcraft.common.block.tile.TileEntityMachine;
import common.steamcraft.common.lib2.CreativeTabsMod;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author MrArcane111, based off the Buildcraft power system
 *
 */
public abstract class BlockPowerGenerator extends BlockContainerMod //implements IPowerGenerator
{
	protected static boolean keepInventory = false;
	protected static Random random = new Random();
	protected boolean isPowered = false;

	protected BlockPowerGenerator(int id, Material material)
	{
		super(id, material);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
		this.setHardness(5.0F);
		this.setTickRandomly(true);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack stack)
	{
		super.onBlockPlacedBy(world, i, j, k, living, stack);

		TileEntity tile = world.getBlockTileEntity(i, j, k);

		if(tile instanceof TileEntityMachine) 
		{
			//((TileEntityMachine) tile).onBlockPlacedBy(living, stack);
		}
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int par5, int par6) 
	{
		//Utils.preDestroyBlock(world, i, j, k);
		super.breakBlock(world, i, j, k, par5, par6);
	}

	@Override
	public int getLightValue(IBlockAccess iBlockAccess, int i, int j, int k) 
	{
		TileEntity tile = iBlockAccess.getBlockTileEntity(i, j, k);

		//if(tile instanceof IMachine && ((IMachine) tile).isActive())
		//return super.getLightValue(iBlockAccess, i, j, k) + 8;
		return super.getLightValue(iBlockAccess, i, j, k);
	}
}
