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
 * File created @ [Apr 8, 2014, 3:23:43 PM]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelLightningRod.
 *
 * @author Surseance (Johnny Eatmon) & Falkok15
 */
@SideOnly(Side.CLIENT)
public class ModelLightningRod extends ModelBase
{

	/** The Shape1. */
	ModelRenderer Shape1;

	/** The Shape2. */
	ModelRenderer Shape2;

	/** The Shape3. */
	ModelRenderer Shape3;

	/** The Shape4. */
	ModelRenderer Shape4;

	/** The Shape5. */
	ModelRenderer Shape5;

	/** The Shape6. */
	ModelRenderer Shape6;

	/** The Shape7. */
	ModelRenderer Shape7;

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

	/** The Shape14. */
	ModelRenderer Shape14;

	/** The Shape15. */
	ModelRenderer Shape15;

	/** The Shape16. */
	ModelRenderer Shape16;

	/** The Shape17. */
	ModelRenderer Shape17;

	/** The Shape18. */
	ModelRenderer Shape18;

	/** The Shape19. */
	ModelRenderer Shape19;

	/** The Shape20. */
	ModelRenderer Shape20;

	// ModelRenderer Shape20;

	/**
	 * Instantiates a new model lightning rod.
	 */
	public ModelLightningRod()
	{
		textureWidth = 64;
		textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 6, 6);
		Shape1.setRotationPoint(-3F, 18F, -3F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 12);
		Shape2.addBox(0F, 0F, 0F, 2, 10, 2);
		Shape2.setRotationPoint(-1F, 8F, -1F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 18, 0);
		Shape3.addBox(0F, 0F, 0F, 6, 1, 2);
		Shape3.setRotationPoint(-3F, 7F, -1F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 34, 0);
		Shape4.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape4.setRotationPoint(-1F, 7F, 1F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 34, 0);
		Shape5.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape5.setRotationPoint(-1F, 7F, -3F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 24);
		Shape6.addBox(0F, 0F, 0F, 4, 2, 4);
		Shape6.setRotationPoint(-2F, 16F, -2F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 42, 0);
		Shape7.addBox(0F, 0F, 0F, 2, 5, 1);
		Shape7.setRotationPoint(-1F, 2F, -4F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 42, 0);
		Shape8.addBox(0F, 0F, 0F, 2, 5, 1);
		Shape8.setRotationPoint(-1F, 2F, 3F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 8, 12);
		Shape9.addBox(0F, 0F, 0F, 2, 1, 6);
		Shape9.setRotationPoint(-1F, 1F, -3F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 30, 3);
		Shape10.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape10.setRotationPoint(-1.5F, 3F, -1.5F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 24, 4);
		Shape11.addBox(0F, 0F, 0F, 1, 5, 2);
		Shape11.setRotationPoint(3F, 2F, -1F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 24, 4);
		Shape12.addBox(0F, 0F, 0F, 1, 5, 2);
		Shape12.setRotationPoint(-4F, 2F, -1F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 8, 20);
		Shape13.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape13.setRotationPoint(1F, 1F, -1F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 8, 20);
		Shape14.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape14.setRotationPoint(-3F, 1F, -1F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 48, 0);
		Shape15.addBox(0F, 0F, 0F, 1, 5, 1);
		Shape15.setRotationPoint(-0.5F, 2F, -0.5F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 19, 13);
		Shape16.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape16.setRotationPoint(-1F, -1F, -1F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 28, 12);
		Shape17.addBox(0F, 0F, 0F, 1, 7, 1);
		Shape17.setRotationPoint(-0.5F, -8F, -0.5F);
		Shape17.setTextureSize(64, 32);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 54, 0);
		Shape18.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape18.setRotationPoint(-0.5F, 0F, 0F);
		Shape18.setTextureSize(64, 32);
		Shape18.mirror = true;
		setRotation(Shape18, 0.5235988F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 54, 0);
		Shape19.addBox(-1F, 0F, -1F, 1, 2, 1);
		Shape19.setRotationPoint(0.5F, 0F, 0F);
		Shape19.setTextureSize(64, 32);
		Shape19.mirror = true;
		setRotation(Shape19, -0.5235988F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 42, 9);
		Shape20.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape20.setRotationPoint(0F, 0F, -0.5F);
		Shape20.setTextureSize(64, 32);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, -0.5235988F);
		Shape20 = new ModelRenderer(this, 42, 9);
		Shape20.addBox(-1F, 0F, -1F, 1, 2, 1);
		Shape20.setRotationPoint(0F, 0F, 0.5F);
		Shape20.setTextureSize(64, 32);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, 0.5235988F);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.client.model.ModelBase#render(net.minecraft.entity.Entity,
	 * float, float, float, float, float, float)
	 */
	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
	 * @see net.minecraft.client.model.ModelBase#setRotationAngles(float, float,
	 * float, float, float, float, net.minecraft.entity.Entity)
	 */
	@Override
	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
