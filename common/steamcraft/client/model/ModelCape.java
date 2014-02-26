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
 * File created @ [Feb 1, 2014, 8:29:28 AM]
 */
package common.steamcraft.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author MrArcane111 & Falkok15
 *
 */
public class ModelCape extends ModelBiped
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;

	public ModelCape(float f)
	{
		super(f, 0, 64, 32);
		//textureWidth = 64;
		//textureHeight = 32;
		//Shape1.mirror = true;
		Shape1 = new ModelRenderer(this, 46, 0);
		Shape1.addBox(-4F, 0F, 0F, 8, 14, 1);
		Shape1.setRotationPoint(0F, 0F, 2F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0.1745329F, 0F, 0F);
		Shape1.mirror = false;
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 9, 1, 5);
		Shape2.setRotationPoint(-4.5F, 11F, -2.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 8);
		Shape3.addBox(0F, 0F, 0F, 2, 2, 1);
		Shape3.setRotationPoint(-1F, 10.5F, -3F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		
		this.bipedBody.addChild(Shape1);
		this.bipedBody.addChild(Shape2);
		this.bipedBody.addChild(Shape3);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		/*
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		*/
	}

	// Techne method
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
