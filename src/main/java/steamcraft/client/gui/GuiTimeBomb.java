package steamcraft.client.gui;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import steamcraft.common.InitPackets;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.packets.TimeBombPacket;
import steamcraft.common.tiles.TileTimeBomb;
import steamcraft.common.tiles.container.ContainerTimeBomb;

public class GuiTimeBomb extends GuiContainer
{
	private static ResourceLocation guitexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/timebomb.png");

	private GuiTextField text;
	private ContainerTimeBomb container;

	TileTimeBomb tile;
	public GuiTimeBomb(InventoryPlayer inv, TileTimeBomb tile)
	{
		super(new ContainerTimeBomb(inv, tile));
		this.tile = tile;
		this.container = (ContainerTimeBomb) this.inventorySlots;
		//
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.renderEngine.bindTexture(guitexture);
		final int x = (width - xSize) / 2;
		final int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		this.drawTexturedModalRect(x + 59, y + 20, 0, this.ySize + 0, 110, 16);

        this.drawTexturedModalRect(x + 99, y + 45, this.xSize, 0, 28, 21);

		mc.renderEngine.bindTexture(guitexture);
	}
	@Override
	public void initGui()
    {
	    Keyboard.enableRepeatEvents(true);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		text = new GuiTextField(fontRendererObj, i + 62, j + 24, 103, 12);
		text.setTextColor(-1);
	    text.setDisabledTextColour(-1);
	    text.setEnableBackgroundDrawing(false);
	    text.setMaxStringLength(4);
	    text.setText(String.valueOf(tile.getTime()));
    }
	@Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
	@Override
	public void keyTyped(char c, int p_73869_2_)
    {
        if (this.text.textboxKeyTyped(c, p_73869_2_))
        {
            this.updateTime();
        }

        super.keyTyped(c, p_73869_2_);
    }
	private void updateTime()
    {
		String s = "0000";

		if(text != null)
		{
			s = text.getText();
		}
		if(s.length() == 4)
		//{
		//this.container.updateTime(s);
		InitPackets.network.sendToServer(new TimeBombPacket(Integer.parseInt(s), this.tile.xCoord, this.tile.yCoord, this.tile.zCoord));
		//}
		//this.mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("SC2|TimeUpdate", s.getBytes(Charsets.UTF_8)));
    }
	  /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.text.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.text.drawTextBox();
    }


}
