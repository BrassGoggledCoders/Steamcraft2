package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCharger extends ModelBase
{
  //fields
    ModelRenderer top;
    ModelRenderer post1;
    ModelRenderer post;
    ModelRenderer post3;
    ModelRenderer post2;
    ModelRenderer base;
    ModelRenderer base2;

  public ModelCharger()
  {
    textureWidth = 64;
    textureHeight = 32;

      top = new ModelRenderer(this, 0, 0);
      top.addBox(0F, 0F, 0F, 15, 2, 15);
      top.setRotationPoint(-7.5F, 6F, -7.5F);
      top.setTextureSize(64, 32);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      post1 = new ModelRenderer(this, 0, 0);
      post1.addBox(0F, 0F, 0F, 2, 16, 2);
      post1.setRotationPoint(-7F, 7F, -7F);
      post1.setTextureSize(64, 32);
      post1.mirror = true;
      setRotation(post1, 0F, 0F, 0F);
      post = new ModelRenderer(this, 0, 0);
      post.addBox(0F, 0F, 0F, 2, 16, 2);
      post.setRotationPoint(5F, 8F, -7F);
      post.setTextureSize(64, 32);
      post.mirror = true;
      setRotation(post, 0F, 0F, 0F);
      post3 = new ModelRenderer(this, 0, 0);
      post3.addBox(0F, 0F, 0F, 2, 15, 2);
      post3.setRotationPoint(5F, 8F, 5F);
      post3.setTextureSize(64, 32);
      post3.mirror = true;
      setRotation(post3, 0F, 0F, 0F);
      post2 = new ModelRenderer(this, 0, 0);
      post2.addBox(0F, 0F, 0F, 2, 16, 2);
      post2.setRotationPoint(-7F, 7F, 5F);
      post2.setTextureSize(64, 32);
      post2.mirror = true;
      setRotation(post2, 0F, 0F, 0F);
      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 16, 2, 16);
      base.setRotationPoint(-8F, 22F, -8F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      base2 = new ModelRenderer(this, 0, 0);
      base2.addBox(0F, 0F, 0F, 12, 2, 12);
      base2.setRotationPoint(-6F, 20F, -6F);
      base2.setTextureSize(64, 32);
      base2.mirror = true;
      setRotation(base2, 0F, 0F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    top.render(f5);
    post1.render(f5);
    post.render(f5);
    post3.render(f5);
    post2.render(f5);
    base.render(f5);
    base2.render(f5);
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
