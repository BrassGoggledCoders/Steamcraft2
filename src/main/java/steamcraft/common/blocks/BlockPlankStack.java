/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.InitBlocks;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlankStack extends BaseBlock
{
	// TODO: Use block metadata

	public BlockPlankStack(Material mat)
	{
		super(mat);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random p_149745_1_)
	{
		return 6;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(Blocks.planks);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	{
		p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, this, this.tickRate(p_149726_1_));
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if(!p_149674_1_.isRemote)
		{
			this.func_149830_m(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
		}
	}

	private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_)
	{
		if(func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && (p_149830_3_ >= 0))
		{
			byte b0 = 32;

			if(p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0))
			{
				if(!p_149830_1_.isRemote)
				{
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(p_149830_1_, p_149830_2_ + 0.5F, p_149830_3_ + 0.5F, p_149830_4_ + 0.5F,
							this, p_149830_1_.getBlockMetadata(p_149830_2_, p_149830_3_, p_149830_4_));
					this.func_149829_a(entityfallingblock);
					p_149830_1_.spawnEntityInWorld(entityfallingblock);
				}
			}
		}
	}

	protected void func_149829_a(EntityFallingBlock p_149829_1_)
	{
	}

	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World p_149738_1_)
	{
		return 2;
	}

	public static boolean func_149831_e(World p_149831_0_, int p_149831_1_, int p_149831_2_, int p_149831_3_)
	{
		Block block = p_149831_0_.getBlock(p_149831_1_, p_149831_2_, p_149831_3_);

		if(block.isAir(p_149831_0_, p_149831_1_, p_149831_2_, p_149831_3_))
		{
			return true;
		}
		else if(block == Blocks.fire)
		{
			return true;
		}
		else
		{
			Material material = block.getMaterial();
			return material == Material.water ? true : material == Material.lava;
		}
	}

	public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
	{
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for(int var4 = 0; var4 < 6; ++var4)
			l.add(new ItemStack(InitBlocks.blockPlankStack, 1, var4));
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockPlankStackRI;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
