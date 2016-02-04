
package steamcraft.common.items.tools.steam;

/**
 * @author Surseance
 *
 */
public class ItemSteamShovel extends ItemSteamTool
{

	public ItemSteamShovel(ToolMaterial mat)
	{
		super(1.0F);
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}
}