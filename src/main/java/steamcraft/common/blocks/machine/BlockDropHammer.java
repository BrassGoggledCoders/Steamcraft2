package steamcraft.common.blocks.machine;

import steamcraft.common.Steamcraft;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDropHammer extends BlockContainerMod
{

	protected BlockDropHammer(Material mat)
	{
		super(mat);
		setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return null;
	}

}
