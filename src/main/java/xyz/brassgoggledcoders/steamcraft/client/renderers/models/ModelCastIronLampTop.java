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
package xyz.brassgoggledcoders.steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author Surseance
 *
 */
public class ModelCastIronLampTop extends ModelBase
{
	public ModelRenderer bracket;
	public ModelRenderer bracketWide;

	public ModelRenderer crossbarLeft;
	public ModelRenderer crossbarRight;

	public ModelRenderer lowerLamp;
	public ModelRenderer upperLamp;

	public ModelRenderer top;
	public ModelRenderer topPeak;

	public ModelCastIronLampTop()
	{
		this.bracket = new ModelRenderer(this, 24, 3);
		this.bracket.addBox(-1F, 0F, -7.5F, 2, 3, 2);
		// Bracket.setPosition(-1F, 0F, -1F);

		this.bracketWide = new ModelRenderer(this, 12, 13);
		this.bracketWide.addBox(-2F, 0F, -8.5F, 4, 3, 4);
		// Bracket.setPosition(-1F, 0F, -1F);

		this.crossbarLeft = new ModelRenderer(this, 11, 16);
		this.crossbarLeft.addBox(0F, 1F, -7.5F, 7, 2, 2);
		// Bracket.setPosition(-1F, 0F, -1F);

		this.crossbarRight = new ModelRenderer(this, 11, 16);
		this.crossbarRight.addBox(-7F, 1F, -7.5F, 7, 2, 2);
		// Bracket.setPosition(-1F, 0F, -1F);

		this.lowerLamp = new ModelRenderer(this, 40, 13);
		this.lowerLamp.addBox(-3F, -4F, -9.5F, 6, 4, 6);
		// LowerLamp.setPosition(-3F, 3F, -3F);

		this.upperLamp = new ModelRenderer(this, 31, 0);
		this.upperLamp.addBox(-4F, -9F, -10.5F, 8, 5, 8);
		// UpperLamp.setPosition(-4F, 7F, -4F);

		this.top = new ModelRenderer(this, 0, 8);
		this.top.addBox(-5F, -11F, -11.5F, 10, 2, 10);
		// Top.setPosition(-5F, 12F, -5F);

		this.topPeak = new ModelRenderer(this);
		this.topPeak.addBox(-3F, -13F, -9.5F, 6, 2, 6);
		// TopPeak.setPosition(-3F, 14F, -3F);
	}

	public void renderLamp()
	{
		this.bracket.render(0.1F);
		this.bracketWide.render(0.1F);
		this.crossbarLeft.render(0.1F);
		this.crossbarRight.render(0.1F);
		this.lowerLamp.render(0.1F);
		this.top.render(0.1F);
		this.topPeak.render(0.1F);
		this.upperLamp.render(0.1F);
	}
}
