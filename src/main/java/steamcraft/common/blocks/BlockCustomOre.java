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
 * File created @ [Mar 14, 2014, 3:22:57 PM]
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
import boilerplate.steamapi.machines.IHammerable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCustomOre.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCustomOre extends BaseBlock implements IHammerable
{

	/** The icon. */
	private IIcon[] icon = new IIcon[7];

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
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreAluminum");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreCopper");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreTin");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreZinc");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreUranite");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreBrimstone");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "orePhosphate");
	}

	/**
	 * Instantiates a new block custom ore.
	 */
	public BlockCustomOre()
	{
		super(Material.rock);
		setBlockName("blockCustomOre");
		setHardness(3.0F);
		setResistance(6.0F);
		setStepSound(Block.soundTypeStone);
		setTickRandomly(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 7; ++var4)
			l.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, var4));
	}

	@Override
	public ItemStack getOutput(int meta)
	{
		if (meta < 4)
			return new ItemStack(ConfigItems.itemPowder);
		else
			return null;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		switch (metadata)
		{
		case 4:
			drop.add(new ItemStack(ConfigItems.itemResource, 1, 4));
			break;
		case 5:
			drop.add(new ItemStack(ConfigItems.itemResource, 1, 1));
			break;
		case 6:
			drop.add(new ItemStack(ConfigItems.itemResource, 1, 3));
			break;
		default:
			drop.add(0, new ItemStack(ConfigBlocks.blockCustomOre, 1, metadata));
			break;
		}

		return drop;
	}
}
