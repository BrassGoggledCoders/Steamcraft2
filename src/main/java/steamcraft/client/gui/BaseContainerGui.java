package steamcraft.client.gui;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StatCollector;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import boilerplate.common.baseclasses.BaseTileWithInventory;

public abstract class BaseContainerGui extends GuiContainer
{
	protected BaseTileWithInventory tile;

	public BaseContainerGui(Container p_i1072_1_)
	{
		super(p_i1072_1_);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		if(this.tile == null)
			return;

		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		for(Slot slot : (List<Slot>) this.inventorySlots.inventorySlots)
		{
			if(!slot.getHasStack() && this.mouseInside(slot, mouseX - x, mouseY - y))
			{
				if(slot.slotNumber < this.tile.getSizeInventory())
				{
					String tt = this.getSlotTooltipUnloc(slot.slotNumber);
					if(!Strings.isNullOrEmpty(tt))
					{
						this.func_146283_a(Lists.newArrayList(StatCollector.translateToLocal(tt)), mouseX - x, mouseY - y);
					}
				}
			}
		}
	}

	private boolean mouseInside(Slot slot, int x, int y)
	{
		return (x >= slot.xDisplayPosition) && (x <= (slot.xDisplayPosition + 16)) && (y >= slot.yDisplayPosition) && (y <= (slot.yDisplayPosition + 16));
	}

	public String getSlotTooltipUnloc(int slotNumber)
	{
		return this.tile.getInventoryName().replaceAll(" ", "").toLowerCase() + ".slot." + slotNumber + ".tooltip";
	}
}
