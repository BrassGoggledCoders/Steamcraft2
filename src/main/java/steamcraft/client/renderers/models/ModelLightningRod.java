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
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author Surseance
 * 
 */
public class ModelLightningRod extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;

	public ModelLightningRod()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0F, 0F, 0F, 6, 6, 6);
		this.Shape1.setRotationPoint(-3F, 18F, -3F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 0, 12);
		this.Shape2.addBox(0F, 0F, 0F, 2, 10, 2);
		this.Shape2.setRotationPoint(-1F, 8F, -1F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
		this.Shape3 = new ModelRenderer(this, 18, 0);
		this.Shape3.addBox(0F, 0F, 0F, 6, 1, 2);
		this.Shape3.setRotationPoint(-3F, 7F, -1F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);
		this.Shape4 = new ModelRenderer(this, 34, 0);
		this.Shape4.addBox(0F, 0F, 0F, 2, 1, 2);
		this.Shape4.setRotationPoint(-1F, 7F, 1F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, 0F);
		this.Shape5 = new ModelRenderer(this, 34, 0);
		this.Shape5.addBox(0F, 0F, 0F, 2, 1, 2);
		this.Shape5.setRotationPoint(-1F, 7F, -3F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0F);
		this.Shape6 = new ModelRenderer(this, 0, 24);
		this.Shape6.addBox(0F, 0F, 0F, 4, 2, 4);
		this.Shape6.setRotationPoint(-2F, 16F, -2F);
		this.Shape6.setTextureSize(64, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0F, 0F, 0F);
		this.Shape7 = new ModelRenderer(this, 42, 0);
		this.Shape7.addBox(0F, 0F, 0F, 2, 5, 1);
		this.Shape7.setRotationPoint(-1F, 2F, -4F);
		this.Shape7.setTextureSize(64, 32);
		this.Shape7.mirror = true;
		this.setRotation(this.Shape7, 0F, 0F, 0F);
		this.Shape8 = new ModelRenderer(this, 42, 0);
		this.Shape8.addBox(0F, 0F, 0F, 2, 5, 1);
		this.Shape8.setRotationPoint(-1F, 2F, 3F);
		this.Shape8.setTextureSize(64, 32);
		this.Shape8.mirror = true;
		this.setRotation(this.Shape8, 0F, 0F, 0F);
		this.Shape9 = new ModelRenderer(this, 8, 12);
		this.Shape9.addBox(0F, 0F, 0F, 2, 1, 6);
		this.Shape9.setRotationPoint(-1F, 1F, -3F);
		this.Shape9.setTextureSize(64, 32);
		this.Shape9.mirror = true;
		this.setRotation(this.Shape9, 0F, 0F, 0F);
		this.Shape10 = new ModelRenderer(this, 30, 3);
		this.Shape10.addBox(0F, 0F, 0F, 3, 3, 3);
		this.Shape10.setRotationPoint(-1.5F, 3F, -1.5F);
		this.Shape10.setTextureSize(64, 32);
		this.Shape10.mirror = true;
		this.setRotation(this.Shape10, 0F, 0F, 0F);
		this.Shape11 = new ModelRenderer(this, 24, 4);
		this.Shape11.addBox(0F, 0F, 0F, 1, 5, 2);
		this.Shape11.setRotationPoint(3F, 2F, -1F);
		this.Shape11.setTextureSize(64, 32);
		this.Shape11.mirror = true;
		this.setRotation(this.Shape11, 0F, 0F, 0F);
		this.Shape12 = new ModelRenderer(this, 24, 4);
		this.Shape12.addBox(0F, 0F, 0F, 1, 5, 2);
		this.Shape12.setRotationPoint(-4F, 2F, -1F);
		this.Shape12.setTextureSize(64, 32);
		this.Shape12.mirror = true;
		this.setRotation(this.Shape12, 0F, 0F, 0F);
		this.Shape13 = new ModelRenderer(this, 8, 20);
		this.Shape13.addBox(0F, 0F, 0F, 2, 1, 2);
		this.Shape13.setRotationPoint(1F, 1F, -1F);
		this.Shape13.setTextureSize(64, 32);
		this.Shape13.mirror = true;
		this.setRotation(this.Shape13, 0F, 0F, 0F);
		this.Shape14 = new ModelRenderer(this, 8, 20);
		this.Shape14.addBox(0F, 0F, 0F, 2, 1, 2);
		this.Shape14.setRotationPoint(-3F, 1F, -1F);
		this.Shape14.setTextureSize(64, 32);
		this.Shape14.mirror = true;
		this.setRotation(this.Shape14, 0F, 0F, 0F);
		this.Shape15 = new ModelRenderer(this, 48, 0);
		this.Shape15.addBox(0F, 0F, 0F, 1, 5, 1);
		this.Shape15.setRotationPoint(-0.5F, 2F, -0.5F);
		this.Shape15.setTextureSize(64, 32);
		this.Shape15.mirror = true;
		this.setRotation(this.Shape15, 0F, 0F, 0F);
		this.Shape16 = new ModelRenderer(this, 19, 13);
		this.Shape16.addBox(0F, 0F, 0F, 2, 2, 2);
		this.Shape16.setRotationPoint(-1F, -1F, -1F);
		this.Shape16.setTextureSize(64, 32);
		this.Shape16.mirror = true;
		this.setRotation(this.Shape16, 0F, 0F, 0F);
		this.Shape17 = new ModelRenderer(this, 28, 12);
		this.Shape17.addBox(0F, 0F, 0F, 1, 7, 1);
		this.Shape17.setRotationPoint(-0.5F, -8F, -0.5F);
		this.Shape17.setTextureSize(64, 32);
		this.Shape17.mirror = true;
		this.setRotation(this.Shape17, 0F, 0F, 0F);
		this.Shape18 = new ModelRenderer(this, 54, 0);
		this.Shape18.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Shape18.setRotationPoint(-0.5F, 0F, 0F);
		this.Shape18.setTextureSize(64, 32);
		this.Shape18.mirror = true;
		this.setRotation(this.Shape18, 0.5235988F, 0F, 0F);
		this.Shape19 = new ModelRenderer(this, 54, 0);
		this.Shape19.addBox(-1F, 0F, -1F, 1, 2, 1);
		this.Shape19.setRotationPoint(0.5F, 0F, 0F);
		this.Shape19.setTextureSize(64, 32);
		this.Shape19.mirror = true;
		this.setRotation(this.Shape19, -0.5235988F, 0F, 0F);
		this.Shape20 = new ModelRenderer(this, 42, 9);
		this.Shape20.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Shape20.setRotationPoint(0F, 0F, -0.5F);
		this.Shape20.setTextureSize(64, 32);
		this.Shape20.mirror = true;
		this.setRotation(this.Shape20, 0F, 0F, -0.5235988F);
		this.Shape20 = new ModelRenderer(this, 42, 9);
		this.Shape20.addBox(-1F, 0F, -1F, 1, 2, 1);
		this.Shape20.setRotationPoint(0F, 0F, 0.5F);
		this.Shape20.setTextureSize(64, 32);
		this.Shape20.mirror = true;
		this.setRotation(this.Shape20, 0F, 0F, 0.5235988F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape5.render(f5);
		this.Shape6.render(f5);
		this.Shape7.render(f5);
		this.Shape8.render(f5);
		this.Shape9.render(f5);
		this.Shape10.render(f5);
		this.Shape11.render(f5);
		this.Shape12.render(f5);
		this.Shape13.render(f5);
		this.Shape14.render(f5);
		this.Shape15.render(f5);
		this.Shape16.render(f5);
		this.Shape17.render(f5);
		this.Shape18.render(f5);
		this.Shape19.render(f5);
		this.Shape20.render(f5);
		this.Shape20.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
