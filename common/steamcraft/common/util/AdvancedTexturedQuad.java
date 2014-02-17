/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 17, 2014, 12:11:02 PM]
 */
package common.steamcraft.common.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

/**
 * @author MrArcane111
 *
 */
public class AdvancedTexturedQuad {
	public AdvancedPositionTextureVertex[] vertexPositions;
	public int verticesNum;
	private boolean invertNormal;

	public AdvancedTexturedQuad(AdvancedPositionTextureVertex[] textVert) {
		this.verticesNum = 0;
		this.invertNormal = false;
		this.vertexPositions = textVert;
		this.verticesNum = textVert.length;
	}

	public AdvancedTexturedQuad(AdvancedPositionTextureVertex[] textVert, int par2, int par3, int par4, int par5, double par6, double par7) {
		this(textVert);
		double var8 = 0.0D / par6;
		double var9 = 0.0D / par7;
		textVert[0] = textVert[0].setTexturePosition(par4 / par6 - var8, par3 / par7 + var9);
		textVert[1] = textVert[1].setTexturePosition(par2 / par6 + var8, par3 / par7 + var9);
		textVert[2] = textVert[2].setTexturePosition(par2 / par6 + var8, par5 / par7 - var9);
		textVert[3] = textVert[3].setTexturePosition(par4 / par6 - var8, par5 / par7 - var9);
	}

	public void flipFace() {
		AdvancedPositionTextureVertex[] textVert = new AdvancedPositionTextureVertex[this.vertexPositions.length];

		for (int pos = 0; pos < this.vertexPositions.length; pos++) {
			textVert[pos] = this.vertexPositions[(this.vertexPositions.length - pos - 1)];
		}

		this.vertexPositions = textVert;
	}

	public void draw(Tessellator tessellator, float f) {
		Vec3 var3 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[0].vector3D);
		Vec3 var4 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[2].vector3D);
		Vec3 var5 = var4.crossProduct(var3).normalize();
		tessellator.startDrawingQuads();

		if (this.invertNormal) {
			tessellator.setNormal(-(float)var5.xCoord, -(float)var5.yCoord, -(float)var5.zCoord);
		} else {
			tessellator.setNormal((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
		}
		
		for (int var6 = 0; var6 < 4; var6++) {
			AdvancedPositionTextureVertex var7 = this.vertexPositions[var6];
			tessellator.addVertexWithUV(var7.vector3D.xCoord * f, var7.vector3D.yCoord * f, var7.vector3D.zCoord * f, var7.texturePositionX, var7.texturePositionY);
		}

		tessellator.draw();
	}
}
