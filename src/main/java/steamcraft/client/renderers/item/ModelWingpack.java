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
package steamcraft.client.renderers.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 *
 */
@SideOnly(Side.CLIENT)
public class ModelWingpack extends ModelBiped
{
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;
	private final ModelRenderer shape12;
	private final ModelRenderer shape13;
	private ModelRenderer shape15;
	private final ModelRenderer shape16;
	private final ModelRenderer shape17;
	private final ModelRenderer shape14;

	public ModelWingpack(float f)
	{
		super(f, 0, 64, 32);
		this.shape1 = new ModelRenderer(this);
		this.shape1.addBox(0F, 0F, 0F, 4, 4, 2);
		this.shape1.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape1.mirror = true;
		this.shape2 = new ModelRenderer(this, 12, 0);
		this.shape2.addBox(0F, 0F, 0F, 3, 1, 1);
		this.shape2.setRotationPoint(1.5F, 0.5F, 2.5F);
		this.shape2.mirror = true;
		this.setRotation(this.shape2, 0F, 0F, -0.4363323F);
		this.shape3 = new ModelRenderer(this, 12, 0);
		this.shape3.addBox(0F, 0F, 0F, 3, 1, 1);
		this.shape3.setRotationPoint(1.5F, 2.5F, 2.5F);
		this.shape3.mirror = true;
		this.setRotation(this.shape3, 0F, 0F, -0.4363323F);
		this.shape6 = new ModelRenderer(this, 0, 6);
		this.shape6.addBox(0F, 0F, 0F, 1, 6, 1);
		this.shape6.setRotationPoint(4F, -0.8F, 2.5F);
		this.shape6.mirror = true;
		this.shape7 = new ModelRenderer(this, 4, 6);
		this.shape7.addBox(0F, 0F, 0F, 4, 1, 1);
		this.shape7.setRotationPoint(4F, -1F, 2.5F);
		this.shape7.mirror = true;
		this.setRotation(this.shape7, 0F, 0F, -0.1745329F);
		this.shape4 = new ModelRenderer(this, 12, 2);
		this.shape4.addBox(-3F, 0F, 0F, 3, 1, 1);
		this.shape4.setRotationPoint(-1.5F, 0.5F, 2.5F);
		this.shape4.mirror = true;
		this.setRotation(this.shape4, 0F, 0F, 0.4363323F);
		this.shape5 = new ModelRenderer(this, 12, 2);
		this.shape5.addBox(-3F, 0F, 0F, 3, 1, 1);
		this.shape5.setRotationPoint(-1.5F, 2.5F, 2.5F);
		this.shape5.mirror = true;
		this.setRotation(this.shape5, 0F, 0F, 0.4363323F);
		// shape8.mirror = true;
		this.shape8 = new ModelRenderer(this, 0, 6);
		this.shape8.addBox(0F, 0F, 0F, 1, 6, 1);
		this.shape8.setRotationPoint(-5F, -0.8F, 2.5F);
		this.shape8.mirror = true;
		// shape8.mirror = false;
		// shape9.mirror = true;
		this.shape9 = new ModelRenderer(this, 20, 0);
		this.shape9.addBox(-4F, 0F, 0F, 4, 1, 1);
		this.shape9.setRotationPoint(-4F, -1F, 2.5F);
		this.shape9.mirror = true;
		this.setRotation(this.shape9, 0F, 0F, 0.1745329F);
		// shape9.mirror = false;
		// shape10.mirror = true;
		this.shape10 = new ModelRenderer(this, 32, 0);
		this.shape10.addBox(0F, 0F, 0F, 4, 10, 0);
		this.shape10.setRotationPoint(4F, -1F, 3F);
		this.shape10.mirror = true;
		// shape10.mirror = false;
		this.shape11 = new ModelRenderer(this, 32, 0);
		this.shape11.addBox(0F, 0F, 0F, 4, 10, 0);
		this.shape11.setRotationPoint(-8F, -1F, 3F);
		this.shape11.mirror = true;
		// shape12.mirror = true;
		this.shape12 = new ModelRenderer(this, 6, 10);
		this.shape12.addBox(0F, 0F, 0F, 3, 3, 0);
		this.shape12.setRotationPoint(4F, 9F, 3F);
		this.shape12.mirror = true;
		// shape12.mirror = false;
		this.shape13 = new ModelRenderer(this, 6, 10);
		this.shape13.addBox(0F, 0F, 0F, 3, 3, 0);
		this.shape13.setRotationPoint(-7F, 9F, 3F);
		this.shape13.mirror = true;
		this.shape15 = new ModelRenderer(this, 20, 6);
		this.shape15.addBox(0F, 0F, 0F, 2, 2, 0);
		this.shape15.setRotationPoint(4F, 12F, 3F);
		this.shape15.mirror = true;
		// shape14.mirror = true;
		this.shape14 = new ModelRenderer(this, 20, 6);
		this.shape14.addBox(0F, 0F, 0F, 2, 2, 0);
		this.shape14.setRotationPoint(-6F, 12F, 3F);
		this.shape14.mirror = true;
		// shape14.mirror = false;
		this.shape15 = new ModelRenderer(this);
		this.shape15.addBox(5F, 0F, 0F, 4, 6, 2);
		this.shape15.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape15.mirror = true;
		this.shape16 = new ModelRenderer(this);
		this.shape16.addBox(-5F, 0F, 0F, 4, 6, 2);
		this.shape16.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape16.mirror = true;
		this.shape17 = new ModelRenderer(this);
		this.shape17.addBox(-1F, 0F, 0F, 6, 2, 2);
		this.shape17.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape17.setTextureSize(64, 172);
		this.shape17.mirror = true;

		this.bipedBody.addChild(this.shape1);
		this.bipedBody.addChild(this.shape2);
		this.bipedBody.addChild(this.shape3);
		this.bipedBody.addChild(this.shape6);
		this.bipedBody.addChild(this.shape7);
		this.bipedBody.addChild(this.shape4);
		this.bipedBody.addChild(this.shape5);
		this.bipedBody.addChild(this.shape8);
		this.bipedBody.addChild(this.shape9);
		this.bipedBody.addChild(this.shape10);
		this.bipedBody.addChild(this.shape11);
		this.bipedBody.addChild(this.shape12);
		this.bipedBody.addChild(this.shape13);
		this.bipedBody.addChild(this.shape15);
		this.bipedBody.addChild(this.shape14);
		this.bipedBody.addChild(this.shape15);
		this.bipedBody.addChild(this.shape16);
		this.bipedBody.addChild(this.shape17);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
