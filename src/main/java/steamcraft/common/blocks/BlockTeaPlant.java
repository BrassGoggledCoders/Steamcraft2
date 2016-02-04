
package steamcraft.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class BlockTeaPlant extends BlockCrops implements IPlantable
{
	IIcon[] iconArray = new IIcon[2];

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if ((meta < 0) || (meta > 1))
			meta = 1;
		return this.iconArray[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.iconArray[0] = ir.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5) + "_0");
		this.iconArray[1] = ir.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5) + "_1");
	}

	public BlockTeaPlant()
	{
		super();
		this.setHardness(0.0F);
		this.setResistance(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		this.disableStats();
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);

		if (world.getBlockLightValue(x, y + 1, z) >= 9)
		{
			int metadata = world.getBlockMetadata(x, y, z);

			if (metadata < 1)
			{
				float growthRate = this.getGrowthRate(world, x, y, z);

				if (random.nextInt((int) (15.0F / growthRate) + 1) == 0)
				{
					++metadata;
					world.setBlockMetadataWithNotify(x, y, z, metadata, 1);
				}
			}
		}
	}

	public void fertilize(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
	}

	private float getGrowthRate(World world, int x, int y, int z)
	{
		float f = 1.0F;

		for (int l1 = x - 1; l1 <= (x + 1); ++l1)
			for (int i3 = z - 1; i3 <= (z + 1); ++i3)
			{
				Block block = world.getBlock(l1, y - 1, i3);
				float growthRate = 0.0F;

				if (block.canSustainPlant(world, l1, y - 1, i3, ForgeDirection.UP, this))
				{
					growthRate = 1.0F;

					if (block.isFertile(world, l1, y - 1, i3))
						growthRate = 3.0F;
				}
				if ((l1 != x) || (i3 != z))
					growthRate /= 4.0F;

				f += growthRate;
			}

		return f;
	}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
	{
		return p_149851_1_.getBlockMetadata(p_149851_2_, p_149851_3_, p_149851_4_) != 1;
	}

	@Override
	public int getRenderType()
	{
		return 6;
	}

	@Override
	protected Item func_149866_i()
	{
		return InitItems.itemTeaSeed;
	}

	@Override
	protected Item func_149865_P()
	{
		return InitItems.itemTeaLeaf;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList();

		int count = this.quantityDropped(metadata, fortune, world.rand);
		for (int i = 0; i < count; i++)
		{
			Item item = this.getItemDropped(metadata, world.rand, fortune);
			if (item != null)
			{
				ret.add(new ItemStack(item, 1, this.damageDropped(metadata)));
			}
		}

		if (metadata != 0)
		{
			ret.add(new ItemStack(this.func_149866_i(), 1, this.damageDropped(metadata)));
			for (int i = 0; i < count; ++i)
			{
				ret.add(new ItemStack(this.func_149866_i(), world.rand.nextInt(1), this.damageDropped(metadata)));
			}
		}

		return ret;
	}
}