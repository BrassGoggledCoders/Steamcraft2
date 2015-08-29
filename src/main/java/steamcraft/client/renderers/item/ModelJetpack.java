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
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;

	public ModelJetpack(float f)
	{
		super(f, 0, 64, 32);
		this.shape1 = new ModelRenderer(this);
		this.shape1.addBox(5F, 0F, 0F, 4, 6, 2);
		this.shape1.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape1.mirror = true;
		this.shape2 = new ModelRenderer(this);
		this.shape2.addBox(-5F, 0F, 0F, 4, 6, 2);
		this.shape2.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape2.mirror = true;
		this.shape3 = new ModelRenderer(this);
		this.shape3.addBox(-1F, 0F, 0F, 6, 2, 2);
		this.shape3.setRotationPoint(-2F, 0F, 2F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape3.mirror = true;

		// shape14.mirror = false;

		this.bipedBody.addChild(this.shape1);
		this.bipedBody.addChild(this.shape2);
		this.bipedBody.addChild(this.shape3);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	/*
	 * private void setRotation(ModelRenderer model, float x, float y, float z)
	 * { model.rotateAngleX = x; model.rotateAngleY = y; model.rotateAngleZ = z;
	 * }
	 */

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
