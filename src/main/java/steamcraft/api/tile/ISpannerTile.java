
package steamcraft.api.tile;

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
