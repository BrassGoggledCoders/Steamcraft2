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
 * File created @ [13 Apr 2014, 09:36:19]
 */
package steamcraft.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockSlate.
 *
 * @author warlordjones
 */
public class BlockSlate extends BaseBlock
{

	/** The icon. */
	private final IIcon[] icon = new IIcon[9];

	/**
	 * Instantiates a new block slate.
	 */
	public BlockSlate()
	{
		super(Material.rock);
		setHardness(3.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("blockSlate");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
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
	public void registerBlockIcons(final IIconRegister ir)
	{
		// Raw
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlue");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlack");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawRed");
		// Cobble
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlue");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlack");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleRed");
		// Brick
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlue");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlack");
		icon[8] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickRed");
		// Polished
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
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < 8; ++var4)
			l.add(new ItemStack(ConfigBlocks.blockSlate, 1, var4));
	}
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		switch (metadata)
		{
		case 0:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 3));
			break;
		case 1:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 4));
			break;
		case 2:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 5));
			break;
		default:
			drop.add(0, new ItemStack(ConfigBlocks.blockSlate, 1, metadata));
			break;
		}

		return drop;
	}
}