
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidContainerItem;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileCopperTank;

/**
 * @author Decebaldecebal
 *
 */
public class BlockCopperTank extends BaseContainerBlock
{
	public static float pixel = 1 / 16f;

	public BlockCopperTank(Material mat)
	{
		super(mat);

		this.setBlockBounds(1.5f * pixel, 0, 1.5f * pixel, 1 - (1.5f * pixel), 1, 1 - (1.5f * pixel));
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCopperTank();
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

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCopperTankRI;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ)
	{
		if (!world.isRemote)
		{
			TileCopperTank tank = (TileCopperTank) world.getTileEntity(x, y, z);
			ItemStack stack = player.getHeldItem();

			if ((stack != null) && (stack.getItem() instanceof IFluidContainerItem))
			{
				IFluidContainerItem container = (IFluidContainerItem) stack.getItem();

				int amount = 0;
				if ((amount = tank.fill(ForgeDirection.getOrientation(side), container.getFluid(stack), false)) > 0)
				{
					amount = tank.fill(ForgeDirection.getOrientation(side), container.getFluid(stack), true);
					container.drain(stack, amount, true);
					return true;
				}
			}
			else
				return false;
		}
		return true;
	}
}
