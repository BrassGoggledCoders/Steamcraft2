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
 * File created @ [Mar 14, 2014, 10:21:08 AM]
 */
package steamcraft.api.machines;

import net.minecraft.util.Vec3;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class AdvancedPositionTextureVertex
{
	public Vec3 vector3D;
	public double texPosX;
	public double texPosY;

	public AdvancedPositionTextureVertex(float fx, float fy, float fz, double dx, double dy) 
	{
		this(Vec3.createVectorHelper(fx, fy, fz), dx, dy);
	}

	public AdvancedPositionTextureVertex setTexturePosition(double dx, double dy) 
	{
		return new AdvancedPositionTextureVertex(this, dx, dy);
	}

	public AdvancedPositionTextureVertex(AdvancedPositionTextureVertex texVert, double dx, double dy)
	{
		this.vector3D = texVert.vector3D;
		this.texPosX = dx;
		this.texPosY = dy;
	}

	public AdvancedPositionTextureVertex(Vec3 vec3, double dx, double dy) 
	{
		this.vector3D = vec3;
		this.texPosX = dx;
		this.texPosY = dy;
	}
}
