
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created using Tabula 4.0.0
 */
public class ModelTopHat extends ModelBase
{
	public ModelRenderer brim;
	public ModelRenderer top;

	public ModelTopHat()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.top = new ModelRenderer(this, 0, 0);
		this.top.setRotationPoint(0.0F, 0F, 0.0F);
		this.top.addBox(-4F, -17.0F, -4F, 8, 8, 8);
		this.brim = new ModelRenderer(this, 0, 0);
		this.brim.setRotationPoint(0.0F, 0F, 0.0F);
		this.brim.addBox(-5.5F, -9.0F, -5.5F, 11, 1, 11);
	}

	@Override
	public void render(Entity p_78088_1_, float rotX, float rotY, float rotZ, float afloat, float p_78088_6_, float f5)
	{
		this.brim.rotateAngleX = rotX;
		this.brim.rotateAngleY = rotY;
		this.brim.rotateAngleZ = rotZ;
		this.top.rotateAngleX = rotX;
		this.top.rotateAngleY = rotY;
		this.top.rotateAngleZ = rotZ;
		this.top.render(f5);
		this.brim.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
