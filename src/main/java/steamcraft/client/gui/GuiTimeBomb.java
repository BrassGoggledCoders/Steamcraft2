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

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import steamcraft.common.InitPackets;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.packets.TimeBombPacket;
import steamcraft.common.tiles.TileTimeBomb;
import steamcraft.common.tiles.container.ContainerTimeBomb;

/**
 * @author warlordjones
 * 
 */
public class GuiTimeBomb extends GuiContainer
{

	private static ResourceLocation guitexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/timebomb.png");

	private GuiTextField text;
	@SuppressWarnings("unused")
	private ContainerTimeBomb container;
	private EntityPlayer player;
	@SuppressWarnings("unused")
	private GuiTimeBomb.ChangeButton timeChangeButton;

	TileTimeBomb tile;

	public GuiTimeBomb(InventoryPlayer inv, TileTimeBomb tile)
	{
		super(new ContainerTimeBomb(inv, tile));
		this.tile = tile;
		this.container = (ContainerTimeBomb) this.inventorySlots;
		this.player = inv.player;
		//
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("Time Bomb", this.xSize - 2, this.ySize - 124, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		// final int x = (width - xSize) / 2;
		// final int y = (height - ySize) / 2;
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(this.guiLeft + 59, this.guiTop + 20, 0, this.ySize + 0, 110, 16);

		this.drawTexturedModalRect(this.guiLeft + 99, this.guiTop + 45, this.xSize, 0, 28, 21);

		this.mc.renderEngine.bindTexture(guitexture);
	}

	@Override
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.text = new GuiTextField(this.fontRendererObj, i + 62, j + 24, 103, 12);
		this.text.setTextColor(-1);
		this.text.setDisabledTextColour(-1);
		this.text.setEnableBackgroundDrawing(false);
		this.text.setMaxStringLength(4);
		this.text.setText(String.valueOf(this.tile.getTime()));
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public void keyTyped(char c, int pos)
	{
		if(!Character.isDigit(c))
			this.text.setText("");

		if(this.text.textboxKeyTyped(c, pos))
			this.updateTime();

		super.keyTyped(c, pos);
	}

	private void updateTime()
	{
		String s = "0000";

		if(this.text != null)
			s = this.text.getText();
		if(s.length() == 4)
			// {
			// this.container.updateTime(s);
			InitPackets.network.sendToServer(new TimeBombPacket(Integer.parseInt(s), this.tile.xCoord, this.tile.yCoord, this.tile.zCoord,
					this.player.dimension));
		// }
		// this.mc.thePlayer.sendQueue.addToSendQueue(new
		// C17PacketCustomPayload("SC2|TimeUpdate",
		// s.getBytes(Charsets.UTF_8)));
	}

	/**
	 * Called when the mouse is clicked.
	 */
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
	{
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.text.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		this.text.drawTextBox();
	}

	public class ChangeButton extends GuiButton
	{
		public ChangeButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_)
		{
			super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		}

	}

}
