
package steamcraft.common.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoor extends BlockDoor
{
	public BlockCustomDoor(String type)
	{
		super(Material.wood);
		this.setBlockTextureName(ModInfo.PREFIX + "block" + type + "Door");
		this.setCreativeTab(Steamcraft.tabSC2);
	}
}
