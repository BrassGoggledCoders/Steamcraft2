/*
 *
 */
package steamcraft.common.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemBrassGoggles.
 */
public class ItemBrassGoggles extends BaseArmor
{

	/**
	 * Instantiates a new item brass goggles.
	 *
	 * @param mat
	 *            the mat
	 * @param p_i45325_2_
	 *            the p_i45325_2_
	 * @param p_i45325_3_
	 *            the p_i45325_3_
	 */
	public ItemBrassGoggles(ArmorMaterial mat, int p_i45325_2_, int p_i45325_3_)
	{
		super(mat, p_i45325_2_, p_i45325_3_);
		setUnlocalizedName("itemBrassGoggles");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return LibInfo.PREFIX + "textures/armor/divinghelmet.png";
	}
	/** The overlay. */
	private static ResourceLocation overlay = new ResourceLocation(LibInfo.PREFIX + "textures/misc/goggles.png");

	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY)
	{
		if ((Minecraft.getMinecraft().thePlayer == null) || (Minecraft.getMinecraft().currentScreen != null))
			return;

		ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);

		if ((Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) && (helmet != null) && (helmet.getItem() == ConfigItems.brassGoggles))// &&
		// KeyHandler.keyPressed)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(overlay);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft(),
					Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			// GL11.glEnable(GL11.GL_BLEND);
			// GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_COLOR);
			// GL11.glColor3f(1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glClearDepth(1.0D);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0.0D, height, 90.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(width, height, 90.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(width, 0.0D, 90.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
			tessellator.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glDisable(GL11.GL_BLEND);

			/*
			 * if (!mc.gameSettings.hideGUI || mc.currentScreen != null) {
			 * int x = (Mouse.getX() * width) / mc.displayWidth; int y =
			 * height - (Mouse.getY() * height) / mc.displayHeight - 1;
			 * mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen !=
			 * null, x, y); }
			 */
		}
	}
}
