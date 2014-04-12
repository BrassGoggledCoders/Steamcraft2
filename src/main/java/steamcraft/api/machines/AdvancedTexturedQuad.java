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
 * File created @ [Mar 14, 2014, 10:23:42 AM]
 */
package steamcraft.api.machines;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class AdvancedTexturedQuad
{
	public AdvancedPositionTextureVertex[] vertPos;
	public int vertices;
	private boolean invertNormal;

	public AdvancedTexturedQuad(AdvancedPositionTextureVertex[] texVert) 
	{
		this.vertices = 0;
		this.invertNormal = false;
		this.vertPos = texVert;
		this.vertices = texVert.length;
	}

	public AdvancedTexturedQuad(AdvancedPositionTextureVertex[] texVert, int par2, int par3, int par4, int par5, double par6, double par7) 
	{
		this(texVert);
		double var8 = 0.0D / par6;
		double var9 = 0.0D / par7;
		texVert[0] = texVert[0].setTexturePosition(par4 / par6 - var8, par3 / par7 + var9);
		texVert[1] = texVert[1].setTexturePosition(par2 / par6 + var8, par3 / par7 + var9);
		texVert[2] = texVert[2].setTexturePosition(par2 / par6 + var8, par5 / par7 - var9);
		texVert[3] = texVert[3].setTexturePosition(par4 / par6 - var8, par5 / par7 - var9);
	}

	public void flipFace() 
	{
		AdvancedPositionTextureVertex[] texVert = new AdvancedPositionTextureVertex[this.vertPos.length];

		for (int pos = 0; pos < this.vertPos.length; pos++) 
		{
			texVert[pos] = this.vertPos[(this.vertPos.length - pos - 1)];
		}

		this.vertPos = texVert;
	}

	public void draw(Tessellator tessellator, float f) 
	{
		Vec3 var3 = this.vertPos[1].vector3D.subtract(this.vertPos[0].vector3D);
		Vec3 var4 = this.vertPos[1].vector3D.subtract(this.vertPos[2].vector3D);
		Vec3 var5 = var4.crossProduct(var3).normalize();
		tessellator.startDrawingQuads();

		if (this.invertNormal) 
		{
			tessellator.setNormal(-(float)var5.xCoord, -(float)var5.yCoord, -(float)var5.zCoord);
		} 
		else 
		{
			tessellator.setNormal((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
		}

		for (int var6 = 0; var6 < 4; var6++) 
		{
			AdvancedPositionTextureVertex var7 = this.vertPos[var6];
			tessellator.addVertexWithUV(var7.vector3D.xCoord * f, var7.vector3D.yCoord * f, var7.vector3D.zCoord * f, var7.texPosX, var7.texPosY);
		}

		tessellator.draw();
	}
}
