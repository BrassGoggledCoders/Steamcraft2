package xyz.brassgoggledcoders.steamcraft.common.tiles.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidContainerRegistry;

public class SlotFluidContainer extends Slot
{
	public SlotFluidContainer(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return FluidContainerRegistry.isContainer(stack) || FluidContainerRegistry.isBucket(stack);
	}
}
