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
 * File created @ [Apr 5, 2014, 8:45:32 PM]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelCastIronLampSide.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ModelCastIronLampSide extends ModelBase
{
	
	/** The bracket. */
	public ModelRenderer bracket;
	
	/** The bracket2. */
	public ModelRenderer bracket2;
	
	/** The crossbar left. */
	public ModelRenderer crossbarLeft;
	
	/** The crossbar right. */
	public ModelRenderer crossbarRight;
	
	/** The lower lamp. */
	public ModelRenderer lowerLamp;
	
	/** The support. */
	public ModelRenderer support;
	
	/** The top. */
	public ModelRenderer top;
	
	/** The top peak. */
	public ModelRenderer topPeak;
	
	/** The upper lamp. */
	public ModelRenderer upperLamp;

	/**
	 * Instantiates a new model cast iron lamp side.
	 */
	public ModelCastIronLampSide()
	{
		bracket2 = new ModelRenderer(this, 13, 16);
		bracket2.addBox(-6.5F, 1F, -7.5F, 6, 2, 2);
		// Bracket2.setPosition(8F, -16F, -1F);

		support = new ModelRenderer(this, 15, 8);
		support.addBox(-7.5F, -3F, -8.5F, 1, 6, 4);
		// Support.setPosition(8F, -16F, -2F);

		crossbarLeft = new ModelRenderer(this, 11, 11);
		crossbarLeft.addBox(-7F, 1F, -7F, 2, 2, 7);
		// Bracket.setPosition(-1F, 0F, -1F);

		crossbarRight = new ModelRenderer(this, 11, 11);
		crossbarRight.addBox(-7F, 1F, -13.5F, 2, 2, 7);
		// Bracket.setPosition(-1F, 0F, -1F);

		bracket = new ModelRenderer(this, 24, 3);
		bracket.addBox(-1F, 0F, -7.5F, 2, 3, 2);
		// Bracket.setPosition(-1F, 0F, -1F);

		lowerLamp = new ModelRenderer(this, 40, 13);
		lowerLamp.addBox(-3F, -4F, -9.5F, 6, 4, 6);
		// LowerLamp.setPosition(-3F, 3F, -3F);

		upperLamp = new ModelRenderer(this, 31, 0);
		upperLamp.addBox(-4F, -9F, -10.5F, 8, 5, 8);
		// UpperLamp.setPosition(-4F, 7F, -4F);

		top = new ModelRenderer(this, 0, 8);
		top.addBox(-5F, -11F, -11.5F, 10, 2, 10);
		// Top.setPosition(-5F, 12F, -5F);

		topPeak = new ModelRenderer(this, 0, 0);
		topPeak.addBox(-3F, -13F, -9.5F, 6, 2, 6);
		// TopPeak.setPosition(-3F, 14F, -3F);
	}

	/**
	 * Render sign.
	 */
	public void renderSign()
	{
		final float scale = 0.1F;
		bracket.render(scale);
		bracket2.render(scale);
		crossbarLeft.render(scale);
		crossbarRight.render(scale);
		lowerLamp.render(scale);
		support.render(scale);
		top.render(scale);
		topPeak.render(scale);
		upperLamp.render(scale);
	}
}
