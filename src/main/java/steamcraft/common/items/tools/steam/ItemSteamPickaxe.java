
package steamcraft.common.items.tools.steam;

/**
 * @author Surseance
 *
 */
public class ItemSteamPickaxe extends ItemSteamTool
{
	public ItemSteamPickaxe(ToolMaterial mat)
	{
		super(1.0F);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
	}
}