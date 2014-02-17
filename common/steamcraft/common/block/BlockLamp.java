package common.steamcraft.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLamp extends BlockMod
{
	private boolean powered;
	
	protected BlockLamp(int id, boolean flag)
	{
		super(id, Material.iron);
		this.powered = flag;
		this.setHardness(2.0F);
		this.setStepSound(Block.soundGlassFootstep);
		
		if(this.powered)
		{
			this.setLightValue(0.98F);
		}
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k)
    {
        if(!world.isRemote)
        {
            if(this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
            {
                world.scheduleBlockUpdate(i, j, k, this.blockID, 4);
            } else if(!this.powered && world.isBlockIndirectlyGettingPowered(i, j, k))
            {
                world.setBlock(i, j, k, ModBlocks.lampOn.blockID, 0, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(!world.isRemote)
        {
            if(this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
            {
                world.scheduleBlockUpdate(i, j, k, this.blockID, 4);
            } else if(!this.powered && world.isBlockIndirectlyGettingPowered(i, j, k))
            {
                world.setBlock(i, j, k, ModBlocks.lampOn.blockID, 0, 2);
            }
        }
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(!world.isRemote && this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
        {
            world.setBlock(i, j, k, ModBlocks.lampOff.blockID, 0, 2);
        }
    }

    @Override
    public int idDropped(int i, Random random, int j)
    {
        return ModBlocks.lampOff.blockID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World world, int i, int j, int k)
    {
        return ModBlocks.lampOff.blockID;
    }
}