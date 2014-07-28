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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.ItemFluidContainer;
import steamcraft.common.tiles.TileCopperTank;

/**
 * @author warlordjones
 *
 */
public class BlockCopperTank extends BlockContainerMod
{
	public BlockCopperTank(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCopperTank();
	}
	 /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideIThink, float posXClickedIThink, float p_149727_8_, float posZClickedIThink)
    {
    	TileCopperTank tank = (TileCopperTank) world.getTileEntity(x, y, z);
    	FluidTankInfo[] info = tank.getTankInfo(ForgeDirection.getOrientation(sideIThink));
    	if(player.getHeldItem().getItem() instanceof ItemFluidContainer)
    	{
    		ItemFluidContainer container = (ItemFluidContainer) player.getHeldItem().getItem();
    	if(player.getHeldItem().getItem() instanceof ItemFluidContainer && info[0].fluid != null)
    	{
    		return true;
    	}
    	else if(container.getFluid(new ItemStack(container)) != null && info[0].capacity != tank.capacity)
    	{
    		container.drain(new ItemStack(container), 1000, true);
    		tank.fill(ForgeDirection.getOrientation(sideIThink), new FluidStack(container.getFluid(new ItemStack(container)), 1000), true);
    		return true;
    	}
    	}
    	else return false;
		return false;
    }

}
