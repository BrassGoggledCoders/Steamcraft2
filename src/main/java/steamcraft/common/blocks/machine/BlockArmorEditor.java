package steamcraft.common.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileArmorEditor;

public class BlockArmorEditor extends BlockContainerMod {

	protected BlockArmorEditor(Material p_i45394_1_) {
		super(p_i45394_1_);

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileArmorEditor();
	}

}
