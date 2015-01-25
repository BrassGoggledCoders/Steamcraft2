package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

public class BlockSiren extends Block
{
	private boolean isOn;
	String sound;

	public BlockSiren(Material p_i45394_1_, boolean isOn, String sound)
	{
		super(p_i45394_1_);
		isOn = this.isOn;
		this.sound = sound;
		if(!isOn)
			setCreativeTab(Steamcraft.tabSC2);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			else if(!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, ModInfo.PREFIX + sound, 5F, 1F);
			}
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if(!world.isRemote)
		{
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			else if(!isOn && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, ModInfo.PREFIX + sound, 5F, 1F);
			}
		}
	}
}
