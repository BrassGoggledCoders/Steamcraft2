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

/**
 * @author warlordjones
 * 
 */
public class ModelTeslaCoil extends ModelBase
{
	// fields
	ModelRenderer base;
	ModelRenderer pillar;
	ModelRenderer pillar2;
	ModelRenderer pin;
	ModelRenderer bar;
	ModelRenderer bar2;
	ModelRenderer ring;
	ModelRenderer ring3;
	ModelRenderer ring2;
	ModelRenderer ring4;
	ModelRenderer urib3;
	ModelRenderer urib2;
	ModelRenderer urib4;
	ModelRenderer urib;
	ModelRenderer rib3;
	ModelRenderer rib;
	ModelRenderer rib1;
	ModelRenderer siderib4;
	ModelRenderer siderib3;
	ModelRenderer siderib;
	ModelRenderer rib2;
	ModelRenderer siderib1;

	public ModelTeslaCoil()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.base = new ModelRenderer(this, 0, 0);
		this.base.addBox(0F, 0F, 0F, 13, 2, 13);
		this.base.setRotationPoint(-6.5F, 22F, -6.5F);
		this.base.setTextureSize(64, 64);
		this.base.mirror = true;
		this.setRotation(this.base, 0F, 0F, 0F);
		this.pillar = new ModelRenderer(this, 0, 16);
		this.pillar.addBox(0F, 0F, 0F, 8, 8, 8);
		this.pillar.setRotationPoint(-4F, 14F, -4F);
		this.pillar.setTextureSize(64, 64);
		this.pillar.mirror = true;
		this.setRotation(this.pillar, 0F, 0F, 0F);
		this.pillar2 = new ModelRenderer(this, 0, 32);
		this.pillar2.addBox(0F, 0F, 0F, 5, 6, 5);
		this.pillar2.setRotationPoint(-2.5F, 8F, -2.5F);
		this.pillar2.setTextureSize(64, 64);
		this.pillar2.mirror = true;
		this.setRotation(this.pillar2, 0F, 0F, 0F);
		this.pin = new ModelRenderer(this, 0, 43);
		this.pin.addBox(0F, 0F, 0F, 2, 2, 2);
		this.pin.setRotationPoint(-1F, 6F, -1F);
		this.pin.setTextureSize(64, 64);
		this.pin.mirror = true;
		this.setRotation(this.pin, 0F, 0F, 0F);
		this.bar = new ModelRenderer(this, 0, 58);
		this.bar.addBox(0F, 0F, 0F, 10, 1, 2);
		this.bar.setRotationPoint(-5F, 5F, -1F);
		this.bar.setTextureSize(64, 64);
		this.bar.mirror = true;
		this.setRotation(this.bar, 0F, 0F, 0F);
		this.bar2 = new ModelRenderer(this, 0, 47);
		this.bar2.addBox(0F, 0F, 0F, 2, 1, 10);
		this.bar2.setRotationPoint(-1F, 5F, -5F);
		this.bar2.setTextureSize(64, 64);
		this.bar2.mirror = true;
		this.setRotation(this.bar2, 0F, 0F, 0F);
		this.ring = new ModelRenderer(this, 24, 47);
		this.ring.addBox(0F, 0F, 0F, 3, 2, 10);
		this.ring.setRotationPoint(5F, 4F, -5F);
		this.ring.setTextureSize(64, 64);
		this.ring.mirror = true;
		this.setRotation(this.ring, 0F, 0F, 0F);
		this.ring3 = new ModelRenderer(this, 24, 59);
		this.ring3.addBox(0F, 0F, 0F, 16, 2, 3);
		this.ring3.setRotationPoint(-8F, 4F, 5F);
		this.ring3.setTextureSize(64, 64);
		this.ring3.mirror = true;
		this.setRotation(this.ring3, 0F, 0F, 0F);
		this.ring2 = new ModelRenderer(this, 24, 47);
		this.ring2.addBox(0F, 0F, 0F, 3, 2, 10);
		this.ring2.setRotationPoint(-8F, 4F, -5F);
		this.ring2.setTextureSize(64, 64);
		this.ring2.mirror = true;
		this.setRotation(this.ring2, 0F, 0F, 0F);
		this.ring4 = new ModelRenderer(this, 24, 59);
		this.ring4.addBox(0F, 0F, 0F, 16, 2, 3);
		this.ring4.setRotationPoint(-8F, 4F, -8F);
		this.ring4.setTextureSize(64, 64);
		this.ring4.mirror = true;
		this.setRotation(this.ring4, 0F, 0F, 0F);
		this.urib3 = new ModelRenderer(this, 34, 15);
		this.urib3.addBox(0F, 0F, 0F, 1, 1, 14);
		this.urib3.setRotationPoint(6F, 6F, -7F);
		this.urib3.setTextureSize(64, 64);
		this.urib3.mirror = true;
		this.setRotation(this.urib3, 0F, 0F, 0F);
		this.urib2 = new ModelRenderer(this, 20, 39);
		this.urib2.addBox(0F, 0F, 0F, 12, 1, 1);
		this.urib2.setRotationPoint(-6F, 6F, 6F);
		this.urib2.setTextureSize(64, 64);
		this.urib2.mirror = true;
		this.setRotation(this.urib2, 0F, 0F, 0F);
		this.urib4 = new ModelRenderer(this, 20, 41);
		this.urib4.addBox(0F, 0F, 0F, 12, 1, 1);
		this.urib4.setRotationPoint(-6F, 6F, -7F);
		this.urib4.setTextureSize(64, 64);
		this.urib4.mirror = true;
		this.setRotation(this.urib4, 0F, 0F, 0F);
		this.urib = new ModelRenderer(this, 34, 15);
		this.urib.addBox(0F, 0F, 0F, 1, 1, 14);
		this.urib.setRotationPoint(-7F, 6F, -7F);
		this.urib.setTextureSize(64, 64);
		this.urib.mirror = true;
		this.setRotation(this.urib, 0F, 0F, 0F);
		this.rib3 = new ModelRenderer(this, 34, 15);
		this.rib3.addBox(0F, 0F, 0F, 1, 1, 14);
		this.rib3.setRotationPoint(6F, 3F, -7F);
		this.rib3.setTextureSize(64, 64);
		this.rib3.mirror = true;
		this.setRotation(this.rib3, 0F, 0F, 0F);
		this.rib = new ModelRenderer(this, 8, 45);
		this.rib.addBox(0F, 0F, 0F, 12, 1, 1);
		this.rib.setRotationPoint(-6F, 3F, -7F);
		this.rib.setTextureSize(64, 64);
		this.rib.mirror = true;
		this.setRotation(this.rib, 0F, 0F, 0F);
		this.rib1 = new ModelRenderer(this, 34, 15);
		this.rib1.addBox(0F, 0F, 0F, 1, 1, 14);
		this.rib1.setRotationPoint(-7F, 3F, -7F);
		this.rib1.setTextureSize(64, 64);
		this.rib1.mirror = true;
		this.setRotation(this.rib1, 0F, 0F, 0F);
		this.siderib4 = new ModelRenderer(this, 27, 43);
		this.siderib4.addBox(0F, 0F, 0F, 18, 1, 1);
		this.siderib4.setRotationPoint(-9F, 4.5F, -9F);
		this.siderib4.setTextureSize(64, 64);
		this.siderib4.mirror = true;
		this.setRotation(this.siderib4, 0F, 0F, 0F);
		this.siderib3 = new ModelRenderer(this, 0, 0);
		this.siderib3.addBox(0F, 0F, 0F, 1, 1, 16);
		this.siderib3.setRotationPoint(-9F, 4.5F, -8F);
		this.siderib3.setTextureSize(64, 64);
		this.siderib3.mirror = true;
		this.setRotation(this.siderib3, 0F, 0F, 0F);
		this.siderib = new ModelRenderer(this, 0, 0);
		this.siderib.addBox(0F, 0F, 0F, 1, 1, 16);
		this.siderib.setRotationPoint(8F, 4.5F, -8F);
		this.siderib.setTextureSize(64, 64);
		this.siderib.mirror = true;
		this.setRotation(this.siderib, 0F, 0F, 0F);
		this.rib2 = new ModelRenderer(this, 8, 45);
		this.rib2.addBox(0F, 0F, 0F, 12, 1, 1);
		this.rib2.setRotationPoint(-6F, 3F, 6F);
		this.rib2.setTextureSize(64, 64);
		this.rib2.mirror = true;
		this.setRotation(this.rib2, 0F, 0F, 0F);
		this.siderib1 = new ModelRenderer(this, 27, 43);
		this.siderib1.addBox(0F, 0F, 0F, 18, 1, 1);
		this.siderib1.setRotationPoint(-9F, 4.5F, 8F);
		this.siderib1.setTextureSize(64, 64);
		this.siderib1.mirror = true;
		this.setRotation(this.siderib1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.base.render(f5);
		this.pillar.render(f5);
		this.pillar2.render(f5);
		this.pin.render(f5);
		this.bar.render(f5);
		this.bar2.render(f5);
		this.ring.render(f5);
		this.ring3.render(f5);
		this.ring2.render(f5);
		this.ring4.render(f5);
		this.urib3.render(f5);
		this.urib2.render(f5);
		this.urib4.render(f5);
		this.urib.render(f5);
		this.rib3.render(f5);
		this.rib.render(f5);
		this.rib1.render(f5);
		// siderib4.render(f5);
		// siderib3.render(f5);
		// siderib.render(f5);
		this.rib2.render(f5);
		// siderib1.render(f5);
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
