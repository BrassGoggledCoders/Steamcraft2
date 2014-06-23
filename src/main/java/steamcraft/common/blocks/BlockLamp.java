/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/14/14, 20:57]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCosmeticSolid.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class BlockLamp extends BlockRedstoneLight
{

	/** The icon. */
	private IIcon[] icon = new IIcon[2];

	/** The powered. */
	private static boolean powered;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return icon[metadata];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockLampOff");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockLampOn");
	}

	/**
	 * Instantiates a new block cosmetic solid.
	 */
	public BlockLamp()
	{
		super(powered);
		setBlockName("blockLamp");
		setHardness(3.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(Steamcraft.tabSC2);

		if (powered)
		{
			setLightLevel(0.98F);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.BlockRedstoneLight#onBlockAdded(net.minecraft.world
	 * .World, int, int, int)
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			if ((BlockLamp.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, ConfigBlocks.blockLamp, 1, 12);
			}
			else if ((!BlockLamp.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockLamp, 1, 11);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.BlockRedstoneLight#onNeighborBlockChange(net.minecraft
	 * .world.World, int, int, int, net.minecraft.block.Block)
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if (!world.isRemote)
		{
			if ((this.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, ConfigBlocks.blockLamp, 1, 12);
			}
			else if ((!BlockLamp.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockLamp, 0, 11);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.BlockRedstoneLight#updateTick(net.minecraft.world
	 * .World, int, int, int, java.util.Random)
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if ((!world.isRemote && BlockLamp.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
		{
			world.setBlock(x, y, z, ConfigBlocks.blockLamp, 0, 12);
		}
	}

}