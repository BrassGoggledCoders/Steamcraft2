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
 * File created @ [3/14/14, 21:25]
 */
package steamcraft.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.utils.EffectUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockEngravedSolid.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class BlockEngravedSolid extends Block
{

	/** The icon. */
	private IIcon[] icon = new IIcon[9];

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
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedBrass");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedCastIron");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedDiamond");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedGold");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedIron");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedLapis");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedStone");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedUranium");
		icon[8] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedEtherium");
		// TODO: Add engravings of aluminum, copper, zinc, tin...
	}

	/**
	 * Instantiates a new block engraved solid.
	 */
	public BlockEngravedSolid()
	{
		super(Material.rock);
		setBlockName("blockEngravedSolid");
		setHardness(3.0F);
		setResistance(15.0F);
		setStepSound(Block.soundTypeMetal);
		setTickRandomly(true);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(int id)
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#onEntityCollidedWithBlock(net.minecraft.world
	 * .World, int, int, int, net.minecraft.entity.Entity)
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if ((world.getBlock(x, y, z) == this) && (world.getBlockMetadata(x, y, z) == 7))
			entity.attackEntityFrom(DamageSource.magic, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World,
	 * int, int, int, java.util.Random)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if ((world.getBlock(x, y, z) == this) && (world.getBlockMetadata(x, y, z) == 7))
			EffectUtils.sparkle(world, x, y, z, "reddust");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item,
	 * net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 8; ++var4)
			l.add(new ItemStack(ConfigBlocks.blockEngraved, 1, var4));
	}

}
