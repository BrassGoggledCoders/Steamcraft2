package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockFissurePortal extends BaseBlock
{
	public BlockFissurePortal(Material mat)
	{
		super(mat);
		this.setBlockUnbreakable();
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity)
	{
		if(entity.ridingEntity == null && entity.riddenByEntity == null && !p_149670_1_.isRemote)
		{
			// Utils.changeEntityDimension(entity, ConfigGeneral.deepsDimensionID);
		}
	}
}
