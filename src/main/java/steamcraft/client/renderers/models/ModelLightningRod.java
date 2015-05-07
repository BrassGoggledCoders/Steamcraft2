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
	public final ModelRenderer shape13;
	public final ModelRenderer shape14;
	public final ModelRenderer shape15;
	public final ModelRenderer shape16;
	public final ModelRenderer shape17;
	public final ModelRenderer shape18;
	public final ModelRenderer shape19;
	public ModelRenderer shape20;

	public ModelLightningRod()
	{
		this.shape1 = new ModelRenderer(this);
		this.shape1.addBox(0F, 0F, 0F, 6, 6, 6);
		this.shape1.setRotationPoint(-3F, 18F, -3F);
		this.shape1.mirror = true;
		this.shape2 = new ModelRenderer(this, 0, 12);
		this.shape2.addBox(0F, 0F, 0F, 2, 10, 2);
		this.shape2.setRotationPoint(-1F, 8F, -1F);
		this.shape2.mirror = true;
		this.shape3 = new ModelRenderer(this, 18, 0);
		this.shape3.addBox(0F, 0F, 0F, 6, 1, 2);
		this.shape3.setRotationPoint(-3F, 7F, -1F);
		this.shape3.mirror = true;
		this.shape4 = new ModelRenderer(this, 34, 0);
		this.shape4.addBox(0F, 0F, 0F, 2, 1, 2);
		this.shape4.setRotationPoint(-1F, 7F, 1F);
		this.shape4.mirror = true;
		this.shape5 = new ModelRenderer(this, 34, 0);
		this.shape5.addBox(0F, 0F, 0F, 2, 1, 2);
		this.shape5.setRotationPoint(-1F, 7F, -3F);
		this.shape5.mirror = true;
		this.shape6 = new ModelRenderer(this, 0, 24);
		this.shape6.addBox(0F, 0F, 0F, 4, 2, 4);
		this.shape6.setRotationPoint(-2F, 16F, -2F);
		this.shape6.mirror = true;
		this.shape7 = new ModelRenderer(this, 42, 0);
		this.shape7.addBox(0F, 0F, 0F, 2, 5, 1);
		this.shape7.setRotationPoint(-1F, 2F, -4F);
		this.shape7.mirror = true;
		this.shape8 = new ModelRenderer(this, 42, 0);
		this.shape8.addBox(0F, 0F, 0F, 2, 5, 1);
		this.shape8.setRotationPoint(-1F, 2F, 3F);
		this.shape8.mirror = true;
		this.shape9 = new ModelRenderer(this, 8, 12);
		this.shape9.addBox(0F, 0F, 0F, 2, 1, 6);
		this.shape9.setRotationPoint(-1F, 1F, -3F);
		this.shape9.mirror = true;
		this.shape10 = new ModelRenderer(this, 30, 3);
		this.shape10.addBox(0F, 0F, 0F, 3, 3, 3);
		this.shape10.setRotationPoint(-1.5F, 3F, -1.5F);
		this.shape10.mirror = true;
		this.shape11 = new ModelRenderer(this, 24, 4);
		this.shape11.addBox(0F, 0F, 0F, 1, 5, 2);
		this.shape11.setRotationPoint(3F, 2F, -1F);
		this.shape11.mirror = true;
		this.shape12 = new ModelRenderer(this, 24, 4);
		this.shape12.addBox(0F, 0F, 0F, 1, 5, 2);
		this.shape12.setRotationPoint(-4F, 2F, -1F);
		this.shape12.mirror = true;
		this.shape13 = new ModelRenderer(this, 8, 20);
		this.shape13.addBox(0F, 0F, 0F, 2, 1, 2);
		this.shape13.setRotationPoint(1F, 1F, -1F);
		this.shape13.mirror = true;
		this.shape14 = new ModelRenderer(this, 8, 20);
		this.shape14.addBox(0F, 0F, 0F, 2, 1, 2);
		this.shape14.setRotationPoint(-3F, 1F, -1F);
		this.shape14.mirror = true;
		this.shape15 = new ModelRenderer(this, 48, 0);
		this.shape15.addBox(0F, 0F, 0F, 1, 5, 1);
		this.shape15.setRotationPoint(-0.5F, 2F, -0.5F);
		this.shape15.mirror = true;
		this.shape16 = new ModelRenderer(this, 19, 13);
		this.shape16.addBox(0F, 0F, 0F, 2, 2, 2);
		this.shape16.setRotationPoint(-1F, -1F, -1F);
		this.shape16.mirror = true;
		this.shape17 = new ModelRenderer(this, 28, 12);
		this.shape17.addBox(0F, 0F, 0F, 1, 7, 1);
		this.shape17.setRotationPoint(-0.5F, -8F, -0.5F);
		this.shape17.mirror = true;
		this.shape18 = new ModelRenderer(this, 54, 0);
		this.shape18.addBox(0F, 0F, 0F, 1, 2, 1);
		this.shape18.setRotationPoint(-0.5F, 0F, 0F);
		this.shape18.mirror = true;
		this.setRotation(this.shape18, 0.5235988F, 0F, 0F);
		this.shape19 = new ModelRenderer(this, 54, 0);
		this.shape19.addBox(-1F, 0F, -1F, 1, 2, 1);
		this.shape19.setRotationPoint(0.5F, 0F, 0F);
		this.shape19.mirror = true;
		this.setRotation(this.shape19, -0.5235988F, 0F, 0F);
		this.shape20 = new ModelRenderer(this, 42, 9);
		this.shape20.addBox(0F, 0F, 0F, 1, 2, 1);
		this.shape20.setRotationPoint(0F, 0F, -0.5F);
		this.shape20.mirror = true;
		this.setRotation(this.shape20, 0F, 0F, -0.5235988F);
		this.shape20 = new ModelRenderer(this, 42, 9);
		this.shape20.addBox(-1F, 0F, -1F, 1, 2, 1);
		this.shape20.setRotationPoint(0F, 0F, 0.5F);
		this.shape20.mirror = true;
		this.setRotation(this.shape20, 0F, 0F, 0.5235988F);
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
		this.shape13.render(f5);
		this.shape14.render(f5);
		this.shape15.render(f5);
		this.shape16.render(f5);
		this.shape17.render(f5);
		this.shape18.render(f5);
		this.shape19.render(f5);
		this.shape20.render(f5);
		this.shape20.render(f5);
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
