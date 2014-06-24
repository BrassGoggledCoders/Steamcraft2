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
 * File created @ [Jun 23, 2014, 10:51:48 PM]
 */
package steamcraft.common.blocks.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileIntake;

/**
 * @author decebaldecebal
 *
 */
public class BlockIntake extends BlockContainerMod
{
	public BlockIntake(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setBlockName("blockIntake");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileIntake();
	}
}
