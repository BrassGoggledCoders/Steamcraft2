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
public class ModelJetpack extends ModelBiped
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
	ModelRenderer Shape15;

	/** The Shape14. */
	ModelRenderer Shape14;

	/**
	 * Instantiates a new model brass wings.
	 * 
	 * @param f
	 *            the f
	 */
	public ModelJetpack(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64; <-- these things are bullshit, they do nothing
		// useful
		// textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(5F, 0F, 0F, 4, 6, 2);
		Shape1.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-5F, 0F, 0F, 4, 6, 2);
		Shape2.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-1F, 0F, 0F, 6, 2, 2);
		Shape3.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);

		// Shape14.mirror = false;

		bipedBody.addChild(Shape1);
		bipedBody.addChild(Shape2);
		bipedBody.addChild(Shape3);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
