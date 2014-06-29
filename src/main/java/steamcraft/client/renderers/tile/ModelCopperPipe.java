/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/15/14, 13:48]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelCrystal.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ModelCopperPipe extends ModelBase
{
	  //fields
    ModelRenderer tube_m;
    ModelRenderer tube_f;
    ModelRenderer tube_b;
    ModelRenderer tube_u;
    ModelRenderer tube_t;
    ModelRenderer tube_r;
    ModelRenderer tube_l;

  public ModelCopperPipe()
  {
    textureWidth = 64;
    textureHeight = 64;

      tube_m = new ModelRenderer(this, 0, 0);
      tube_m.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_m.setRotationPoint(-3F, 13F, -3F);
      tube_m.setTextureSize(64, 64);
      tube_m.mirror = true;
      setRotation(tube_m, 0F, 0F, 0F);
      tube_f = new ModelRenderer(this, 40, 0);
      tube_f.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_f.setRotationPoint(-3F, 13F, -8F);
      tube_f.setTextureSize(64, 64);
      tube_f.mirror = true;
      setRotation(tube_f, 0F, 0F, 0F);
      tube_b = new ModelRenderer(this, 40, 0);
      tube_b.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_b.setRotationPoint(-3F, 13F, 2F);
      tube_b.setTextureSize(64, 64);
      tube_b.mirror = true;
      setRotation(tube_b, 0F, 0F, 0F);
      tube_u = new ModelRenderer(this, 0, 26);
      tube_u.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_u.setRotationPoint(-3F, 18F, -3F);
      tube_u.setTextureSize(64, 64);
      tube_u.mirror = true;
      setRotation(tube_u, 0F, 0F, 0F);
      tube_t = new ModelRenderer(this, 0, 26);
      tube_t.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_t.setRotationPoint(-3F, 8F, -3F);
      tube_t.setTextureSize(64, 64);
      tube_t.mirror = true;
      setRotation(tube_t, 0F, 0F, 0F);
      tube_r = new ModelRenderer(this, 0, 52);
      tube_r.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_r.setRotationPoint(2F, 13F, -3F);
      tube_r.setTextureSize(64, 64);
      tube_r.mirror = true;
      setRotation(tube_r, 0F, 0F, 0F);
      tube_l = new ModelRenderer(this, 0, 52);
      tube_l.addBox(0F, 0F, 0F, 6, 6, 6);
      tube_l.setRotationPoint(-8F, 13F, -3F);
      tube_l.setTextureSize(64, 64);
      tube_l.mirror = true;
      setRotation(tube_l, 0F, 0F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    tube_m.render(f5);
    tube_f.render(f5);
    tube_b.render(f5);
    tube_u.render(f5);
    tube_t.render(f5);
    tube_r.render(f5);
    tube_l.render(f5);
  }

  public void renderModel(float f, int type)
  {
	  	if(type == 0)
      	{
		  tube_m.render(f);
      	}

	  	if(type == 1)
	  	{
		  tube_t.render(f);
		}

		if(type == 2)
		{
		 tube_u.render(f);
		}

		if(type == 3)
		{
		 tube_l.render(f);
		}

		if(type == 4)
		{
		 tube_r.render(f);
		}

		if(type == 5)
		{
		 tube_b.render(f);
		}

		if(type == 6)
		{
		 tube_f.render(f);
		}
		tube_m.render(f);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
