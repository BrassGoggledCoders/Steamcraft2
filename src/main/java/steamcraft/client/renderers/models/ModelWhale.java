
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * EntityWhale - warlordjones Created using Tabula 4.0.0
 */
public class ModelWhale extends ModelBase
{
	public ModelRenderer head;
	public ModelRenderer tail2;
	public ModelRenderer mouth;
	public ModelRenderer tail3;
	public ModelRenderer flipper;
	public ModelRenderer flipper1;
	public ModelRenderer tail;
	public ModelRenderer body;

	public ModelWhale()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.flipper1 = new ModelRenderer(this, 0, 64);
		this.flipper1.setRotationPoint(13.0F, 11.0F, -11.0F);
		this.flipper1.addBox(0.0F, 0.0F, 0.0F, 12, 2, 8);
		this.setRotateAngle(this.flipper1, 0.5462880730628967F, 0.31869712471961975F, 0.5009095072746276F);
		this.head = new ModelRenderer(this, 52, 50);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(0.0F, 0.0F, 0.0F, 15, 10, 15);
		this.tail3 = new ModelRenderer(this, 0, 114);
		this.tail3.setRotationPoint(-4.0F, -2.0F, -67.0F);
		this.tail3.addBox(0.0F, 0.0F, 0.0F, 22, 5, 9);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(-3.0F, -2.0F, -33.0F);
		this.body.addBox(0.0F, 0.0F, 0.0F, 20, 16, 33);
		this.tail2 = new ModelRenderer(this, 0, 98);
		this.tail2.setRotationPoint(2.0F, -2.0F, -58.0F);
		this.tail2.addBox(0.0F, 0.0F, 0.0F, 10, 5, 10);
		this.mouth = new ModelRenderer(this, 0, 48);
		this.mouth.setRotationPoint(1.0F, 8.0F, 0.0F);
		this.mouth.addBox(0.0F, 0.0F, 0.0F, 13, 3, 13);
		this.setRotateAngle(this.mouth, -0.22759093344211578F, 0.0F, 0.0F);
		this.flipper = new ModelRenderer(this, 0, 65);
		this.flipper.setRotationPoint(-12.0F, 11.0F, -11.0F);
		this.flipper.addBox(0.0F, 0.0F, 0.0F, 12, 2, 8);
		this.setRotateAngle(this.flipper, -0.5462880730628967F, -0.31869712471961975F, -0.5009095072746276F);
		this.tail = new ModelRenderer(this, 0, 74);
		this.tail.setRotationPoint(0.0F, -2.0F, -48.0F);
		this.tail.addBox(0.0F, 0.0F, 0.0F, 15, 10, 15);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glScalef(2F, 2F, 2F);
		GL11.glTranslatef(-0.5F, 0F, 1F);
		this.flipper1.render(f5);
		this.head.render(f5);
		this.tail3.render(f5);
		this.body.render(f5);
		this.tail2.render(f5);
		this.mouth.render(f5);
		this.flipper.render(f5);
		this.tail.render(f5);
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