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
 * File created @ [Mar 20, 2014, 10:31:50 AM]
 */
package steamcraft.common.items;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.fx.FXSmoke;
import boilerplate.steamapi.IChiselable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemChisel.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ItemChisel extends BaseItem
{

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture
	 * .IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemChisel");
	}

	/**
	 * Instantiates a new item chisel.
	 */
	public ItemChisel()
	{
		setMaxStackSize(1);
		setMaxDamage(20);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#onItemUse(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int,
	 * int, int, int, float, float, float)
	 */
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block block = world.getBlock(x, y, z);

		if(block == ConfigBlocks.blockMetal)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, world.getBlockMetadata(x, y, z), 2);
		}
		else if(block == Blocks.diamond_block)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, 9, 2);
		}
		else if(block == Blocks.gold_block)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, 10, 2);
		}
		else if(block == Blocks.iron_block)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, 11, 2);
		}
		else if(block == Blocks.lapis_block)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, 12, 2);
		}
		else if(block == Blocks.stone)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved, 13, 2);
		}
		else if(block instanceof IChiselable)
		{
			Block newBlock = ((IChiselable) block).getChiseledVariant();
			int meta = ((IChiselable) block).getChiseledVariantMeta();
			world.setBlock(x, y, z, newBlock, meta, 2);
		}

		Random random = world.rand;
		Steamcraft.proxy.smokeFX(world, x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), FXSmoke.class);
		is.damageItem(1, player);

		return true;
	}
}
