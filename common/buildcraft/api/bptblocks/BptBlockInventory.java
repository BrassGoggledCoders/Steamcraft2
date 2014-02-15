package common.buildcraft.api.bptblocks;

import common.buildcraft.api.blueprints.BptBlock;
import common.buildcraft.api.blueprints.BptSlotInfo;
import common.buildcraft.api.blueprints.IBptContext;

import net.minecraft.inventory.IInventory;

@Deprecated
public class BptBlockInventory extends BptBlock {

	public BptBlockInventory(int blockId) {
		super(blockId);

	}

	@Override
	public void buildBlock(BptSlotInfo slot, IBptContext context) {
		super.buildBlock(slot, context);

		IInventory inv = (IInventory) context.world().getBlockTileEntity(slot.x, slot.y, slot.z);

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			inv.setInventorySlotContents(i, null);
		}

	}

}
