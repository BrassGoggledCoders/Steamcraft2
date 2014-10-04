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
	// fields
	ModelRenderer top;
	ModelRenderer post1;
	ModelRenderer post;
	ModelRenderer post3;
	ModelRenderer post2;
	ModelRenderer base;
	ModelRenderer base2;

	public ModelCharger()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.top = new ModelRenderer(this, 0, 0);
		this.top.addBox(0F, 0F, 0F, 15, 2, 15);
		this.top.setRotationPoint(-7.5F, 6F, -7.5F);
		this.top.setTextureSize(64, 32);
		this.top.mirror = true;
		this.setRotation(this.top, 0F, 0F, 0F);
		this.post1 = new ModelRenderer(this, 0, 0);
		this.post1.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post1.setRotationPoint(-7F, 7F, -7F);
		this.post1.setTextureSize(64, 32);
		this.post1.mirror = true;
		this.setRotation(this.post1, 0F, 0F, 0F);
		this.post = new ModelRenderer(this, 0, 0);
		this.post.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post.setRotationPoint(5F, 8F, -7F);
		this.post.setTextureSize(64, 32);
		this.post.mirror = true;
		this.setRotation(this.post, 0F, 0F, 0F);
		this.post3 = new ModelRenderer(this, 0, 0);
		this.post3.addBox(0F, 0F, 0F, 2, 15, 2);
		this.post3.setRotationPoint(5F, 8F, 5F);
		this.post3.setTextureSize(64, 32);
		this.post3.mirror = true;
		this.setRotation(this.post3, 0F, 0F, 0F);
		this.post2 = new ModelRenderer(this, 0, 0);
		this.post2.addBox(0F, 0F, 0F, 2, 16, 2);
		this.post2.setRotationPoint(-7F, 7F, 5F);
		this.post2.setTextureSize(64, 32);
		this.post2.mirror = true;
		this.setRotation(this.post2, 0F, 0F, 0F);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.addBox(0F, 0F, 0F, 16, 2, 16);
		this.base.setRotationPoint(-8F, 22F, -8F);
		this.base.setTextureSize(64, 32);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.base2 = new ModelRenderer(this, 0, 0);
		this.base2.addBox(0F, 0F, 0F, 12, 2, 12);
		this.base2.setRotationPoint(-6F, 20F, -6F);
		this.base2.setTextureSize(64, 32);
		this.base2.mirror = true;
		this.setRotation(this.base2, 0F, 0F, 0F);
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
