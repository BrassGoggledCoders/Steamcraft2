
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.api.tile.ISpannerTile;
import steamcraft.common.init.InitPackets;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.packets.UpdateExtractionPacket;
import steamcraft.common.tiles.container.ContainerChangeExtractions;

/**
 * @author decebaldecebal
 *
 */
public class GuiChangeExtractions extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/changeextractions.png");
	private static String[] buttonNames = new String[] { "Insert", "Extract" };

	private TileEntity tile;
	private int worldId;

	public GuiChangeExtractions(TileEntity tile, int worldId)
	{
		super(new ContainerChangeExtractions());

		this.xSize = 206;
		this.ySize = 111;
		this.tile = tile;
		this.worldId = worldId;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("Change Extraction", 60, 6, 4210752);
		this.fontRendererObj.drawString("Up:", 20, 26, 4210752);
		this.fontRendererObj.drawString("Down:", 115, 26, 4210752);
		this.fontRendererObj.drawString("North:", 20, 56, 4210752);
		this.fontRendererObj.drawString("South:", 115, 56, 4210752);
		this.fontRendererObj.drawString("West:", 20, 86, 4210752);
		this.fontRendererObj.drawString("East:", 115, 86, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();

		ISpannerTile spannerTile = (ISpannerTile) this.tile;

		ForgeDirection[] connections = spannerTile.getExtractableConnections();
		ForgeDirection[] extractions = spannerTile.getExtractions();

		GuiButton up = this.createGuiButton(0, this.guiLeft + 55, this.guiTop + 20, 44, 20, connections, extractions);
		GuiButton down = this.createGuiButton(1, this.guiLeft + 150, this.guiTop + 20, 44, 20, connections, extractions);

		GuiButton north = this.createGuiButton(2, this.guiLeft + 55, this.guiTop + 50, 44, 20, connections, extractions);
		GuiButton south = this.createGuiButton(3, this.guiLeft + 150, this.guiTop + 50, 44, 20, connections, extractions);

		GuiButton west = this.createGuiButton(4, this.guiLeft + 55, this.guiTop + 80, 44, 20, connections, extractions);
		GuiButton east = this.createGuiButton(5, this.guiLeft + 150, this.guiTop + 80, 44, 20, connections, extractions);

		this.buttonList.add(north);
		this.buttonList.add(south);
		this.buttonList.add(west);
		this.buttonList.add(east);
		this.buttonList.add(up);
		this.buttonList.add(down);
	}

	private GuiButton createGuiButton(int index, int x, int y, int xx, int yy, ForgeDirection[] connections, ForgeDirection[] extractions)
	{
		GuiButton button = new GuiButton(index, x, y, xx, yy, extractions[index] == null ? buttonNames[0] : buttonNames[1]);

		if (connections[index] == null)
			button.enabled = false;

		return button;
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			InitPackets.network
					.sendToServer(new UpdateExtractionPacket(this.worldId, this.tile.xCoord, this.tile.yCoord, this.tile.zCoord, button.id));
			button.displayString = button.displayString.equals(buttonNames[0]) ? buttonNames[1] : buttonNames[0];
		}
	}
}
