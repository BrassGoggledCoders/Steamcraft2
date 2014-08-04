package steamcraft.client.gui;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.io.Charsets;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
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
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.renderEngine.bindTexture(guitexture);
		final int x = (width - xSize) / 2;
		final int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

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
    }
	@Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (this.text.textboxKeyTyped(p_73869_1_, p_73869_2_))
        {
            this.updateTime();
        }
        else
        {
            super.keyTyped(p_73869_1_, p_73869_2_);
        }
    }
	private void updateTime()
    {
		String s = text.getText();

        this.container.updateTime(s);
        this.mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("SC2|TimeUpdate", s.getBytes(Charsets.UTF_8)));
    }
}
