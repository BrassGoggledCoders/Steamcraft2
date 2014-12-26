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
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCharger extends ModelBase
{
	public final ModelRenderer top;
	public final ModelRenderer post1;
	public final ModelRenderer post;
	public final ModelRenderer post3;
	public final ModelRenderer post2;
	public final ModelRenderer base;
	public final ModelRenderer base2;

	public ModelCharger()
	{
		this.top = new ModelRenderer(this);
		this.top.addBox(0F, 0F, 0F, 15, 2, 15);
		this.top.setRotationPoint(-7.5F, 6F, -7.5F);
		this.top.mirror = true;
		this.post1 = new ModelRenderer(this);
		this.post1.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post1.setRotationPoint(-7F, 7F, -7F);
		this.post1.mirror = true;
		this.post = new ModelRenderer(this);
		this.post.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post.setRotationPoint(5F, 8F, -7F);
		this.post.mirror = true;
		this.post3 = new ModelRenderer(this);
		this.post3.addBox(0F, 0F, 0F, 2, 15, 2);
		this.post3.setRotationPoint(5F, 8F, 5F);
		this.post3.mirror = true;
		this.post2 = new ModelRenderer(this);
		this.post2.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post2.setRotationPoint(-7F, 7F, 5F);
		this.post2.mirror = true;
		this.base = new ModelRenderer(this);
		this.base.addBox(0F, 0F, 0F, 16, 2, 16);
		this.base.setRotationPoint(-8F, 22F, -8F);
		this.base.mirror = true;
		this.base2 = new ModelRenderer(this);
		this.base2.addBox(0F, 0F, 0F, 12, 2, 12);
		this.base2.setRotationPoint(-6F, 20F, -6F);
		this.base2.mirror = true;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.top.render(f5);
		this.post1.render(f5);
		this.post.render(f5);
		this.post3.render(f5);
		this.post2.render(f5);
		this.base.render(f5);
		this.base2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
