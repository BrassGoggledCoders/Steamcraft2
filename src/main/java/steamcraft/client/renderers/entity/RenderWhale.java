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
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.client.renderers.models.ModelWhale;
import steamcraft.common.lib.ModInfo;

public class RenderWhale extends RenderLiving
{
	private static final ResourceLocation ENT_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/whale.png");

	public RenderWhale()
	{
		super(new ModelWhale(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderWhale.ENT_TEXTURE;
	}
}
