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
package steamcraft.common.worldgen.dimension.util;

import net.minecraft.util.ChunkCoordinates;

public class DeepsPortalPosition extends ChunkCoordinates
{
	public long field_85087_d;
	final TeleporterDeeps field_85088_e;

	public DeepsPortalPosition(TeleporterDeeps teleporterDeeps, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = teleporterDeeps;
		this.field_85087_d = par5;
	}
}
