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
	//variables init:
	public ModelRenderer side3;
	public ModelRenderer side4;
	public ModelRenderer bottom;
	public ModelRenderer middle;
	public ModelRenderer side;
	public ModelRenderer side2;
	public ModelRenderer top;


  public ModelCopperPipe()
  {
    textureWidth = 64;
    textureHeight = 64;

    //constructor:
  	side3 = new ModelRenderer(this, 0, 0);
  	side3.addBox(0F, 0F, 0F, 6, 6, 6);
  	//side3.setPosition(0F, 0F, 6F);

  	side4 = new ModelRenderer(this, 0, 0);
  	side4.addBox(0F, 0F, 0F, 6, 6, 6);
  	//side4.setPosition(0F, 0F, -6F);

  	bottom = new ModelRenderer(this, 0, 0);
  	bottom.addBox(0F, 0F, 0F, 6, 6, 6);
  	//bottom.setPosition(0F, -6F, 0F);

  	middle = new ModelRenderer(this, 0, 0);
  	middle.addBox(0F, 0F, 0F, 6, 6, 6);

  	side = new ModelRenderer(this, 0, 0);
  	side.addBox(0F, 0F, 0F, 6, 6, 6);
  	//side.setPosition(6F, 0F, 0F);

  	side2 = new ModelRenderer(this, 0, 0);
  	side2.addBox(0F, 0F, 0F, 6, 6, 6);
  	//side2.setPosition(-6F, 0F, 0F);

  	top = new ModelRenderer(this, 0, 0);
  	top.addBox(0F, 0F, 0F, 6, 6, 6);
  	//top.setPosition(0F, 6F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	//render:
	side3.render(f5);
	side4.render(f5);
	bottom.render(f5);
	middle.render(f5);
	side.render(f5);
	side2.render(f5);
	top.render(f5);
  }

  public void renderModel(float f, int type)
  {
	  	/*if(type == 0)
      	{
		  side3.render(f);
      	}

	  	if(type == 1)
	  	{
		  side4.render(f);
		}

		if(type == 2)
		{
		 bottom.render(f);
		}

		if(type == 3)
		{
		 top.render(f);
		}

		if(type == 4)
		{
		 side2.render(f);
		}

		if(type == 5)
		{
		 side.render(f);
		}
		top.render(f);*/
		side3.render(f);
		side4.render(f);
		bottom.render(f);
		middle.render(f);
		side.render(f);
		side2.render(f);
		top.render(f);
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
