
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCharger extends ModelBase
{
	public ModelRenderer base2;
	public ModelRenderer top;
	public ModelRenderer post;
	public ModelRenderer post2;
	public ModelRenderer post3;
	public ModelRenderer post1;
	public ModelRenderer base;
	public ModelRenderer top2;

	public ModelCharger()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.base2 = new ModelRenderer(this, 0, 18);
		this.base2.setRotationPoint(-6.0F, 20.0F, -6.0F);
		this.base2.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12);
		this.top2 = new ModelRenderer(this, 0, 18);
		this.top2.setRotationPoint(-6.0F, 8.0F, -6.0F);
		this.top2.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12);
		this.top = new ModelRenderer(this, 0, 0);
		this.top.setRotationPoint(-8.0F, 6.0F, -8.0F);
		this.top.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
		this.post = new ModelRenderer(this, 0, 0);
		this.post.setRotationPoint(5.0F, 8.0F, -7.0F);
		this.post.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2);
		this.post2 = new ModelRenderer(this, 0, 0);
		this.post2.setRotationPoint(-7.0F, 7.0F, 5.0F);
		this.post2.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2);
		this.post3 = new ModelRenderer(this, 0, 0);
		this.post3.setRotationPoint(5.0F, 8.0F, 5.0F);
		this.post3.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(-8.0F, 22.0F, -8.0F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
		this.post1 = new ModelRenderer(this, 0, 0);
		this.post1.setRotationPoint(-7.0F, 7.0F, -7.0F);
		this.post1.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.base2.render(f5);
		this.top2.render(f5);
		this.top.render(f5);
		this.post.render(f5);
		this.post2.render(f5);
		this.post3.render(f5);
		this.base.render(f5);
		this.post1.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
