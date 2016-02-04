
package steamcraft.common.items.armor;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.items.BaseArmor;
import org.lwjgl.opengl.GL11;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemDivingHelmet extends BaseArmor
{
	private static ResourceLocation overlay = new ResourceLocation(ModInfo.PREFIX + "textures/misc/divinghelmet.png");

	public ItemDivingHelmet(ArmorMaterial mat, int renderIndex, int type)
	{
		super(mat, type, "", ModInfo.PREFIX);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return ModInfo.PREFIX + "textures/models/armor/divinghelmet.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY)
	{
		if ((ClientHelper.player() == null) || (ClientHelper.screen() != null))
			return;

		ItemStack helmet = ClientHelper.player().inventory.armorItemInSlot(3);

		if ((ClientHelper.settings().thirdPersonView == 0) && (helmet != null) && (helmet.getItem() == InitItems.itemDivingHelmet)) // &&
		// KeyHandler.keyPressed)
		{
			ClientHelper.textureManager().bindTexture(overlay);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = ClientHelper.resolution();
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
			 * if (!mc.gameSettings.hideGUI || mc.currentScreen != null) { int x
			 * = (Mouse.getX() * width) / mc.displayWidth; int y = height -
			 * (Mouse.getY() * height) / mc.displayHeight - 1;
			 * mc.ingameGUI.renderGameOverlay(0.0F, mc.currentScreen != null, x,
			 * y); }
			 */
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		if (player.getAir() <= 0)
		{
			player.setAir(300);
			is.damageItem(4, player); // tweak the damage taken a bit
		}
	}

}
