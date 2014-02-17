package common.steamcraft.mod.common.block.tile.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.steamcraft.mod.common.block.tile.TileEntityElectricMachine;

public class SlotBattery extends Slot
{
	public SlotBattery(IInventory inventory, int i, int j, int k)
    {
        super(inventory, i, j, k);
    }
	
	@Override
    public boolean isItemValid(ItemStack stack)
    {
		if(isValidBatteryItem(stack))
			return true;
		return false;
    }
	
	public static boolean isValidBatteryItem(ItemStack stack)
	{

			return true;
	}
}
