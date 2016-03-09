package xyz.brassgoggledcoders.steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorEditor - warlordjoens Created using Tabula 4.0.0
 */
public class ModelArmorEditor extends ModelBase
{
	public ModelRenderer base;
	public ModelRenderer base1;
	public ModelRenderer base2;

	public ModelArmorEditor()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(-8.0F, 22.0F, -8.0F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
		this.base1 = new ModelRenderer(this, 2, 0);
		this.base1.setRotationPoint(-7.0F, 12.0F, -7.0F);
		this.base1.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14);
		this.base2 = new ModelRenderer(this, 0, 0);
		this.base2.setRotationPoint(-8.0F, 10.0F, -8.0F);
		this.base2.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.base.render(f5);
		this.base1.render(f5);
		this.base2.render(f5);
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
