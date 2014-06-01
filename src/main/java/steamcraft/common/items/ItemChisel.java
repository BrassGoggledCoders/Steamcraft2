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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.fx.FXSmoke;
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

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons( IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemChisel");
	}

	/**
	 * Instantiates a new item chisel.
	 */
	public ItemChisel()
	{
		setMaxStackSize(1);
		setMaxDamage(21);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#onItemUse(net.minecraft.item.ItemStack, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int, int, float, float, float)
	 */
	@Override
	public boolean onItemUse( ItemStack is,  EntityPlayer player,
			 World world,  int x,  int y,  int z,
			 int side,  float hitX,  float hitY,  float hitZ)
	{
		world.getBlock(x, y, z);

		/*
		 * if(bid == Block.blockIron.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorIron.blockID); } if(bid == Block.blockGold.blockID) {
		 * world.setBlock(i, j, k, ModBlocks.decorGold.blockID); } if(bid ==
		 * Block.blockDiamond.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorDiamond.blockID); } if(bid ==
		 * ModBlocks.blockCastIron.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorCastIron.blockID); } if(bid ==
		 * ModBlocks.blockBrass.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorBrass.blockID); } if(bid ==
		 * ModBlocks.blockVolucite.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorVolucite.blockID); } if(bid ==
		 * Block.blockLapis.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorLapis.blockID); } if(bid == Block.stone.blockID) {
		 * world.setBlock(i, j, k, ModBlocks.decorStone.blockID); } if(bid ==
		 * ModBlocks.blockUranium.blockID) { world.setBlock(i, j, k,
		 * ModBlocks.decorUranium.blockID); }
		 */

		 Random random = world.rand;
		Steamcraft.proxy.smokeFX(world, x + random.nextFloat(),
				y + random.nextFloat(), z + random.nextFloat(), FXSmoke.class);
		is.damageItem(1, player);

		return true;
	}
}
