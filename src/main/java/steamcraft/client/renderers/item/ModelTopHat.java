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
 * The Class ModelTopHat.
 * 
 * @author Surseance (Johnny Eatmon) & Falkok15
 */
@SideOnly(Side.CLIENT)
public class ModelTopHat extends ModelBiped
{

	/** The Shape1. */
	ModelRenderer Shape1;

	/** The Shape2. */
	ModelRenderer Shape2;

	/**
	 * Instantiates a new model top hat.
	 * 
	 * @param f
	 *            the f
	 */
	public ModelTopHat(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64;
		// textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0F, 0F, 0F, 11, 1, 11);
		this.Shape1.setRotationPoint(-5.5F, -9F, -5.5F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 0, 14);
		this.Shape2.addBox(0F, 0F, 0F, 9, 8, 9);
		this.Shape2.setRotationPoint(-4.5F, -17F, -4.5F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);

		this.bipedHead.addChild(this.Shape1);
		this.bipedHead.addChild(this.Shape2);
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
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
