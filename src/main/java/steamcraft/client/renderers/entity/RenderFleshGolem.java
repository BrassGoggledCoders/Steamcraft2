package steamcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.LibInfo;

public class RenderFleshGolem extends RenderLiving {
    private static final ResourceLocation FLESHGOLEM_TEXTURE = new ResourceLocation(LibInfo.PREFIX + "textures/models/mobs/flesh_golem.png");

    public RenderFleshGolem(final ModelBase par1ModelBase, final float par2) {
	super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
	return RenderFleshGolem.FLESHGOLEM_TEXTURE;
    }
}
