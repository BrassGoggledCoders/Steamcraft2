package common.steamcraft.common.block;

import common.steamcraft.common.SC2;
import common.steamcraft.common.block.tile.TileEntityHammerHead;
import common.steamcraft.common.lib2.CreativeTabsMod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHammerHead extends BlockContainer{

	protected BlockHammerHead(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(CreativeTabsMod.tabSCBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHammerHead();
	}
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
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
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return -1;
    }
}
