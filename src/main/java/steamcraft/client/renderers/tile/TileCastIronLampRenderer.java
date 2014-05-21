/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 5, 2014, 8:40:41 PM]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class TileCastIronLampRenderer extends TileEntitySpecialRenderer
{
	private ModelCastIronLampTop lampModelTop;
	private ModelCastIronLampSide lampModelSide;

	public TileCastIronLampRenderer()
	{
		this.lampModelTop = new ModelCastIronLampTop();
		this.lampModelSide = new ModelCastIronLampSide();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale) 
	{
		Block block = te.getBlockType();
		GL11.glPushMatrix();
		float f1 = 0.6666667F;
		int metadata = te.getBlockMetadata();
		float f3 = 0.0F;
		float f2 = 1.0F;

		if (metadata == 2)
		{
			f3 = 180F;
		}
		
		if (metadata == 4)
		{
			f3 = 90F;
		}
		
		if (metadata == 3)
		{
			f3 = -90F;
		}
		
		if (metadata == 6)
		{
			f2 = 180F;
		}

		GL11.glTranslatef((float)dx + 0.5F, (float)dy + 0.75F * f1, (float)dz + 0.5F);
		GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);

		if (metadata == 6)
		{
			GL11.glRotatef(f2, 0.0F, 0.0F, 1.0F);
		}

		GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);

		if ((block == Block.torchRedstoneActive) || (block == Block.torchRedstoneIdle))
		{
			lampModelTop.bracketWide.showModel = false;
			lampModelTop.crossbarLeft.showModel = false;
			lampModelTop.crossbarRight.showModel = false;
			lampModelSide.crossbarLeft.showModel = false;
			lampModelSide.crossbarRight.showModel = false;
		} 
		else if (block == Block.torchWood)
		{
			lampModelTop.bracketWide.showModel = true;
			lampModelTop.crossbarLeft.showModel = true;
			lampModelTop.crossbarRight.showModel = true;
			lampModelSide.crossbarLeft.showModel = true;
			lampModelSide.crossbarRight.showModel = true;
		}

		// Renders the textures based on torch state
		ResourceLocation lampOn = (new ResourceLocation(LibInfo.PREFIX + "textures/models/lampon.png"));
		ResourceLocation lampOff = (new ResourceLocation(LibInfo.PREFIX + "textures/models/lampoff.png"));

		if (block == ConfigBlocks.blockCastIronLampA)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(lampOn);		
		} 
		else if (block == ConfigBlocks.blockCastIronLampI)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(lampOff);		
		}

		GL11.glPushMatrix();
		GL11.glScalef(f1, -f1, -f1);

		if (metadata == 5 || metadata == 6)
		{
			lampModelTop.renderSign();
		} 
		else
		{
			lampModelSide.renderSign();
		}

		GL11.glPopMatrix();
		float f4 = 0.01666667F * f1;
		GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
		GL11.glScalef(f4, -f4, f4);
		GL11.glNormal3f(0.0F, 0.0F, -1F * f4);
		GL11.glDepthMask(false);
		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	/*
	@Override
	protected void bindTexture(ResourceLocation resource)
    {
        TextureManager texturemanager = this.tileEntityRenderer.renderEngine;

        if(texturemanager != null)
        {
            texturemanager.bindTexture(TextureMap.locationBlocksTexture);
        }
    }*/
}
