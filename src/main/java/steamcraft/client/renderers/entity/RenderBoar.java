
package steamcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

public class RenderBoar extends RenderPig
{
	private static final ResourceLocation ENT_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/boar.png");

	public RenderBoar(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderBoar.ENT_TEXTURE;
	}
}
