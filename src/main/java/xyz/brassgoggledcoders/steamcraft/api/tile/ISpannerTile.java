/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.steamcraft.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author decebaldecebal
 *
 */
public interface ISpannerTile
{
	public void changeExtraction();
	public void changeExtraction(int dirIndex);
	public ForgeDirection[] getExtractableConnections();
	public ForgeDirection[] getExtractions();
}
