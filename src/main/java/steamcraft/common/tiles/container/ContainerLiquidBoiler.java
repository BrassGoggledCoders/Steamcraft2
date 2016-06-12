
package steamcraft.common.tiles.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import steamcraft.common.tiles.TileLiquidBoiler;
import steamcraft.common.tiles.container.slot.SlotFluidContainer;

/**
 * @author Decebaldecebal
 *
 */
public class ContainerLiquidBoiler extends ContainerBaseBoiler
{
	private TileLiquidBoiler tileent;
	private int lastFuelLevel = 0;

	public ContainerLiquidBoiler(InventoryPlayer player, TileLiquidBoiler tileLiquidBoiler)
	{
		super(player, tileLiquidBoiler);
		this.tileent = (TileLiquidBoiler) super.tileent;
		this.addSlotToContainer(new SlotFluidContainer(tileLiquidBoiler, 0, 116, 61));
		this.addSlotToContainer(new SlotFluidContainer(tileLiquidBoiler, 1, 135, 61));
		this.addSlotToContainer(new SlotFluidContainer(tileLiquidBoiler, 2, 154, 61));
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.tileent.fuelTank.getFluidAmount());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (Object obj : this.crafters)
		{
			if (this.lastFuelLevel != this.tileent.fuelTank.getFluidAmount())
				((ICrafting) obj).sendProgressBarUpdate(this, 2, this.tileent.fuelTank.getFluidAmount());
		}
		this.lastFuelLevel = this.tileent.fuelTank.getFluidAmount();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		super.updateProgressBar(par1, par2);
		if (par1 == 2)
			// TODO
			this.tileent.fuelTank.setFluid(new FluidStack(FluidRegistry.getFluid("whaleoil"), par2));
	}
}
