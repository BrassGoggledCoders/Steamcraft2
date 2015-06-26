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
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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
		
		this.setBlockBounds(2 * pixel, 0, 2 * pixel, 1 - 2 * pixel, 1, 1 - 2 * pixel);
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
	
	/*
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideIThink, float posXClickedIThink,
			float p_149727_8_, float posZClickedIThink)
	{
		if (!world.isRemote)
		{
			TileCopperTank tank = (TileCopperTank) world.getTileEntity(x, y, z);
			
			if((player.getHeldItem() != null) && (player.getHeldItem().getItem() instanceof ItemFluidContainer))
			{
				ItemFluidContainer container = (ItemFluidContainer) player.getHeldItem().getItem();
				
				if(tank.tank.getFluid() != null)
					return true;
				else if((container.getFluid(player.getHeldItem()) != null) && (TileCopperTank.capacity - tank.tank.getFluidAmount()
						<= container.getFluid(player.getHeldItem()).amount))
				{
					tank.fill(ForgeDirection.getOrientation(sideIThink), container.getFluid(player.getHeldItem()).copy(), true);
					container.drain(player.getHeldItem(), container.getFluid(player.getHeldItem()).amount, true);
					return true;
				}
			}
			else
				return false;
		}
		return false;
	}
	*/
}
