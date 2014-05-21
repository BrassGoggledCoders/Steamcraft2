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
 * File created @ [3/14/14, 20:57]
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCosmeticSolid extends Block
{
	private Icon[] icon = new Icon[5];

	private boolean powered;

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		if (metadata == 8) // This is for the brass wood. It's kinda messed up. Maybe a separate file would do?
		{
			int i = metadata & 11;
			int j = metadata & 3;
			return i == 0 && (side == 1 || side == 0) ? this.icon[0] : (i == 4 && (side == 5 || side == 4) ? this.icon[0] : (i == 8 && (side == 2 || side == 3) ? this.icon[0] : this.icon[0]));
		}

		return this.icon[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLog");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLogTop");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLeaves");
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockLampOff");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockLampOn");
	}

	public BlockCosmeticSolid(int id)
	{
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("blockCosmeticSolid");
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
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList drop = new ArrayList();

		switch (metadata) {
		case 0:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 0));
		case 1:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 1));
		case 2:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 2));
		case 3:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 3));
		case 4:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 4));
		case 5:
			drop.add(new ItemStack(ConfigBlocks.blockCosmetic, 1, 5));
		}

		return drop;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 6))
		{
			entity.attackEntityFrom(DamageSource.magic, 1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 6))
		{
			Utils.sparkle(world, x, y, z, "reddust");
		}
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 10))
			return this.blockMaterial == Material.leaves;
		else
			return false;
	}

	@Override // Something is wrong with the functionality of this method. You'll see when you place a Brass Log.
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 8))
		{
			int meta = metadata & 3;
			byte byte0 = 0;

			switch (side)
			{
			case 0:
			case 1:
				byte0 = 0;
				break;
			case 2:
			case 3:
				byte0 = 8;
				break;
			case 4:
			case 5:
				byte0 = 4;
			}

			return meta | byte0;
		}
		else
			return metadata;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int bid, int meta)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 8))
		{
			byte byte0 = 4;
			int factor = byte0 + 1;

			if (world.checkChunksExist(x - factor, y - factor, z - factor, x + factor, y + factor, z + factor))
			{
				for (int ix = -byte0; ix <= byte0; ++ix)
				{
					for (int iy = -byte0; iy <= byte0; ++iy)
					{
						for (int iz = -byte0; iz <= byte0; ++iz)
						{
							bid = world.getBlockId(x + ix, y + iy, z + iz);
							Block block = Block.blocksList[bid];
							
							if (block instanceof BlockLeaves)
							{
								block.beginLeavesDecay(world, x + ix, y + iy, z + iz);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 8))
			return true;
		else
			return false;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		if ((world.getBlockId(x, y, z) == this.blockID) && (world.getBlockMetadata(x, y, z) == 8))
			return true;
		else
			return false;
	}

	// I don't know why it says blockCastIronLamp, it's just supposed to be that lamp block.
	// Make sure you do a metadata check before implementing this.
	
	/*
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			if ((this.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12);
			}
			else if ((!this.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 11);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int bid)
	{
		if (!world.isRemote)
		{
			if ((this.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12);
			}
			else if ((!this.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z)))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 11);
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if ((!world.isRemote && this.powered) && (!world.isBlockIndirectlyGettingPowered(x, y, z)))
		{
			world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12);
		}
	}*/
}
