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
 * File created @ [May 7, 2014, 11:21:06 AM]
 */
package steamcraft.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class MaterialGas extends MaterialLiquid
{
	private final boolean canBurn;

	public MaterialGas(final boolean canBurn)
	{
		super(MapColor.airColor);
		this.canBurn = canBurn;
	}

	@Override
	public Material setRequiresTool()
	{
		return Material.air;
	}

	@Override
	public boolean getCanBurn()
	{
		return canBurn;
	}

	@Override
	public boolean isReplaceable()
	{
		return true;
	}

	@Override
	public boolean isOpaque()
	{
		return false;
	}
}
