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
public class ModelJetpack extends ModelBiped
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;

	public ModelJetpack(final float f)
	{
		super(f, 0, 64, 32);
		// textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(5F, 0F, 0F, 4, 6, 2);
		this.Shape1.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 0, 0);
		this.Shape2.addBox(-5F, 0F, 0F, 4, 6, 2);
		this.Shape2.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
		this.Shape3 = new ModelRenderer(this, 0, 0);
		this.Shape3.addBox(-1F, 0F, 0F, 6, 2, 2);
		this.Shape3.setRotationPoint(-2F, 0F, 2F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);

		// Shape14.mirror = false;

		this.bipedBody.addChild(this.Shape1);
		this.bipedBody.addChild(this.Shape2);
		this.bipedBody.addChild(this.Shape3);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
