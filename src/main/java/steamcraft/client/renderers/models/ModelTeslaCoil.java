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
  //fields
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
    textureWidth = 64;
    textureHeight = 64;

      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 13, 2, 13);
      base.setRotationPoint(-6.5F, 22F, -6.5F);
      base.setTextureSize(64, 64);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      pillar = new ModelRenderer(this, 0, 16);
      pillar.addBox(0F, 0F, 0F, 8, 8, 8);
      pillar.setRotationPoint(-4F, 14F, -4F);
      pillar.setTextureSize(64, 64);
      pillar.mirror = true;
      setRotation(pillar, 0F, 0F, 0F);
      pillar2 = new ModelRenderer(this, 0, 32);
      pillar2.addBox(0F, 0F, 0F, 5, 6, 5);
      pillar2.setRotationPoint(-2.5F, 8F, -2.5F);
      pillar2.setTextureSize(64, 64);
      pillar2.mirror = true;
      setRotation(pillar2, 0F, 0F, 0F);
      pin = new ModelRenderer(this, 0, 43);
      pin.addBox(0F, 0F, 0F, 2, 2, 2);
      pin.setRotationPoint(-1F, 6F, -1F);
      pin.setTextureSize(64, 64);
      pin.mirror = true;
      setRotation(pin, 0F, 0F, 0F);
      bar = new ModelRenderer(this, 0, 58);
      bar.addBox(0F, 0F, 0F, 10, 1, 2);
      bar.setRotationPoint(-5F, 5F, -1F);
      bar.setTextureSize(64, 64);
      bar.mirror = true;
      setRotation(bar, 0F, 0F, 0F);
      bar2 = new ModelRenderer(this, 0, 47);
      bar2.addBox(0F, 0F, 0F, 2, 1, 10);
      bar2.setRotationPoint(-1F, 5F, -5F);
      bar2.setTextureSize(64, 64);
      bar2.mirror = true;
      setRotation(bar2, 0F, 0F, 0F);
      ring = new ModelRenderer(this, 24, 47);
      ring.addBox(0F, 0F, 0F, 3, 2, 10);
      ring.setRotationPoint(5F, 4F, -5F);
      ring.setTextureSize(64, 64);
      ring.mirror = true;
      setRotation(ring, 0F, 0F, 0F);
      ring3 = new ModelRenderer(this, 24, 59);
      ring3.addBox(0F, 0F, 0F, 16, 2, 3);
      ring3.setRotationPoint(-8F, 4F, 5F);
      ring3.setTextureSize(64, 64);
      ring3.mirror = true;
      setRotation(ring3, 0F, 0F, 0F);
      ring2 = new ModelRenderer(this, 24, 47);
      ring2.addBox(0F, 0F, 0F, 3, 2, 10);
      ring2.setRotationPoint(-8F, 4F, -5F);
      ring2.setTextureSize(64, 64);
      ring2.mirror = true;
      setRotation(ring2, 0F, 0F, 0F);
      ring4 = new ModelRenderer(this, 24, 59);
      ring4.addBox(0F, 0F, 0F, 16, 2, 3);
      ring4.setRotationPoint(-8F, 4F, -8F);
      ring4.setTextureSize(64, 64);
      ring4.mirror = true;
      setRotation(ring4, 0F, 0F, 0F);
      urib3 = new ModelRenderer(this, 34, 15);
      urib3.addBox(0F, 0F, 0F, 1, 1, 14);
      urib3.setRotationPoint(6F, 6F, -7F);
      urib3.setTextureSize(64, 64);
      urib3.mirror = true;
      setRotation(urib3, 0F, 0F, 0F);
      urib2 = new ModelRenderer(this, 20, 39);
      urib2.addBox(0F, 0F, 0F, 12, 1, 1);
      urib2.setRotationPoint(-6F, 6F, 6F);
      urib2.setTextureSize(64, 64);
      urib2.mirror = true;
      setRotation(urib2, 0F, 0F, 0F);
      urib4 = new ModelRenderer(this, 20, 41);
      urib4.addBox(0F, 0F, 0F, 12, 1, 1);
      urib4.setRotationPoint(-6F, 6F, -7F);
      urib4.setTextureSize(64, 64);
      urib4.mirror = true;
      setRotation(urib4, 0F, 0F, 0F);
      urib = new ModelRenderer(this, 34, 15);
      urib.addBox(0F, 0F, 0F, 1, 1, 14);
      urib.setRotationPoint(-7F, 6F, -7F);
      urib.setTextureSize(64, 64);
      urib.mirror = true;
      setRotation(urib, 0F, 0F, 0F);
      rib3 = new ModelRenderer(this, 34, 15);
      rib3.addBox(0F, 0F, 0F, 1, 1, 14);
      rib3.setRotationPoint(6F, 3F, -7F);
      rib3.setTextureSize(64, 64);
      rib3.mirror = true;
      setRotation(rib3, 0F, 0F, 0F);
      rib = new ModelRenderer(this, 8, 45);
      rib.addBox(0F, 0F, 0F, 12, 1, 1);
      rib.setRotationPoint(-6F, 3F, -7F);
      rib.setTextureSize(64, 64);
      rib.mirror = true;
      setRotation(rib, 0F, 0F, 0F);
      rib1 = new ModelRenderer(this, 34, 15);
      rib1.addBox(0F, 0F, 0F, 1, 1, 14);
      rib1.setRotationPoint(-7F, 3F, -7F);
      rib1.setTextureSize(64, 64);
      rib1.mirror = true;
      setRotation(rib1, 0F, 0F, 0F);
      siderib4 = new ModelRenderer(this, 27, 43);
      siderib4.addBox(0F, 0F, 0F, 18, 1, 1);
      siderib4.setRotationPoint(-9F, 4.5F, -9F);
      siderib4.setTextureSize(64, 64);
      siderib4.mirror = true;
      setRotation(siderib4, 0F, 0F, 0F);
      siderib3 = new ModelRenderer(this, 0, 0);
      siderib3.addBox(0F, 0F, 0F, 1, 1, 16);
      siderib3.setRotationPoint(-9F, 4.5F, -8F);
      siderib3.setTextureSize(64, 64);
      siderib3.mirror = true;
      setRotation(siderib3, 0F, 0F, 0F);
      siderib = new ModelRenderer(this, 0, 0);
      siderib.addBox(0F, 0F, 0F, 1, 1, 16);
      siderib.setRotationPoint(8F, 4.5F, -8F);
      siderib.setTextureSize(64, 64);
      siderib.mirror = true;
      setRotation(siderib, 0F, 0F, 0F);
      rib2 = new ModelRenderer(this, 8, 45);
      rib2.addBox(0F, 0F, 0F, 12, 1, 1);
      rib2.setRotationPoint(-6F, 3F, 6F);
      rib2.setTextureSize(64, 64);
      rib2.mirror = true;
      setRotation(rib2, 0F, 0F, 0F);
      siderib1 = new ModelRenderer(this, 27, 43);
      siderib1.addBox(0F, 0F, 0F, 18, 1, 1);
      siderib1.setRotationPoint(-9F, 4.5F, 8F);
      siderib1.setTextureSize(64, 64);
      siderib1.mirror = true;
      setRotation(siderib1, 0F, 0F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    base.render(f5);
    pillar.render(f5);
    pillar2.render(f5);
    pin.render(f5);
    bar.render(f5);
    bar2.render(f5);
    ring.render(f5);
    ring3.render(f5);
    ring2.render(f5);
    ring4.render(f5);
    urib3.render(f5);
    urib2.render(f5);
    urib4.render(f5);
    urib.render(f5);
    rib3.render(f5);
    rib.render(f5);
    rib1.render(f5);
    //siderib4.render(f5);
    //siderib3.render(f5);
    //siderib.render(f5);
    rib2.render(f5);
    //siderib1.render(f5);
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
