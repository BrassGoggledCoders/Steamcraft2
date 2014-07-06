/*
 *
 */
package steamcraft.common.tiles;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

// TODO: Auto-generated Javadoc
/**
 * The Class TileArmorEditor.
 */
public class TileArmorEditor extends BaseTileWithInventory implements IInventory
{
	public TileArmorEditor(byte invSize)
	{
		super((byte)22);
	}
	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return "Armor Editor";
	}

}
