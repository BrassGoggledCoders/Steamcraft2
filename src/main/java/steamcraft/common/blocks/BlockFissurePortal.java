package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigGeneral;

public class BlockFissurePortal extends BaseBlock
{
	public BlockFissurePortal(Material mat)
	{
		super(mat);

	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		// if(entity.ridingEntity == null && entity.riddenByEntity == null && !world.isRemote)
		// {
		// if(entity.dimension == 0)
		entity.travelToDimension(-1);
		// else
		// entity.travelToDimension(0);
		// }
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_,
			float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		player.travelToDimension(ConfigGeneral.deepsDimensionID);
		return true;
	}
}
