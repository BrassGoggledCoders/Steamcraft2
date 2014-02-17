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
 * File created @ [Feb 17, 2014, 12:09:57 PM]
 */
package common.steamcraft.common.util;

import net.minecraft.client.renderer.Tessellator;

/**
 * @author MrArcane111
 *
 */
public class ModelCustomBox {
	private AdvancedPositionTextureVertex[] vertexPositions;
	private AdvancedTexturedQuad[] quadList;
	public final float posX1;
	public final float posY1;
	public final float posZ1;
	public final float posX2;
	public final float posY2;
	public final float posZ2;
	public int textureWidth;
	public int textureHeight;
	public boolean[] renderQuad = { true, true, true, true, true, true };
	public float transparency = 1.0F;

	public ModelCustomBox(int texCoordX, int texCoordY, int textureWidth, int textureHeight, float x1, float y1, float z1, int width, int height, int depth, float size) {
		this.posX1 = x1;
		this.posY1 = y1;
		this.posZ1 = z1;
		this.posX2 = (x1 + width);
		this.posY2 = (y1 + height);
		this.posZ2 = (z1 + depth);
		this.vertexPositions = new AdvancedPositionTextureVertex[8];
		this.quadList = new AdvancedTexturedQuad[6];
		float x2 = x1 + width;
		float y2 = y1 + height;
		float z2 = z1 + depth;
		x1 -= size;
		y1 -= size;
		z1 -= size;
		x2 += size;
		y2 += size;
		z2 += size;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		AdvancedPositionTextureVertex botLeftFront = new AdvancedPositionTextureVertex(x1, y1, z1, 0.0D, 0.0D);
		AdvancedPositionTextureVertex botRightFront = new AdvancedPositionTextureVertex(x2, y1, z1, 0.0D, 8.0D);
		AdvancedPositionTextureVertex topRightFront = new AdvancedPositionTextureVertex(x2, y2, z1, 8.0D, 8.0D);
		AdvancedPositionTextureVertex topLeftFront = new AdvancedPositionTextureVertex(x1, y2, z1, 8.0D, 0.0D);
		AdvancedPositionTextureVertex botLeftBack = new AdvancedPositionTextureVertex(x1, y1, z2, 0.0D, 0.0D);
		AdvancedPositionTextureVertex botRightBack = new AdvancedPositionTextureVertex(x2, y1, z2, 0.0D, 8.0D);
		AdvancedPositionTextureVertex topRightBack = new AdvancedPositionTextureVertex(x2, y2, z2, 8.0D, 8.0D);
		AdvancedPositionTextureVertex topLeftBack = new AdvancedPositionTextureVertex(x1, y2, z2, 8.0D, 0.0D);
		this.vertexPositions[0] = botLeftFront;
		this.vertexPositions[1] = botRightFront;
		this.vertexPositions[2] = topRightFront;
		this.vertexPositions[3] = topLeftFront;
		this.vertexPositions[4] = botLeftBack;
		this.vertexPositions[5] = botRightBack;
		this.vertexPositions[6] = topRightBack;
		this.vertexPositions[7] = topLeftBack;
		this.quadList[0] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { botRightBack, botRightFront, topRightFront, topRightBack }, texCoordX + depth + width, texCoordY + depth, texCoordX + depth + width + depth, texCoordY + depth + height, textureWidth, textureHeight);
		this.quadList[1] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { botLeftFront, botLeftBack, topLeftBack, topLeftFront }, texCoordX, texCoordY + depth, texCoordX + depth, texCoordY + depth + height, textureWidth, textureHeight);
		this.quadList[2] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { botRightBack, botLeftBack, botLeftFront, botRightFront }, texCoordX + depth, texCoordY, texCoordX + depth + width, texCoordY + depth, textureWidth, textureHeight);
		this.quadList[3] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { topRightFront, topLeftFront, topLeftBack, topRightBack }, texCoordX + depth + width, texCoordY + depth, texCoordX + depth + width + width, texCoordY, textureWidth, textureHeight);
		this.quadList[4] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { botRightFront, botLeftFront, topLeftFront, topRightFront }, texCoordX + depth, texCoordY + depth, texCoordX + depth + width, texCoordY + depth + height, textureWidth, textureHeight);
		this.quadList[5] = new AdvancedTexturedQuad(new AdvancedPositionTextureVertex[] { botLeftBack, botRightBack, topRightBack, topLeftBack }, texCoordX + depth + width + depth, texCoordY + depth, texCoordX + depth + width + depth + width, texCoordY + depth + height, textureWidth, textureHeight);
	}

	public void shouldRenderQuad(int quadNum, boolean doRender) {
		this.renderQuad[quadNum] = doRender;
	}

	public void setQuadTexCoords(int quadNum, double texCoordMinX, double texCoordMinY, double texCoordMaxX, double texCoordMaxY) {
		this.quadList[quadNum].vertexPositions[3].texturePositionX = (texCoordMinX / this.textureWidth);
		this.quadList[quadNum].vertexPositions[3].texturePositionY = (texCoordMinY / this.textureHeight);
		this.quadList[quadNum].vertexPositions[2].texturePositionX = (texCoordMaxX / this.textureWidth);
		this.quadList[quadNum].vertexPositions[2].texturePositionY = (texCoordMinY / this.textureHeight);
		this.quadList[quadNum].vertexPositions[1].texturePositionX = (texCoordMaxX / this.textureWidth);
		this.quadList[quadNum].vertexPositions[1].texturePositionY = (texCoordMaxY / this.textureHeight);
		this.quadList[quadNum].vertexPositions[0].texturePositionX = (texCoordMinX / this.textureWidth);
		this.quadList[quadNum].vertexPositions[0].texturePositionY = (texCoordMaxY / this.textureHeight);
	}

	public void rotateQuad(int quadNum, int times) {
		double[] texX = new double[4];
		double[] texY = new double[4];
		
		for (int i = 0; i < 4; i++) {
			texX[i] = this.quadList[quadNum].vertexPositions[i].texturePositionX;
			texY[i] = this.quadList[quadNum].vertexPositions[i].texturePositionY;
		}

		for (int i = 0; i < 4; i++) {
			int target = (i + times) % 4;
			this.quadList[quadNum].vertexPositions[i].texturePositionX = texX[target];
			this.quadList[quadNum].vertexPositions[i].texturePositionY = texY[target];
		}
	}

	public void setAllQuadsTexCoords(double texCoordMinX, double texCoordMinY, double texCoordMaxX, double texCoordMaxY)
	{
		for (int i = 0; i < 6; i++) {
			setQuadTexCoords(i, texCoordMinX, texCoordMinY, texCoordMaxX, texCoordMaxY);
		}
	}

	public void render(Tessellator tessellator, float scale)
	{
		int quadLength = this.quadList.length;

		for (int i = 0; i < quadLength; i++) {
			if (renderQuad[i] != false) {
				this.quadList[i].draw(tessellator, scale);
			}
		}
	}
}
