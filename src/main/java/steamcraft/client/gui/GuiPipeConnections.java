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

		buttonList.add(new GuiButton(0, guiLeft + 50, guiTop + 20, 40, 20, "North"));
		buttonList.add(new GuiButton(1, guiLeft + 50, guiTop + 80, 40, 20, "South"));
		buttonList.add(new GuiButton(2, guiLeft + 10, guiTop + 50, 40, 20, "East"));
		buttonList.add(new GuiButton(3, guiLeft + 90, guiTop + 50, 40, 20, "West"));
		buttonList.add(new GuiButton(4, guiLeft + 150, guiTop + 20, 40, 20, "Up"));
		buttonList.add(new GuiButton(5, guiLeft + 150, guiTop + 80, 40, 20, "Down"));
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
			System.out.println("Button 0 pressed");
	}
}
