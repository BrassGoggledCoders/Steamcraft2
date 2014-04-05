/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [3 Apr 2014, 10:25:36]
 */
package common.steamcraft.common.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import common.steamcraft.common.lib2.LibInfo;

/**
 * @author warlordjones
 *
 * 3 Apr 201410:25:36
 */
public class GuiVanity extends GuiContainer{
	/** x size of the inventory window in pixels. Defined as float, passed as int */
	private float xSize_lo;

	/** y size of the inventory window in pixels. Defined as float, passed as int. */
	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation(LibInfo.SC2_PREFIX + "textures/gui/vanity.png");

	private final PlayerInventoryVanity inventory;

	public GuiVanity(EntityPlayer player, InventoryPlayer inventoryPlayer, PlayerInventoryVanity inventoryCustom)
	{
	super(new ContainerVanity(player, inventoryPlayer, inventoryCustom));
	this.inventory = inventoryCustom;
	}

	/**
	* Draws the screen and all the components in it.
	*/
	public void drawScreen(int par1, int par2, float par3)
	{
	super.drawScreen(par1, par2, par3);
	this.xSize_lo = (float)par1;
	this.ySize_lo = (float)par2;
	}

	/**
	* Draw the foreground layer for the GuiContainer (everything in front of the items)
	*/
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{

	//String s = this.inventory.isInvNameLocalized() ? this.inventory.getInvName() : I18n.getString(this.inventory.getInvName());
	// with the name "Custom Inventory", the 'Cu' will be drawn in the first slot
	//this.fontRenderer.drawString(s, this.xSize - this.fontRenderer.getStringWidth(s), 12, 4210752);
	// this just adds "Inventory" above the player's inventory below
	//this.fontRenderer.drawString(I18n.getString("container.inventory"), 80, this.ySize - 96, 4210752);
	}

	/**
	* Draw the background layer for the GuiContainer (everything behind the items)
	*/
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.getTextureManager().bindTexture(iconLocation);
	int k = (this.width - this.xSize) / 2;
	int l = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	int i1;
	}
	}
