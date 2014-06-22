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
 * File created @ [3/15/14, 13:40]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.tiles.TileCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCrystal.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCrystal extends BlockContainer implements ITileEntityProvider
{

	/**
	 * Instantiates a new block crystal.
	 */
	public BlockCrystal()
	{
		super(Material.glass);
		setBlockName("blockCrystal");
		setHardness(8.5F);
		setResistance(-1);
		setStepSound(Block.soundTypeGlass);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft
	 * .world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(final World world, final int metadata)
	{
		return new TileCrystal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType()
	{
		return ConfigBlocks.blockCrystalRI;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#getCollisionBoundingBoxFromPool(net.minecraft
	 * .world.World, int, int, int)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#canCreatureSpawn(net.minecraft.entity.
	 * EnumCreatureType, net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public boolean canCreatureSpawn(final EnumCreatureType type, final IBlockAccess world, final int x, final int y, final int z)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#addDestroyEffects(net.minecraft.world.World,
	 * int, int, int, int, net.minecraft.client.particle.EffectRenderer)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(final World world, final int x, final int y, final int z, final int metadata, final EffectRenderer effectRenderer)
	{
		return super.addDestroyEffects(world, x, y, z, metadata, effectRenderer);
	}

	/*
	 * @SideOnly(Side.CLIENT) public AxisAlignedBB
	 * getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int
	 * p_149633_3_, int p_149633_4_) { return null; }
	 * 
	 * @Override public int getExpDrop(World world, int i, int j) { if
	 * (this.idDropped(i, world.rand, j) != this.blockID) { int randInt =
	 * MathHelper.getRandomIntegerInRange(world.rand, 4, 10); return randInt; }
	 * 
	 * return 0; }
	 * 
	 * @Override public int quantityDroppedWithBonus(int quantity, Random
	 * random) { if (quantity > 0 && (this.blockID != this.idDropped(0, random,
	 * quantity))) { int randInt = random.nextInt(quantity + 2) - 1;
	 * 
	 * if (randInt < 0) { randInt = 0; }
	 * 
	 * return this.quantityDropped(random) * (randInt + 1); } else { return
	 * this.quantityDropped(random); } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#tickRate(net.minecraft.world.World)
	 */
	@Override
	public int tickRate(final World world)
	{
		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getItemDropped(int, java.util.Random, int)
	 */
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return ConfigItems.itemResource;
	}

}
