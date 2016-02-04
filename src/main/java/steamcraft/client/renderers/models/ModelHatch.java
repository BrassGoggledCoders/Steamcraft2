
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author warlordjones
 *
 */
public class ModelHatch extends ModelBase
{
	public ModelRenderer base;
	public ModelRenderer center;
	public ModelRenderer hatch;

	public ModelRenderer spoke;
	public ModelRenderer spoke1;
	public ModelRenderer spoke2;
	public ModelRenderer spoke3;

	public ModelHatch()
	{
		this.base = new ModelRenderer(this);
		this.base.addBox(-8.5F, -2.0F, -8.5F, 17, 4, 17, 0);

		this.hatch = new ModelRenderer(this);
		this.hatch.addBox(-7.0F, -5.5F, -7.0F, 14, 5, 14, 0);
		this.center = new ModelRenderer(this);
		this.center.addBox(-1.5F, -9.0F, -1.5F, 3, 8, 3, 0);

		this.spoke = new ModelRenderer(this);
		this.spoke.addBox(1.0F, -9.0F, -1.0F, 6, 2, 2, 0);

		this.spoke1 = new ModelRenderer(this);
		this.spoke1.addBox(-7.0F, -9.0F, -1.0F, 6, 2, 2, 0);

		this.spoke2 = new ModelRenderer(this);
		this.spoke2.addBox(-1.0F, -9.0F, 1.0F, 2, 2, 6, 0);

		this.spoke3 = new ModelRenderer(this);
		this.spoke3.addBox(-1.0F, -9.0F, -7.0F, 2, 2, 6, 0);

	}

	public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.base.render(f5);
		this.hatch.render(f5);
		this.center.render(f5);
		this.spoke.render(f5);
		this.spoke1.render(f5);
		this.spoke2.render(f5);
		this.spoke3.render(f5);
	}

	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{

	}
}
