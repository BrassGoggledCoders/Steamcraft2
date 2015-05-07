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
	public final ModelRenderer shape1;
	public final ModelRenderer shape2;
	public final ModelRenderer shape3;
	public final ModelRenderer shape4;
	public final ModelRenderer shape5;
	public final ModelRenderer shape6;
	public final ModelRenderer shape7;
	public final ModelRenderer shape8;
	public final ModelRenderer shape9;
	public final ModelRenderer shape10;
	public final ModelRenderer shape11;
	public final ModelRenderer shape12;

	public ModelCrystal()
	{
		this.shape1 = new ModelRenderer(this);
		this.shape1.addBox(0F, 0F, 0F, 6, 7, 6);
		this.shape1.setRotationPoint(-3F, 17F, -3F);
		this.shape1.mirror = true;
		this.shape2 = new ModelRenderer(this, 26, 0);
		this.shape2.addBox(0F, 0F, 0F, 5, 5, 5);
		this.shape2.setRotationPoint(-2.5F, 12F, -2.5F);
		this.shape2.mirror = true;
		this.shape3 = new ModelRenderer(this, 0, 14);
		this.shape3.addBox(0F, 0F, 0F, 2, 4, 2);
		this.shape3.setRotationPoint(-1F, 8F, -1F);
		this.shape3.mirror = true;
		this.shape4 = new ModelRenderer(this, 50, 0);
		this.shape4.addBox(0F, 0F, 0F, 1, 4, 3);
		this.shape4.setRotationPoint(0F, 9F, -1.5F);
		this.shape4.mirror = true;
		this.setRotation(this.shape4, 0F, 0F, -0.2617994F);
		this.shape5 = new ModelRenderer(this, 50, 0);
		this.shape5.addBox(-1F, 0F, -3F, 1, 4, 3);
		this.shape5.setRotationPoint(0F, 9F, 1.5F);
		this.shape5.mirror = true;
		this.setRotation(this.shape5, 0F, 0F, 0.2617994F);
		this.shape6 = new ModelRenderer(this, 10, 14);
		this.shape6.addBox(0F, 0F, 0F, 3, 4, 1);
		this.shape6.setRotationPoint(-1.5F, 9F, 0.5F);
		this.shape6.mirror = true;
		this.setRotation(this.shape6, 0.2617994F, 0F, 0F);
		this.shape7 = new ModelRenderer(this, 10, 14);
		this.shape7.addBox(-3F, 0F, -1F, 3, 4, 1);
		this.shape7.setRotationPoint(1.5F, 9F, -0.5F);
		this.shape7.mirror = true;
		this.setRotation(this.shape7, -0.1745329F, 0F, 0F);
		this.shape8 = new ModelRenderer(this, 20, 14);
		this.shape8.addBox(0F, 0F, 0F, 2, 4, 2);
		this.shape8.setRotationPoint(-4.5F, 16F, 3F);
		this.shape8.mirror = true;
		this.setRotation(this.shape8, 0F, 0.7853982F, -0.6981317F);
		this.shape9 = new ModelRenderer(this, 0, 22);
		this.shape9.addBox(0F, 0F, 0F, 1, 2, 1);
		this.shape9.setRotationPoint(-4.5F, 15F, 4F);
		this.shape9.mirror = true;
		this.setRotation(this.shape9, 0F, 0.7853982F, -0.6981317F);
		this.shape10 = new ModelRenderer(this);
		this.shape10.addBox(0F, 0F, 0F, 1, 3, 1);
		this.shape10.setRotationPoint(3F, 14F, -2F);
		this.shape10.mirror = true;
		this.setRotation(this.shape10, 0F, 0.5235988F, 0.5235988F);
		this.shape11 = new ModelRenderer(this, 30, 14);
		this.shape11.addBox(0F, 0F, 0F, 2, 2, 2);
		this.shape11.setRotationPoint(-4.5F, 22F, -2.5F);
		this.shape11.mirror = true;
		this.setRotation(this.shape11, 0F, 0F, -0.7853982F);
		this.shape12 = new ModelRenderer(this, 20, 0);
		this.shape12.addBox(0F, 0F, 0F, 1, 1, 1);
		this.shape12.setRotationPoint(-4.5F, 21F, -2F);
		this.shape12.mirror = true;
		this.setRotation(this.shape12, 0F, 0F, -0.7853982F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.shape1.render(f5);
		this.shape2.render(f5);
		this.shape3.render(f5);
		this.shape4.render(f5);
		this.shape5.render(f5);
		this.shape6.render(f5);
		this.shape7.render(f5);
		this.shape8.render(f5);
		this.shape9.render(f5);
		this.shape10.render(f5);
		this.shape11.render(f5);
		this.shape12.render(f5);
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
