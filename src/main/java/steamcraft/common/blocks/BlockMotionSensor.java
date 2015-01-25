package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BaseContainerBlock;
import steamcraft.common.tiles.TileMotionSensor;

public class BlockMotionSensor extends BaseContainerBlock
{
	public boolean isOn;

	public BlockMotionSensor(Material p_i45386_1_, boolean isOn)
	{
		super(p_i45386_1_);
		this.isOn = isOn;
		if(!isOn)
		{
			setCreativeTab(Steamcraft.tabSC2);
		};
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileMotionSensor();
	}

	public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
	{
		return isOn;
	}

	@Override
	public boolean canProvidePower()
	{
		return isOn;
	}

}
