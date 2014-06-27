package steamcraft.common.blocks.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileDropHammer;

public class BlockDropHammerAnvil extends BlockContainerMod
 {

 public BlockDropHammerAnvil(Material mat)
 {
 super(mat);
 }

@Override
public TileEntity createNewTileEntity(World var1, int var2)
{
	return new TileDropHammer();
}
}
