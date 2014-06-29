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
 * File created @ [12 Apr 2014, 21:57:03]
 */
package steamcraft.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.tiles.TileHatch;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockHatch.
 *
 * @author warlordjones
 */
public class BlockHatch extends BlockContainer
{

	/** The hatch textures. */
	public static ResourceLocation hatchTextures = new ResourceLocation("assets/steamcraft/textures/blocks/");

	/**
	 * Instantiates a new block hatch.
	 *
	 * @param par1
	 *            the par1
	 * @param mat
	 *            the mat
	 */
	public BlockHatch(final int par1, final Material mat)
	{
		super(mat);
		setCreativeTab(Steamcraft.tabSC2);
		// setUnlocalizedName("blockHatch");
		setBlockBounds(0, 0, 0, 1, 0.55F, 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft
	 * .world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(final World world, final int var2)
	{
		return new TileHatch();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockHatchRI;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#isOpaqueCube()
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#renderAsNormalBlock()
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
