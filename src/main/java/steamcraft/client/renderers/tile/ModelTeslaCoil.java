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


/**
 * @author Surseance
 *
 */
@Deprecated
public class ModelTeslaCoil extends ModelBase
{
	public ModelRenderer center;
	public ModelRenderer fin1;
	public ModelRenderer fin2;
	public ModelRenderer fin3;
	public ModelRenderer top;

	public ModelTeslaCoil()
	{
		this.center = new ModelRenderer(this, 0, 0);
		this.center.addBox(-1.5F, 7.5F, -1.5F, 3, 15, 3, 0);

		this.top = new ModelRenderer(this, 0, 0);
		this.top.addBox(-2.5F, 4.5F, -2.5F, 5, 5, 5, 0);

		this.fin1 = new ModelRenderer(this, 0, 0);
		this.fin1.addBox(-3.5F, 11.0F, -3.5F, 7, 2, 7, 0);

		this.fin2 = new ModelRenderer(this, 0, 0);
		this.fin2.addBox(-4.5F, 15.0F, -4.5F, 9, 2, 9, 0);

		this.fin3 = new ModelRenderer(this, 0, 0);
		this.fin3.addBox(-5.5F, 19.0F, -5.5F, 11, 2, 11, 0);

	}

	public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.center.render(f5);
		this.top.render(f5);
		this.fin1.render(f5);
		this.fin2.render(f5);
		this.fin3.render(f5);
	}

	public void renderModel(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.center.render(f5);
		this.top.render(f5);
		this.fin1.render(f5);
		this.fin2.render(f5);
		this.fin3.render(f5);
	}

	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
	}
}
