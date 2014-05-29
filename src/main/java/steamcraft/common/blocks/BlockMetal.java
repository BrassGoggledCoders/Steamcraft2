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
 * File created @ [9 Apr 2014, 09:43:13]
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
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockMetal.
 *
 * @author warlordjones
 */
public class BlockMetal extends Block
{
	
	/** The icon. */
	private final IIcon[] icon = new IIcon[9];

	/** The powered. */
	private boolean powered;

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBrass");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBronze");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockSteel");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockUranium");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockAluminum");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockCopper");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockTin");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockZinc");
		icon[8] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockEtherium");
	}

	/**
	 * Instantiates a new block metal.
	 */
	public BlockMetal()
	{
		super(Material.iron);
		setHardness(3.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeMetal);
		// this.setUnlocalizedName("blockMetal");
		setTickRandomly(true);
		setCreativeTab(Steamcraft.tabSC2);

		if (powered)
		{
			setLightLevel(0.98F);
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(final int id)
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onEntityCollidedWithBlock(net.minecraft.world.World, int, int, int, net.minecraft.entity.Entity)
	 */
	@Override
	public void onEntityCollidedWithBlock(final World world, final int x,
			final int y, final int z, final Entity entity)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 4))
		{
			entity.attackEntityFrom(DamageSource.magic, 1);
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World, int, int, int, java.util.Random)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World world, final int x, final int y,
			final int z, final Random random)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 4))
		{
			Utils.sparkle(world, x, y, z, "reddust");
		}
	}
	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item, net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab,
			final List l)
	{
		for (int var4 = 0; var4 < 8; ++var4)
		{
			l.add(new ItemStack(ConfigBlocks.blockMetal, 1, var4));
		}
	}
}
