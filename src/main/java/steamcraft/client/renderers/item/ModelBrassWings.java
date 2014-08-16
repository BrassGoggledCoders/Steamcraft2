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

// TODO: Auto-generated Javadoc
/**
 * The Class ModelBrassWings.
 * 
 * @author Surseance (Johnny Eatmon) & Falkok15
 */
@SideOnly(Side.CLIENT)
public class ModelBrassWings extends ModelBiped
{

	/** The Shape1. */
	ModelRenderer Shape1, Shape2, Shape3, Shape4, Shape5, Shape6, Shape7, Shape8, Shape9, Shape10, Shape11, Shape12, Shape13, Shape15, Shape14;

	/**
	 * Instantiates a new model brass wings.
	 * 
	 * @param f
	 *            the f
	 */
	public ModelBrassWings(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64; <-- these things are bullshit, they do nothing
		// useful
		// textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0F, 0F, 0F, 4, 4, 2);
		this.Shape1.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 12, 0);
		this.Shape2.addBox(0F, 0F, 0F, 3, 1, 1);
		this.Shape2.setRotationPoint(1.5F, 0.5F, 2.5F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, -0.4363323F);
		this.Shape3 = new ModelRenderer(this, 12, 0);
		this.Shape3.addBox(0F, 0F, 0F, 3, 1, 1);
		this.Shape3.setRotationPoint(1.5F, 2.5F, 2.5F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, -0.4363323F);
		this.Shape6 = new ModelRenderer(this, 0, 6);
		this.Shape6.addBox(0F, 0F, 0F, 1, 6, 1);
		this.Shape6.setRotationPoint(4F, -0.8F, 2.5F);
		this.Shape6.setTextureSize(64, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0F, 0F, 0F);
		this.Shape7 = new ModelRenderer(this, 4, 6);
		this.Shape7.addBox(0F, 0F, 0F, 4, 1, 1);
		this.Shape7.setRotationPoint(4F, -1F, 2.5F);
		this.Shape7.setTextureSize(64, 32);
		this.Shape7.mirror = true;
		this.setRotation(this.Shape7, 0F, 0F, -0.1745329F);
		this.Shape4 = new ModelRenderer(this, 12, 2);
		this.Shape4.addBox(-3F, 0F, 0F, 3, 1, 1);
		this.Shape4.setRotationPoint(-1.5F, 0.5F, 2.5F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, 0.4363323F);
		this.Shape5 = new ModelRenderer(this, 12, 2);
		this.Shape5.addBox(-3F, 0F, 0F, 3, 1, 1);
		this.Shape5.setRotationPoint(-1.5F, 2.5F, 2.5F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0.4363323F);
		// Shape8.mirror = true;
		this.Shape8 = new ModelRenderer(this, 0, 6);
		this.Shape8.addBox(0F, 0F, 0F, 1, 6, 1);
		this.Shape8.setRotationPoint(-5F, -0.8F, 2.5F);
		this.Shape8.setTextureSize(64, 32);
		this.Shape8.mirror = true;
		this.setRotation(this.Shape8, 0F, 0F, 0F);
		// Shape8.mirror = false;
		// Shape9.mirror = true;
		this.Shape9 = new ModelRenderer(this, 20, 0);
		this.Shape9.addBox(-4F, 0F, 0F, 4, 1, 1);
		this.Shape9.setRotationPoint(-4F, -1F, 2.5F);
		this.Shape9.setTextureSize(64, 32);
		this.Shape9.mirror = true;
		this.setRotation(this.Shape9, 0F, 0F, 0.1745329F);
		// Shape9.mirror = false;
		// Shape10.mirror = true;
		this.Shape10 = new ModelRenderer(this, 32, 0);
		this.Shape10.addBox(0F, 0F, 0F, 4, 10, 0);
		this.Shape10.setRotationPoint(4F, -1F, 3F);
		this.Shape10.setTextureSize(64, 32);
		this.Shape10.mirror = true;
		this.setRotation(this.Shape10, 0F, 0F, 0F);
		// Shape10.mirror = false;
		this.Shape11 = new ModelRenderer(this, 32, 0);
		this.Shape11.addBox(0F, 0F, 0F, 4, 10, 0);
		this.Shape11.setRotationPoint(-8F, -1F, 3F);
		this.Shape11.setTextureSize(64, 32);
		this.Shape11.mirror = true;
		this.setRotation(this.Shape11, 0F, 0F, 0F);
		// Shape12.mirror = true;
		this.Shape12 = new ModelRenderer(this, 6, 10);
		this.Shape12.addBox(0F, 0F, 0F, 3, 3, 0);
		this.Shape12.setRotationPoint(4F, 9F, 3F);
		this.Shape12.setTextureSize(64, 32);
		this.Shape12.mirror = true;
		this.setRotation(this.Shape12, 0F, 0F, 0F);
		// Shape12.mirror = false;
		this.Shape13 = new ModelRenderer(this, 6, 10);
		this.Shape13.addBox(0F, 0F, 0F, 3, 3, 0);
		this.Shape13.setRotationPoint(-7F, 9F, 3F);
		this.Shape13.setTextureSize(64, 32);
		this.Shape13.mirror = true;
		this.setRotation(this.Shape13, 0F, 0F, 0F);
		this.Shape15 = new ModelRenderer(this, 20, 6);
		this.Shape15.addBox(0F, 0F, 0F, 2, 2, 0);
		this.Shape15.setRotationPoint(4F, 12F, 3F);
		this.Shape15.setTextureSize(64, 32);
		this.Shape15.mirror = true;
		this.setRotation(this.Shape15, 0F, 0F, 0F);
		// Shape14.mirror = true;
		this.Shape14 = new ModelRenderer(this, 20, 6);
		this.Shape14.addBox(0F, 0F, 0F, 2, 2, 0);
		this.Shape14.setRotationPoint(-6F, 12F, 3F);
		this.Shape14.setTextureSize(64, 32);
		this.Shape14.mirror = true;
		this.setRotation(this.Shape14, 0F, 0F, 0F);
		// Shape14.mirror = false;

		this.bipedBody.addChild(this.Shape1);
		this.bipedBody.addChild(this.Shape2);
		this.bipedBody.addChild(this.Shape3);
		this.bipedBody.addChild(this.Shape6);
		this.bipedBody.addChild(this.Shape7);
		this.bipedBody.addChild(this.Shape4);
		this.bipedBody.addChild(this.Shape5);
		this.bipedBody.addChild(this.Shape8);
		this.bipedBody.addChild(this.Shape9);
		this.bipedBody.addChild(this.Shape10);
		this.bipedBody.addChild(this.Shape11);
		this.bipedBody.addChild(this.Shape12);
		this.bipedBody.addChild(this.Shape13);
		this.bipedBody.addChild(this.Shape15);
		this.bipedBody.addChild(this.Shape14);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	// Techne method
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
