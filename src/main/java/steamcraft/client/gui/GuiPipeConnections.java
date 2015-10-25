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
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.container.ContainerPipeConnections;

/**
 * @author decebaldecebal
 *
 */
public class GuiPipeConnections extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/changeextractions.png");

	private InventoryPlayer player;
	private TileCopperPipe tile;

	public GuiPipeConnections(InventoryPlayer player, TileCopperPipe tile)
	{
		super(new ContainerPipeConnections(player));

		this.xSize = 206;
		this.ySize = 196;
		this.player = player;
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("Change Extraction", 60, 6, 4210752);
		this.fontRendererObj.drawString("North:", 20, 26, 4210752);
		this.fontRendererObj.drawString("South:", 115, 26, 4210752);
		this.fontRendererObj.drawString("West:", 20, 56, 4210752);
		this.fontRendererObj.drawString("East:", 115, 56, 4210752);
		this.fontRendererObj.drawString("Up:", 20, 86, 4210752);
		this.fontRendererObj.drawString("Down:", 115, 86, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.clear();

		ForgeDirection[] connections = tile.getConnections();

		GuiButton north = new GuiButton(0, guiLeft + 55, guiTop + 20, 44, 20, "Insert");
		GuiButton south = new GuiButton(4, guiLeft + 150, guiTop + 20, 44, 20, "Insert");
		if (connections[2] == null)
			north.enabled = false;
		if (connections[3] == null)
			south.enabled = false;

		GuiButton west = new GuiButton(2, guiLeft + 55, guiTop + 50, 44, 20, "Insert");
		GuiButton east = new GuiButton(3, guiLeft + 150, guiTop + 50, 44, 20, "Insert");
		if (connections[4] == null)
			west.enabled = false;
		if (connections[5] == null)
			east.enabled = false;

		GuiButton up = new GuiButton(1, guiLeft + 55, guiTop + 80, 44, 20, "Insert");
		GuiButton down = new GuiButton(5, guiLeft + 150, guiTop + 80, 44, 20, "Insert");
		if (connections[0] == null)
			up.enabled = false;
		if (connections[1] == null)
			down.enabled = false;

		buttonList.add(north);
		buttonList.add(south);
		buttonList.add(west);
		buttonList.add(east);
		buttonList.add(up);
		buttonList.add(down);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{

		}
	}
}
