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
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelArmorEditor;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileArmorEditor;
import boilerplate.client.renderers.RenderFloatingItem;

/**
 * @author Surseance
 * 
 */
public class TileArmorEditorRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation crystal = new ResourceLocation(LibInfo.PREFIX.replace(":", ""), "textures/models/armoreditor.png");
	private final ModelArmorEditor model;

	public TileArmorEditorRenderer()
	{
		this.model = new ModelArmorEditor();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{
		TileArmorEditor tile = (TileArmorEditor) te;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(crystal);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(1, 0.9F, 1);
		GL11.glTranslatef(0, 0.2F, 0);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();

		if(tile.inventory[0] != null)
		{
			GL11.glPushMatrix();
			RenderFloatingItem.render(dx + 0.5F, dy + 0.6F, dz + 0.5F, 60, 0, 0, tile.inventory[0]);
			GL11.glPopMatrix();
		}
	}
}
