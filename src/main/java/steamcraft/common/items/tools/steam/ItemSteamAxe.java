
package steamcraft.common.items.tools.steam;

/**
 * @author Surseance
 *
 */
public class ItemSteamAxe extends ItemSteamTool
{

	public ItemSteamAxe(ToolMaterial mat)
	{
		super(2.0F);
		this.setHarvestLevel("axe", mat.getHarvestLevel());
	}
}
