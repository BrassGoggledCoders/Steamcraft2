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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class BlockMetal extends Block{
	private Icon[] icon = new Icon[9];
	private boolean powered;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
        this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBrass");
        this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBronze");
        this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockSteel");
        this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockUranite");
        this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockAluminum");
        this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockCopper");
        this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockTin");
        this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockZinc");
        this.icon[8] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockEtherium");
	}

	public BlockMetal(int id)
	{
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("blockMetal");
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);

		if (this.powered)
		{
			this.setLightValue(0.98F);
		}
	}

	@Override
	public int damageDropped(int id)
	{
		return id;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
		list.add(new ItemStack(id, 1, 3));
		list.add(new ItemStack(id, 1, 4));
		list.add(new ItemStack(id, 1, 5));
		list.add(new ItemStack(id, 1, 6));
		list.add(new ItemStack(id, 1, 7));
		list.add(new ItemStack(id, 1, 8));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 4))
		{
			entity.attackEntityFrom(DamageSource.magic, 1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 4))
		{
			Utils.sparkle(world, x, y, z, "reddust");
		}
	}
}