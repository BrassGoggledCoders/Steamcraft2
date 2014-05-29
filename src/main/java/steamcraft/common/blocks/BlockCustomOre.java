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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCustomOre.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCustomOre extends Block
{
	
	/** The icon. */
	private final IIcon[] icon = new IIcon[8];

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		return icon[metadata];
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreBornite");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreBrimstone");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "orePhosphate");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreUranite");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreAluminum");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreCopper");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreTin");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreZinc");
	}

	/**
	 * Instantiates a new block custom ore.
	 */
	public BlockCustomOre()
	{
		super(Material.rock);
		setHardness(3.0F);
		setResistance(6.0F);
		setStepSound(Block.soundTypeStone);
		// this.setUnlocalizedName("blockOre");
		setTickRandomly(true);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(final int id)
	{
		return id;
	}
}
