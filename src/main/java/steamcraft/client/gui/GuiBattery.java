package steamcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.container.ContainerBattery;

public class GuiBattery extends GuiContainer
{
	/** The tile. */
	private TileBattery tile;

	public GuiBattery(Container par1Container)
	{
		super(par1Container);
		// TODO Auto-generated constructor stub
	}

	public GuiBattery(InventoryPlayer inventory, TileBattery tile)
	{
		super(new ContainerBattery(inventory, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{

	}

}
