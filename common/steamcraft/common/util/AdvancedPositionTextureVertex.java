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
 * File created @ [Feb 17, 2014, 12:11:26 PM]
 */
package common.steamcraft.common.util;

import net.minecraft.util.Vec3;

/**
 * @author MrArcane111
 *
 */
public class AdvancedPositionTextureVertex {
	public Vec3 vector3D;
	public double texturePositionX;
	public double texturePositionY;

	public AdvancedPositionTextureVertex(float x, float y, float z, double d1, double d2) {
		this(Vec3.createVectorHelper(x, y, z), d1, d2);
	}

	public AdvancedPositionTextureVertex setTexturePosition(double x, double y) {
		return new AdvancedPositionTextureVertex(this, x, y);
	}

	public AdvancedPositionTextureVertex(AdvancedPositionTextureVertex textVert, double x, double y) {
		this.vector3D = textVert.vector3D;
		this.texturePositionX = x;
		this.texturePositionY = y;
	}

	public AdvancedPositionTextureVertex(Vec3 vec3, double x, double y) {
		this.vector3D = vec3;
		this.texturePositionX = x;
		this.texturePositionY = y;
	}
}
