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
 * File created @ [12 Apr 2014, 22:42:24]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author warlordjones
 *
 */
public class ModelHatch extends ModelBase{
	public ModelRenderer base;
    public ModelRenderer center;
    public ModelRenderer hatch;
    public ModelRenderer spoke;
    public ModelRenderer spoke1;
    public ModelRenderer spoke2;
    public ModelRenderer spoke3;

    public ModelHatch() {
	base = new ModelRenderer(this, 0, 0);
	base.addBox(-8.5F, -2.0F, -8.5F, 17, 4, 17, 0);

	hatch = new ModelRenderer(this, 0, 0);
	hatch.addBox(-7.0F, -5.5F, -7.0F, 14, 5, 14, 0);
	center = new ModelRenderer(this, 0, 0);
	center.addBox(-1.5F, -9.0F, -1.5F, 3, 8, 3, 0);

	spoke = new ModelRenderer(this, 0, 0);
	spoke.addBox(1.0F, -9.0F, -1.0F, 6, 2, 2, 0);

	spoke1 = new ModelRenderer(this, 0, 0);
	spoke1.addBox(-7.0F, -9.0F, -1.0F, 6, 2, 2, 0);

	spoke2 = new ModelRenderer(this, 0, 0);
	spoke2.addBox(-1.0F, -9.0F, 1.0F, 2, 2, 6, 0);

	spoke3 = new ModelRenderer(this, 0, 0);
	spoke3.addBox(-1.0F, -9.0F, -7.0F, 2, 2, 6, 0);

    }

    public void render(final float f, final float f1, final float f2,
	    final float f3, final float f4, final float f5) {
	this.setRotationAngles(f, f1, f2, f3, f4, f5);
	base.render(f5);
	hatch.render(f5);
	center.render(f5);
	spoke.render(f5);
	spoke1.render(f5);
	spoke2.render(f5);
	spoke3.render(f5);
    }

    // Method you're going to want to override:
    public void setRotationAngles(final float f, final float f1,
	    final float f2, final float f3, final float f4, final float f5) {
    }


}
