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
public class BlockSlate extends Block
{
	private Icon[] icon = new Icon[11];

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		return this.icon[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		//Raw
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlue");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlack");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawRed");
		//Cobble
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlue");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlack");
		this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleRed");
		//Brick
		this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlue");
		this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlack");
		this.icon[8] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickRed");
		//Polished
	}

	public BlockSlate(int id)
	{
		super(id, Material.rock);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("blockSlate");
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);
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
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList drop = new ArrayList();

		switch (metadata) {
		case 0:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 0));
		case 1:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 1));
		case 2:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 2));
		case 3:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 3));
		case 4:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 4));
		case 5:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 5));
		case 6:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 6));
		case 7:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 7));
		case 8:
			drop.add(new ItemStack(ConfigBlocks.blockSlate, 1, 8));
		}

		return drop;
	}
}