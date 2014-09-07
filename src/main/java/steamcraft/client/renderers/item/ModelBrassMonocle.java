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
 * @author Surseance (Johnny Eatmon) & Falkok15
 * 
 */
@SideOnly(Side.CLIENT)
public class ModelBrassMonocle extends ModelBiped
{
	ModelRenderer Shape1, Shape2, Shape3, Shape4, Shape5;

	public ModelBrassMonocle(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64;
		// textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Shape1.setRotationPoint(-4F, -4.5F, -5F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 0, 4);
		this.Shape2.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Shape2.setRotationPoint(-3F, -5.5F, -5F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
		this.Shape3 = new ModelRenderer(this, 0, 0);
		this.Shape3.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Shape3.setRotationPoint(-1F, -4.5F, -5F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);
		this.Shape4 = new ModelRenderer(this, 0, 4);
		this.Shape4.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Shape4.setRotationPoint(-3F, -2.5F, -5F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, 0F);
		this.Shape5 = new ModelRenderer(this, 4, 0);
		this.Shape5.addBox(0F, 0F, 0F, 1, 1, 0);
		this.Shape5.setRotationPoint(-4F, -1.5F, -4.5F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0F);
		this.Shape5 = new ModelRenderer(this, 4, 1);
		this.Shape5.addBox(0F, 0F, 0F, 1, 3, 0);
		this.Shape5.setRotationPoint(-5F, -0.5F, -4.5F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0F);

		this.bipedHead.addChild(this.Shape3);
		this.bipedHead.addChild(this.Shape1);
		this.bipedHead.addChild(this.Shape2);
		this.bipedHead.addChild(this.Shape4);
		this.bipedHead.addChild(this.Shape5);

	}

	@Override
	public void render(final Entity entity, final float f1, final float f2, final float f3, final float f4, final float f5, final float f6)
	{
		super.render(entity, f1, f2, f3, f4, f5, f6);
		this.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float f1, final float f2, final float f3, final float f4, final float f5, final float f6, final Entity entity)
	{
		super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}
}
