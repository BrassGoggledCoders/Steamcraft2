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
 * File created @ [Apr 8, 2014, 3:18:03 PM]
 */
package steamcraft.client.renderers.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelCape.
 * 
 * @author Surseance (Johnny Eatmon) & Falkok15
 */
@SideOnly(Side.CLIENT)
public class ModelCape extends ModelBiped
{

	/** The Shape1. */
	ModelRenderer Shape1;

	/** The Shape2. */
	ModelRenderer Shape2;

	/** The Shape3. */
	ModelRenderer Shape3;

	/**
	 * Instantiates a new model cape.
	 * 
	 * @param f
	 *            the f
	 */
	public ModelCape(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64;
		// textureHeight = 32;
		// Shape1.mirror = true;
		Shape1 = new ModelRenderer(this, 46, 0);
		Shape1.addBox(-4F, 0F, 0F, 8, 14, 1);
		Shape1.setRotationPoint(0F, 0F, 2F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.1745329F, 0F, 0F);
		Shape1.mirror = false;
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 9, 1, 5);
		Shape2.setRotationPoint(-4.5F, 11F, -2.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 8);
		Shape3.addBox(0F, 0F, 0F, 2, 2, 1);
		Shape3.setRotationPoint(-1F, 10.5F, -3F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);

		bipedBody.addChild(Shape1);
		bipedBody.addChild(Shape2);
		bipedBody.addChild(Shape3);
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
		// Shape1.render(f5);
		// Shape2.render(f5);
		// Shape3.render(f5);
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
