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
package steamcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelCharger;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCharger;
import boilerplate.client.renderers.RenderFloatingItem;

/**
 * @author Surseance
 * 
 */
public class TileChargerRenderer extends TileEntitySpecialRenderer
{
	@SuppressWarnings("unused")
	private final ModelCharger model;
	@SuppressWarnings("unused")
	private RenderFloatingItem itemRenderer;

	public TileChargerRenderer()
	{
		this.model = new ModelCharger();
		this.itemRenderer = new RenderFloatingItem(false, false);
	}

	@Override
	public void renderTileEntityAt(final TileEntity te, final double dx, final double dy, final double dz, final float scale)
	{
		TileCharger tile = (TileCharger)te;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		final ResourceLocation crystal = (new ResourceLocation(LibInfo.PREFIX.replace(":", ""), "textures/models/charger.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(crystal);
		GL11.glPushMatrix();
		if(tile.inventory[0] != null)
		{
			EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, tile.inventory[0]);
			//Without the below line, the item will spazz out
			entItem.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glTranslatef((float)dx + 0.5F, (float)dy - 0.5F, (float)dz + 0.3F);
			GL11.glRotatef(180, 0, 2.5F, 1);
			GL11.glScalef(1.7F, 1.7F, 1.7F);
			RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
