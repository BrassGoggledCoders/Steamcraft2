package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelGrub extends ModelBase
{
	// fields
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer body1;
	ModelRenderer body4;
	ModelRenderer body3;

	public ModelGrub()
	{
		textureWidth = 64;
		textureHeight = 32;
		GL11.glTranslatef(0, -0.5F, 0);

		body = new ModelRenderer(this, 0, 0);
		body.addBox(0F, 0F, 0F, 5, 4, 6);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 22, 0);
		body2.addBox(0F, 0F, 0F, 4, 3, 4);
		body2.setRotationPoint(5F, 1F, 1F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body1 = new ModelRenderer(this, 22, 0);
		body1.addBox(0F, 0F, 0F, 4, 3, 4);
		body1.setRotationPoint(-4F, 1F, 1F);
		body1.setTextureSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body4 = new ModelRenderer(this, 38, 0);
		body4.addBox(0F, 0F, 0F, 2, 2, 3);
		body4.setRotationPoint(-6F, 2F, 1.5F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 38, 0);
		body3.addBox(0F, 0F, 0F, 2, 2, 3);
		body3.setRotationPoint(9F, 2F, 1.5F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		body2.render(f5);
		body1.render(f5);
		body4.render(f5);
		body3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
