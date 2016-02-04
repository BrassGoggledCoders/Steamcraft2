
package steamcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

public class RenderFleshGolem extends RenderLiving
{
	private static final ResourceLocation FLESHGOLEM_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/flesh_golem.png");

	public RenderFleshGolem(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderFleshGolem.FLESHGOLEM_TEXTURE;
	}
}
