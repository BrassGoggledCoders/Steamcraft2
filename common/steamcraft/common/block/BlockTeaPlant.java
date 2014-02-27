package common.steamcraft.common.block;

import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class BlockTeaPlant extends BlockFlower
{
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    protected BlockTeaPlant(int id)
    {
        super(id);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
        this.setUnlocalizedName("tea");
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id)
    {
        return id == Block.tilledField.blockID;
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        super.updateTick(world, i, j, k, random);

        if(world.getBlockLightValue(i, j + 1, k) >= 9)
        {
            int l = world.getBlockMetadata(i, j, k);

            if(l < 1)
            {
                float f = this.getGrowthRate(world, i, j, k);

                if(random.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    ++l;
                    world.setBlockMetadataWithNotify(i, j, k, l, 2);
                }
            }
        }
    }

    /**
     * Apply bonemeal to the crops.
     */
    public void fertilize(World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if(l > 1)
        {
            l = 1;
        }

        world.setBlockMetadataWithNotify(i, j, k, l, 2);
    }

    /**
     * Gets the growth rate for the crop. Setup to encourage rows by halving growth rate if there is diagonals, crops on
     * different sides that aren't opposing, and by adding growth for every crop next to this one (and for crop below
     * this one). Args: x, y, z
     */
    private float getGrowthRate(World world, int i, int j, int k)
    {
        float f = 1.0F;
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        int l1 = world.getBlockId(i - 1, j, k - 1);
        int i2 = world.getBlockId(i + 1, j, k - 1);
        int j2 = world.getBlockId(i + 1, j, k + 1);
        int k2 = world.getBlockId(i - 1, j, k + 1);
        boolean flag = j1 == this.blockID || k1 == this.blockID;
        boolean flag1 = l == this.blockID || i1 == this.blockID;
        boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

        for(int l2 = i - 1; l2 <= i + 1; ++l2)
        {
            for(int i3 = k - 1; i3 <= k + 1; ++i3)
            {
                int j3 = world.getBlockId(l2, j - 1, i3);
                float f1 = 0.0F;

                if(blocksList[j3] != null && blocksList[j3].canSustainPlant(world, l2, j - 1, i3, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if(blocksList[j3].isFertile(world, l2, j - 1, i3))
                    {
                        f1 = 3.0F;
                    }
                }
                if(l2 != i || i3 != k)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }
        if(flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int j)
    {
        if(j < 0 || j > 1)
        {
            j = 1;
        }

        return this.iconArray[j];
    }

    @Override
    public int getRenderType()
    {
        return 6;
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return ModItems.teaSeed.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return ModItems.teaLeaves.itemID;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f, int m)
    {
        super.dropBlockAsItemWithChance(world, i, j, k, l, f, 0);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if(metadata >= 1)
        {
            for(int n = 0; n < 3 + fortune; n++)
            {
                if(world.rand.nextInt(3) <= metadata)
                {
                    ret.add(new ItemStack(this.getSeedItem(), 1, 0));
                }
            }
        }

        return ret;
    }

    @Override
    public int idDropped(int i, Random random, int j)
    {
        return i == 1 ? this.getCropItem() : this.getSeedItem();
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.getSeedItem();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        this.iconArray = new Icon[2];

        for(int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = icon.registerIcon(LibInfo.SC2_PREFIX + this.getUnlocalizedName().substring(5) + "_stage_" + i);
        }
    }
}