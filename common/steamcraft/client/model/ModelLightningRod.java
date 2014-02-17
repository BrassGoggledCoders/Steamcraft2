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
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
/**
 * 
 * @author MrArcane111 & Falkok15
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
	//ModelRenderer Shape20;

	public ModelLightningRod()
	{
		textureWidth = 64;
		textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 6, 6);
		Shape1.setRotationPoint(-3F, 18F, -3F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 12);
		Shape2.addBox(0F, 0F, 0F, 2, 10, 2);
		Shape2.setRotationPoint(-1F, 8F, -1F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 18, 0);
		Shape3.addBox(0F, 0F, 0F, 6, 1, 2);
		Shape3.setRotationPoint(-3F, 7F, -1F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 34, 0);
		Shape4.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape4.setRotationPoint(-1F, 7F, 1F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 34, 0);
		Shape5.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape5.setRotationPoint(-1F, 7F, -3F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 24);
		Shape6.addBox(0F, 0F, 0F, 4, 2, 4);
		Shape6.setRotationPoint(-2F, 16F, -2F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 42, 0);
		Shape7.addBox(0F, 0F, 0F, 2, 5, 1);
		Shape7.setRotationPoint(-1F, 2F, -4F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		this.setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 42, 0);
		Shape8.addBox(0F, 0F, 0F, 2, 5, 1);
		Shape8.setRotationPoint(-1F, 2F, 3F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		this.setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 8, 12);
		Shape9.addBox(0F, 0F, 0F, 2, 1, 6);
		Shape9.setRotationPoint(-1F, 1F, -3F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		this.setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 30, 3);
		Shape10.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape10.setRotationPoint(-1.5F, 3F, -1.5F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		this.setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 24, 4);
		Shape11.addBox(0F, 0F, 0F, 1, 5, 2);
		Shape11.setRotationPoint(3F, 2F, -1F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		this.setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 24, 4);
		Shape12.addBox(0F, 0F, 0F, 1, 5, 2);
		Shape12.setRotationPoint(-4F, 2F, -1F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		this.setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 8, 20);
		Shape13.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape13.setRotationPoint(1F, 1F, -1F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		this.setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 8, 20);
		Shape14.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape14.setRotationPoint(-3F, 1F, -1F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		this.setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 48, 0);
		Shape15.addBox(0F, 0F, 0F, 1, 5, 1);
		Shape15.setRotationPoint(-0.5F, 2F, -0.5F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		this.setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 19, 13);
		Shape16.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape16.setRotationPoint(-1F, -1F, -1F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		this.setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 28, 12);
		Shape17.addBox(0F, 0F, 0F, 1, 7, 1);
		Shape17.setRotationPoint(-0.5F, -8F, -0.5F);
		Shape17.setTextureSize(64, 32);
		Shape17.mirror = true;
		this.setRotation(Shape17, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 54, 0);
		Shape18.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape18.setRotationPoint(-0.5F, 0F, 0F);
		Shape18.setTextureSize(64, 32);
		Shape18.mirror = true;
		this.setRotation(Shape18, 0.5235988F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 54, 0);
		Shape19.addBox(-1F, 0F, -1F, 1, 2, 1);
		Shape19.setRotationPoint(0.5F, 0F, 0F);
		Shape19.setTextureSize(64, 32);
		Shape19.mirror = true;
		this.setRotation(Shape19, -0.5235988F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 42, 9);
		Shape20.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape20.setRotationPoint(0F, 0F, -0.5F);
		Shape20.setTextureSize(64, 32);
		Shape20.mirror = true;
		this.setRotation(Shape20, 0F, 0F, -0.5235988F);
		Shape20 = new ModelRenderer(this, 42, 9);
		Shape20.addBox(-1F, 0F, -1F, 1, 2, 1);
		Shape20.setRotationPoint(0F, 0F, 0.5F);
		Shape20.setTextureSize(64, 32);
		Shape20.mirror = true;
		this.setRotation(Shape20, 0F, 0F, 0.5235988F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
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
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape18.render(f5);
		Shape19.render(f5);
		Shape20.render(f5);
		Shape20.render(f5);
	}
	
	// Original method
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}