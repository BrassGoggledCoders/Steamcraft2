/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 17, 2014, 10:00:16 AM]
 */
package common.steamcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Etherium Crystal Model
 * 
 * @author MrArcane111 & Falkok15
 *
 */
public class ModelEtheriumCrystal extends ModelBase {
	/** Renderer Fields */
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

	public ModelEtheriumCrystal() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 7, 6);
		Shape1.setRotationPoint(-3F, 17F, -3F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 26, 0);
		Shape2.addBox(0F, 0F, 0F, 5, 5, 5);
		Shape2.setRotationPoint(-2.5F, 12F, -2.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 14);
		Shape3.addBox(0F, 0F, 0F, 2, 4, 2);
		Shape3.setRotationPoint(-1F, 8F, -1F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 50, 0);
		Shape4.addBox(0F, 0F, 0F, 1, 4, 3);
		Shape4.setRotationPoint(0F, 9F, -1.5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, 0F, -0.2617994F);
		Shape5 = new ModelRenderer(this, 50, 0);
		Shape5.addBox(-1F, 0F, -3F, 1, 4, 3);
		Shape5.setRotationPoint(0F, 9F, 1.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0.2617994F);
		Shape6 = new ModelRenderer(this, 10, 14);
		Shape6.addBox(0F, 0F, 0F, 3, 4, 1);
		Shape6.setRotationPoint(-1.5F, 9F, 0.5F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0.2617994F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 10, 14);
		Shape7.addBox(-3F, 0F, -1F, 3, 4, 1);
		Shape7.setRotationPoint(1.5F, 9F, -0.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		this.setRotation(Shape7, -0.1745329F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 20, 14);
		Shape8.addBox(0F, 0F, 0F, 2, 4, 2);
		Shape8.setRotationPoint(-4.5F, 16F, 3F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		this.setRotation(Shape8, 0F, 0.7853982F, -0.6981317F);
		Shape9 = new ModelRenderer(this, 0, 22);
		Shape9.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape9.setRotationPoint(-4.5F, 15F, 4F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		this.setRotation(Shape9, 0F, 0.7853982F, -0.6981317F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape10.setRotationPoint(3F, 14F, -2F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		this.setRotation(Shape10, 0F, 0.5235988F, 0.5235988F);
		Shape11 = new ModelRenderer(this, 30, 14);
		Shape11.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape11.setRotationPoint(-4.5F, 22F, -2.5F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		this.setRotation(Shape11, 0F, 0F, -0.7853982F);
		Shape12 = new ModelRenderer(this, 20, 0);
		Shape12.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape12.setRotationPoint(-4.5F, 21F, -2F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		this.setRotation(Shape12, 0F, 0F, -0.7853982F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
	}

	// Techne method
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
