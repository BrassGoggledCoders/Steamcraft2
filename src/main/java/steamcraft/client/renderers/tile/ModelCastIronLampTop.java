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
 * File created @ [Apr 5, 2014, 8:41:38 PM]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ModelCastIronLampTop extends ModelBase
{
	public ModelRenderer bracket;
	public ModelRenderer bracketWide;
	public ModelRenderer crossbarLeft;
	public ModelRenderer crossbarRight;
	public ModelRenderer lowerLamp;
	public ModelRenderer top;
	public ModelRenderer topPeak;
	public ModelRenderer upperLamp;

	public ModelCastIronLampTop()
	{
		bracket = new ModelRenderer(this, 24, 3);
		bracket.addBox(-1F, 0F, -7.5F, 2, 3, 2);
		//Bracket.setPosition(-1F, 0F, -1F);

		bracketWide = new ModelRenderer(this, 12, 13);
		bracketWide.addBox(-2F, 0F, -8.5F, 4, 3, 4);
		//Bracket.setPosition(-1F, 0F, -1F);

		crossbarLeft = new ModelRenderer(this, 11, 16);
		crossbarLeft.addBox(0F, 1F, -7.5F, 7, 2, 2);
		//Bracket.setPosition(-1F, 0F, -1F);

		crossbarRight = new ModelRenderer(this, 11, 16);
		crossbarRight.addBox(-7F, 1F, -7.5F, 7, 2, 2);
		//Bracket.setPosition(-1F, 0F, -1F);

		lowerLamp = new ModelRenderer(this, 40, 13);
		lowerLamp.addBox(-3F, -4F, -9.5F, 6, 4, 6);
		//LowerLamp.setPosition(-3F, 3F, -3F);

		upperLamp = new ModelRenderer(this, 31, 0);
		upperLamp.addBox(-4F, -9F, -10.5F, 8, 5, 8);
		//UpperLamp.setPosition(-4F, 7F, -4F);

		top = new ModelRenderer(this, 0, 8);
		top.addBox(-5F, -11F, -11.5F, 10, 2, 10);
		//Top.setPosition(-5F, 12F, -5F);

		topPeak = new ModelRenderer(this, 0, 0);
		topPeak.addBox(-3F, -13F, -9.5F, 6, 2, 6);
		//TopPeak.setPosition(-3F, 14F, -3F);
	}

	public void renderSign()
	{
		bracket.render(0.1F);
		bracketWide.render(0.1F);
		crossbarLeft.render(0.1F);
		crossbarRight.render(0.1F);
		lowerLamp.render(0.1F);
		top.render(0.1F);
		topPeak.render(0.1F);
		upperLamp.render(0.1F);
	}
}
