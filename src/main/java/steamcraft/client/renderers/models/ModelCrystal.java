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
public class ModelCrystal extends ModelBase
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

	public ModelCrystal()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0F, 0F, 0F, 6, 7, 6);
		this.Shape1.setRotationPoint(-3F, 17F, -3F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 26, 0);
		this.Shape2.addBox(0F, 0F, 0F, 5, 5, 5);
		this.Shape2.setRotationPoint(-2.5F, 12F, -2.5F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
		this.Shape3 = new ModelRenderer(this, 0, 14);
		this.Shape3.addBox(0F, 0F, 0F, 2, 4, 2);
		this.Shape3.setRotationPoint(-1F, 8F, -1F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);
		this.Shape4 = new ModelRenderer(this, 50, 0);
		this.Shape4.addBox(0F, 0F, 0F, 1, 4, 3);
		this.Shape4.setRotationPoint(0F, 9F, -1.5F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, -0.2617994F);
		this.Shape5 = new ModelRenderer(this, 50, 0);
		this.Shape5.addBox(-1F, 0F, -3F, 1, 4, 3);
		this.Shape5.setRotationPoint(0F, 9F, 1.5F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0.2617994F);
		this.Shape6 = new ModelRenderer(this, 10, 14);
		this.Shape6.addBox(0F, 0F, 0F, 3, 4, 1);
		this.Shape6.setRotationPoint(-1.5F, 9F, 0.5F);
		this.Shape6.setTextureSize(64, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0.2617994F, 0F, 0F);
		this.Shape7 = new ModelRenderer(this, 10, 14);
		this.Shape7.addBox(-3F, 0F, -1F, 3, 4, 1);
		this.Shape7.setRotationPoint(1.5F, 9F, -0.5F);
		this.Shape7.setTextureSize(64, 32);
		this.Shape7.mirror = true;
		this.setRotation(this.Shape7, -0.1745329F, 0F, 0F);
		this.Shape8 = new ModelRenderer(this, 20, 14);
		this.Shape8.addBox(0F, 0F, 0F, 2, 4, 2);
		this.Shape8.setRotationPoint(-4.5F, 16F, 3F);
		this.Shape8.setTextureSize(64, 32);
		this.Shape8.mirror = true;
		this.setRotation(this.Shape8, 0F, 0.7853982F, -0.6981317F);
		this.Shape9 = new ModelRenderer(this, 0, 22);
		this.Shape9.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Shape9.setRotationPoint(-4.5F, 15F, 4F);
		this.Shape9.setTextureSize(64, 32);
		this.Shape9.mirror = true;
		this.setRotation(this.Shape9, 0F, 0.7853982F, -0.6981317F);
		this.Shape10 = new ModelRenderer(this, 0, 0);
		this.Shape10.addBox(0F, 0F, 0F, 1, 3, 1);
		this.Shape10.setRotationPoint(3F, 14F, -2F);
		this.Shape10.setTextureSize(64, 32);
		this.Shape10.mirror = true;
		this.setRotation(this.Shape10, 0F, 0.5235988F, 0.5235988F);
		this.Shape11 = new ModelRenderer(this, 30, 14);
		this.Shape11.addBox(0F, 0F, 0F, 2, 2, 2);
		this.Shape11.setRotationPoint(-4.5F, 22F, -2.5F);
		this.Shape11.setTextureSize(64, 32);
		this.Shape11.mirror = true;
		this.setRotation(this.Shape11, 0F, 0F, -0.7853982F);
		this.Shape12 = new ModelRenderer(this, 20, 0);
		this.Shape12.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Shape12.setRotationPoint(-4.5F, 21F, -2F);
		this.Shape12.setTextureSize(64, 32);
		this.Shape12.mirror = true;
		this.setRotation(this.Shape12, 0F, 0F, -0.7853982F);
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