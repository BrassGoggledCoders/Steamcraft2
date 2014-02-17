package common.steamcraft.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BlockCopperWire extends Block
{
    /**
     * When false, power transmission methods do not look at other copper wires. Used internally during
     * updateCurrentStrength.
     */
    private boolean wiresProvidePower = true;
    private Set blocksNeedingUpdate = new HashSet();
    @SideOnly(Side.CLIENT)
    private Icon icon_cross;
    @SideOnly(Side.CLIENT)
    private Icon icon_line;
    @SideOnly(Side.CLIENT)
    private Icon icon_cross_overlay;
    @SideOnly(Side.CLIENT)
    private Icon icon_line_overlay;

    public BlockCopperWire(int id)
    {
        super(id, Material.circuits);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setHardness(0.0F);
        this.setStepSound(this.soundPowderFootstep);
        this.setUnlocalizedName("redstoneDust");
        this.disableStats();
        this.setTextureName("redstone_dust");
        this.setCreativeTab((CreativeTabs)null);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        this.icon_cross = icon.registerIcon(this.getTextureName() + "_" + "cross");
        this.icon_line = icon.registerIcon(this.getTextureName() + "_" + "line");
        this.icon_cross_overlay = icon.registerIcon(this.getTextureName() + "_" + "cross_overlay");
        this.icon_line_overlay = icon.registerIcon(this.getTextureName() + "_" + "line_overlay");
        this.blockIcon = this.icon_cross;
    }

    @SideOnly(Side.CLIENT)
    public static Icon getRedstoneWireIcon(String s)
    {
        return s.equals("cross") ? ModPowerBlocks.copperWire.icon_cross : (s.equals("line") ? ModPowerBlocks.copperWire.icon_line : (s.equals("cross_overlay") ? ModPowerBlocks.copperWire.icon_cross_overlay : (s.equals("line_overlay") ? ModPowerBlocks.copperWire.icon_line_overlay : null)));
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 5;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 0x800000; //8388608;
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || par1World.getBlockId(par2, par3 - 1, par4) == Block.glowStone.blockID;
    }

    /**
     * Sets the strength of the wire current (0-15) for this block based on neighboring blocks and propagates to
     * neighboring redstone wires
     */
    private void updateAndPropagateCurrentStrength(World par1World, int par2, int par3, int par4)
    {
        this.calculateCurrentChanges(par1World, par2, par3, par4, par2, par3, par4);
        ArrayList arraylist = new ArrayList(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (int l = 0; l < arraylist.size(); ++l)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l);
            par1World.notifyBlocksOfNeighborChange(chunkposition.x, chunkposition.y, chunkposition.z, this.blockID);
        }
    }

    private void calculateCurrentChanges(World par1World, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        int k1 = par1World.getBlockMetadata(par2, par3, par4);
        byte b0 = 0;
        int l1 = this.getMaxCurrentStrength(par1World, par5, par6, par7, b0);
        this.wiresProvidePower = false;
        int i2 = par1World.getStrongestIndirectPower(par2, par3, par4);
        this.wiresProvidePower = true;

        if (i2 > 0 && i2 > l1 - 1)
        {
            l1 = i2;
        }

        int j2 = 0;

        for (int k2 = 0; k2 < 4; ++k2)
        {
            int l2 = par2;
            int i3 = par4;

            if (k2 == 0)
            {
                l2 = par2 - 1;
            }

            if (k2 == 1)
            {
                ++l2;
            }

            if (k2 == 2)
            {
                i3 = par4 - 1;
            }

            if (k2 == 3)
            {
                ++i3;
            }

            if (l2 != par5 || i3 != par7)
            {
                j2 = this.getMaxCurrentStrength(par1World, l2, par3, i3, j2);
            }

            if (par1World.isBlockNormalCube(l2, par3, i3) && !par1World.isBlockNormalCube(par2, par3 + 1, par4))
            {
                if ((l2 != par5 || i3 != par7) && par3 >= par6)
                {
                    j2 = this.getMaxCurrentStrength(par1World, l2, par3 + 1, i3, j2);
                }
            }
            else if (!par1World.isBlockNormalCube(l2, par3, i3) && (l2 != par5 || i3 != par7) && par3 <= par6)
            {
                j2 = this.getMaxCurrentStrength(par1World, l2, par3 - 1, i3, j2);
            }
        }

        if (j2 > l1)
        {
            l1 = j2 - 1;
        }
        else if (l1 > 0)
        {
            --l1;
        }
        else
        {
            l1 = 0;
        }

        if (i2 > l1 - 1)
        {
            l1 = i2;
        }

        if (k1 != l1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l1, 2);
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2 - 1, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2 + 1, par3, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 - 1, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3 + 1, par4));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 - 1));
            this.blocksNeedingUpdate.add(new ChunkPosition(par2, par3, par4 + 1));
        }
    }

    /**
     * Calls World.notifyBlocksOfNeighborChange() for all neighboring blocks, but only if the given block is a redstone
     * wire.
     */
    private void notifyWireNeighborsOfNeighborChange(World par1World, int par2, int par3, int par4)
    {
        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (!par1World.isRemote)
        {
            this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);

            if (par1World.isBlockNormalCube(par2 - 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2 + 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 - 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 + 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
            }
        }
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);

        if (!par1World.isRemote)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3, par4);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 - 1);
            this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3, par4 + 1);

            if (par1World.isBlockNormalCube(par2 - 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 - 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2 + 1, par3, par4))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 + 1, par4);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2 + 1, par3 - 1, par4);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 - 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 - 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 - 1);
            }

            if (par1World.isBlockNormalCube(par2, par3, par4 + 1))
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 + 1, par4 + 1);
            }
            else
            {
                this.notifyWireNeighborsOfNeighborChange(par1World, par2, par3 - 1, par4 + 1);
            }
        }
    }

    /**
     * Returns the current strength at the specified block if it is greater than the passed value, or the passed value
     * otherwise. Signature: (world, x, y, z, strength)
     */
    private int getMaxCurrentStrength(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.getBlockId(par2, par3, par4) != this.blockID)
        {
            return par5;
        }
        else
        {
            int i1 = par1World.getBlockMetadata(par2, par3, par4);
            return i1 > par5 ? i1 : par5;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            boolean flag = this.canPlaceBlockAt(par1World, par2, par3, par4);

            if (flag)
            {
                this.updateAndPropagateCurrentStrength(par1World, par2, par3, par4);
            }
            else
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
                par1World.setBlockToAir(par2, par3, par4);
            }

            super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3) // TODO:
    {
        return Item.redstone.itemID;
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return !this.wiresProvidePower ? 0 : this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (!this.wiresProvidePower)
        {
            return 0;
        }
        else
        {
            int i1 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

            if (i1 == 0)
            {
                return 0;
            }
            else if (par5 == 1)
            {
                return i1;
            }
            else
            {
                boolean flag = isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3, par4, 1) || !par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 - 1, par4, -1);
                boolean flag1 = isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3, par4, 3) || !par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 - 1, par4, -1);
                boolean flag2 = isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 - 1, 2) || !par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 - 1, -1);
                boolean flag3 = isPoweredOrRepeater(par1IBlockAccess, par2, par3, par4 + 1, 0) || !par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 - 1, par4 + 1, -1);

                if (!par1IBlockAccess.isBlockNormalCube(par2, par3 + 1, par4))
                {
                    if (par1IBlockAccess.isBlockNormalCube(par2 - 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 - 1, par3 + 1, par4, -1))
                    {
                        flag = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2 + 1, par3, par4) && isPoweredOrRepeater(par1IBlockAccess, par2 + 1, par3 + 1, par4, -1))
                    {
                        flag1 = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 - 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 - 1, -1))
                    {
                        flag2 = true;
                    }

                    if (par1IBlockAccess.isBlockNormalCube(par2, par3, par4 + 1) && isPoweredOrRepeater(par1IBlockAccess, par2, par3 + 1, par4 + 1, -1))
                    {
                        flag3 = true;
                    }
                }

                return !flag2 && !flag1 && !flag && !flag3 && par5 >= 2 && par5 <= 5 ? i1 : (par5 == 2 && flag2 && !flag && !flag1 ? i1 : (par5 == 3 && flag3 && !flag && !flag1 ? i1 : (par5 == 4 && flag && !flag2 && !flag3 ? i1 : (par5 == 5 && flag1 && !flag2 && !flag3 ? i1 : 0))));
            }
        }
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return this.wiresProvidePower;
    }

    /**
     * Returns true if redstone wire can connect to the specified block. Params: World, X, Y, Z, side (not a normal
     * notch-side, this can be 0, 1, 2, 3 or -1)
     */
    public static boolean isPowerProviderOrWire(IBlockAccess world, int i, int j, int k, int side) // TODO:
    {
        int bid = world.getBlockId(i, j, k);

        if(bid == Block.redstoneWire.blockID)
        {
            return true;
        } else if(bid == 0)
        {
            return false;
        } else if(!Block.redstoneRepeaterIdle.func_94487_f(bid))
        {
            return (Block.blocksList[bid] != null && Block.blocksList[bid].canConnectRedstone(world, i, j, k, side));
        } else
        {
            int meta = world.getBlockMetadata(i, j, k);
            return side == (meta & 3) || side == Direction.rotateOpposite[meta & 3];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        int meta = world.getBlockMetadata(i, j, k);

        if(meta > 0)
        {
        	/*
            double d0 = (double)i + 0.5D + ((double)random.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)j + 0.0625F);
            double d2 = (double)k + 0.5D + ((double)random.nextFloat() - 0.5D) * 0.2D;
            float f = (float)l / 15.0F;
            float f1 = f * 0.6F + 0.4F;

            if(l == 0)
            {
                f1 = 0.0F;
            }

            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;

            if(f2 < 0.0F)
            {
                f2 = 0.0F;
            }
            if(f3 < 0.0F)
            {
                f3 = 0.0F;
            }

            world.spawnParticle("reddust", d0, d1, d2, (double)f1, (double)f2, (double)f3);
            */
        	
        	double d = (double)i + 0.5D + ((double)random.nextFloat() - 0.5D) * 0.20000000000000001D;
            double d1 = (float)j + 0.0625F;
            double d2 = (double)k + 0.5D + ((double)random.nextFloat() - 0.5D) * 0.20000000000000001D;
            float f = 1 / 15F;
            float f1 = -1;
            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;
           
            if(f2 < 0.0F)
            {
                f2 = 0.7F;
            }
            if(f3 < 0.0F)
            {
                f3 = 1.0F;
            }
           
            world.spawnParticle("reddust", d, d1, d2, f1, f2, f3);
        }
    }

    /**
     * Returns true if the block coordinate passed can provide power, or is a copper wire, or if its a diode that
     * is powered.
     */
    public static boolean isPoweredOrRepeater(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4) // TODO
    {
        if(isPowerProviderOrWire(par0IBlockAccess, par1, par2, par3, par4))
        {
            return true;
        }
        else
        {
            int i1 = par0IBlockAccess.getBlockId(par1, par2, par3);

            if (i1 == Block.redstoneRepeaterActive.blockID)
            {
                int j1 = par0IBlockAccess.getBlockMetadata(par1, par2, par3);
                return par4 == (j1 & 3);
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4) // TODO: Change value
    {
        return Item.redstone.itemID;
    }
}