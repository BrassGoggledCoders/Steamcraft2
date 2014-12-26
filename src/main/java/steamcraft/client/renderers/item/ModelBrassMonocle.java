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
	public final ModelRenderer shape1;
	public final ModelRenderer shape2;
	public final ModelRenderer shape3;
	public final ModelRenderer shape4;
	public ModelRenderer shape5;

	public ModelBrassMonocle(float f)
	{
		super(f, 0, 64, 32);
		this.shape1 = new ModelRenderer(this);
		this.shape1.addBox(0F, 0F, 0F, 1, 2, 1);
		this.shape1.setRotationPoint(-4F, -4.5F, -5F);
		// shape1.setRotationPoint(0F, 0F, 0F);
		this.shape1.mirror = true;
		this.shape2 = new ModelRenderer(this, 0, 4);
		this.shape2.addBox(0F, 0F, 0F, 2, 1, 1);
		this.shape2.setRotationPoint(-3F, -5.5F, -5F);
		this.shape2.mirror = true;
		this.shape3 = new ModelRenderer(this);
		this.shape3.addBox(0F, 0F, 0F, 1, 2, 1);
		this.shape3.setRotationPoint(-1F, -4.5F, -5F);
		this.shape3.mirror = true;
		this.shape4 = new ModelRenderer(this, 0, 4);
		this.shape4.addBox(0F, 0F, 0F, 2, 1, 1);
		this.shape4.setRotationPoint(-3F, -2.5F, -5F);
		this.shape4.mirror = true;
		this.shape5 = new ModelRenderer(this, 4, 0);
		this.shape5.addBox(0F, 0F, 0F, 1, 1, 0);
		this.shape5.setRotationPoint(-4F, -1.5F, -4.5F);
		this.shape5.mirror = true;
		this.shape5 = new ModelRenderer(this, 4, 1);
		this.shape5.addBox(0F, 0F, 0F, 1, 3, 0);
		this.shape5.setRotationPoint(-5F, -0.5F, -4.5F);
		this.shape5.mirror = true;

		this.bipedHead.addChild(this.shape3);
		this.bipedHead.addChild(this.shape1);
		this.bipedHead.addChild(this.shape2);
		this.bipedHead.addChild(this.shape4);
		this.bipedHead.addChild(this.shape5);
		this.bipedHead.addChild(this.shape5);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6)
	{
		super.render(entity, f1, f2, f3, f4, f5, f6);
		this.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity)
	{
		super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}
}
