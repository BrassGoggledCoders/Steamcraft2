
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelGrub extends ModelBase
{
	public final ModelRenderer body;
	public final ModelRenderer body2;
	public final ModelRenderer body1;
	public final ModelRenderer body4;
	public final ModelRenderer body3;

	public ModelGrub()
	{
		GL11.glTranslatef(0, -0.65F, 0);
		GL11.glRotatef(90, 0, 1, 0);

		this.body = new ModelRenderer(this);
		this.body.addBox(0F, 0F, 0F, 5, 4, 6);
		this.body.mirror = true;
		this.body2 = new ModelRenderer(this, 22, 0);
		this.body2.addBox(0F, 0F, 0F, 4, 3, 4);
		this.body2.setRotationPoint(5F, 1F, 1F);
		this.body2.mirror = true;
		this.body1 = new ModelRenderer(this, 22, 0);
		this.body1.addBox(0F, 0F, 0F, 4, 3, 4);
		this.body1.setRotationPoint(-4F, 1F, 1F);
		this.body1.mirror = true;
		this.body4 = new ModelRenderer(this, 38, 0);
		this.body4.addBox(0F, 0F, 0F, 2, 2, 3);
		this.body4.setRotationPoint(-6F, 2F, 1.5F);
		this.body4.mirror = true;
		this.body3 = new ModelRenderer(this, 38, 0);
		this.body3.addBox(0F, 0F, 0F, 2, 2, 3);
		this.body3.setRotationPoint(9F, 2F, 1.5F);
		this.body3.mirror = true;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.body.render(f5);
		this.body2.render(f5);
		this.body1.render(f5);
		this.body4.render(f5);
		this.body3.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
