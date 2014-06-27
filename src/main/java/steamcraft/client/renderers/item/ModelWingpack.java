/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 3:15:51 PM]
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
public class ModelWingpack extends ModelBiped
{

	/** The Shape1. */
	ModelRenderer Shape1;

	/** The Shape2. */
	ModelRenderer Shape2;

	/** The Shape3. */
	ModelRenderer Shape3;

	/** The Shape6. */
	ModelRenderer Shape6;

	/** The Shape7. */
	ModelRenderer Shape7;

	/** The Shape4. */
	ModelRenderer Shape4;

	/** The Shape5. */
	ModelRenderer Shape5;

	/** The Shape8. */
	ModelRenderer Shape8;

	/** The Shape9. */
	ModelRenderer Shape9;

	/** The Shape10. */
	ModelRenderer Shape10;

	/** The Shape11. */
	ModelRenderer Shape11;

	/** The Shape12. */
	ModelRenderer Shape12;

	/** The Shape13. */
	ModelRenderer Shape13;

	/** The Shape15. */
	ModelRenderer Shape15, Shape16, Shape17;

	/** The Shape14. */
	ModelRenderer Shape14;

	/**
	 * Instantiates a new model brass wings.
	 *
	 * @param f
	 *            the f
	 */
	public ModelWingpack(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64; <-- these things are bullshit, they do nothing
		// useful
		// textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 4, 4, 2);
		Shape1.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 12, 0);
		Shape2.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape2.setRotationPoint(1.5F, 0.5F, 2.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, -0.4363323F);
		Shape3 = new ModelRenderer(this, 12, 0);
		Shape3.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape3.setRotationPoint(1.5F, 2.5F, 2.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, -0.4363323F);
		Shape6 = new ModelRenderer(this, 0, 6);
		Shape6.addBox(0F, 0F, 0F, 1, 6, 1);
		Shape6.setRotationPoint(4F, -0.8F, 2.5F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 4, 6);
		Shape7.addBox(0F, 0F, 0F, 4, 1, 1);
		Shape7.setRotationPoint(4F, -1F, 2.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, -0.1745329F);
		Shape4 = new ModelRenderer(this, 12, 2);
		Shape4.addBox(-3F, 0F, 0F, 3, 1, 1);
		Shape4.setRotationPoint(-1.5F, 0.5F, 2.5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.4363323F);
		Shape5 = new ModelRenderer(this, 12, 2);
		Shape5.addBox(-3F, 0F, 0F, 3, 1, 1);
		Shape5.setRotationPoint(-1.5F, 2.5F, 2.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0.4363323F);
		// Shape8.mirror = true;
		Shape8 = new ModelRenderer(this, 0, 6);
		Shape8.addBox(0F, 0F, 0F, 1, 6, 1);
		Shape8.setRotationPoint(-5F, -0.8F, 2.5F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		// Shape8.mirror = false;
		// Shape9.mirror = true;
		Shape9 = new ModelRenderer(this, 20, 0);
		Shape9.addBox(-4F, 0F, 0F, 4, 1, 1);
		Shape9.setRotationPoint(-4F, -1F, 2.5F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0.1745329F);
		// Shape9.mirror = false;
		// Shape10.mirror = true;
		Shape10 = new ModelRenderer(this, 32, 0);
		Shape10.addBox(0F, 0F, 0F, 4, 10, 0);
		Shape10.setRotationPoint(4F, -1F, 3F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		// Shape10.mirror = false;
		Shape11 = new ModelRenderer(this, 32, 0);
		Shape11.addBox(0F, 0F, 0F, 4, 10, 0);
		Shape11.setRotationPoint(-8F, -1F, 3F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		// Shape12.mirror = true;
		Shape12 = new ModelRenderer(this, 6, 10);
		Shape12.addBox(0F, 0F, 0F, 3, 3, 0);
		Shape12.setRotationPoint(4F, 9F, 3F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		// Shape12.mirror = false;
		Shape13 = new ModelRenderer(this, 6, 10);
		Shape13.addBox(0F, 0F, 0F, 3, 3, 0);
		Shape13.setRotationPoint(-7F, 9F, 3F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 20, 6);
		Shape15.addBox(0F, 0F, 0F, 2, 2, 0);
		Shape15.setRotationPoint(4F, 12F, 3F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		// Shape14.mirror = true;
		Shape14 = new ModelRenderer(this, 20, 6);
		Shape14.addBox(0F, 0F, 0F, 2, 2, 0);
		Shape14.setRotationPoint(-6F, 12F, 3F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		// Shape14.mirror = false;
		Shape15 = new ModelRenderer(this, 0, 0);
		Shape15.addBox(5F, 0F, 0F, 4, 6, 2);
		Shape15.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 0);
		Shape16.addBox(-5F, 0F, 0F, 4, 6, 2);
		Shape16.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 0, 0);
		Shape17.addBox(-1F, 0F, 0F, 6, 2, 2);
		Shape17.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape17.setTextureSize(64, 172);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);

		bipedBody.addChild(Shape1);
		bipedBody.addChild(Shape2);
		bipedBody.addChild(Shape3);
		bipedBody.addChild(Shape6);
		bipedBody.addChild(Shape7);
		bipedBody.addChild(Shape4);
		bipedBody.addChild(Shape5);
		bipedBody.addChild(Shape8);
		bipedBody.addChild(Shape9);
		bipedBody.addChild(Shape10);
		bipedBody.addChild(Shape11);
		bipedBody.addChild(Shape12);
		bipedBody.addChild(Shape13);
		bipedBody.addChild(Shape15);
		bipedBody.addChild(Shape14);
		bipedBody.addChild(Shape15);
		bipedBody.addChild(Shape16);
		bipedBody.addChild(Shape17);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.client.model.ModelBiped#render(net.minecraft.entity.Entity,
	 * float, float, float, float, float, float)
	 */
	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	// Techne method
	/**
	 * Sets the rotation.
	 *
	 * @param model
	 *            the model
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.client.model.ModelBiped#setRotationAngles(float,
	 * float, float, float, float, float, net.minecraft.entity.Entity)
	 */
	@Override
	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
