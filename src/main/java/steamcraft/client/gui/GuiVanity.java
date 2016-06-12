
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
@SideOnly(Side.CLIENT)
public class GuiVanity extends GuiContainer
{
	private float xSize_lo;

	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation(ModInfo.PREFIX + "textures/gui/vanity.png");

	private final InventoryVanity inventory;

	public GuiVanity(final EntityPlayer player, final InventoryPlayer inventoryPlayer, final InventoryVanity inventoryCustom)
	{
		super(new ContainerVanity(player, inventoryPlayer, inventoryCustom));
		this.inventory = inventoryCustom;
	}

	@Override
	public void drawScreen(final int x, final int y, final float scale)
	{
		super.drawScreen(x, y, scale);
		this.xSize_lo = x;
		this.ySize_lo = y;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int x, final int y)
	{
		final String s = this.inventory.getInventoryName();
		this.fontRendererObj.drawString(s, this.xSize - this.fontRendererObj.getStringWidth(s) - 8, 5, 4210752);
		this.fontRendererObj.drawString("container.inventory", 120, this.ySize - 92, 4210752); // TODO

		this.fontRendererObj.drawString("Thanks, coolAlias!", this.xSize + 35, this.ySize + 34, 2000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float scale, final int par2, final int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		final int x = (this.width - this.xSize) / 2;
		final int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}
