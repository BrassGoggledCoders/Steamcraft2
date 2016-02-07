
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;

import boilerplate.common.baseclasses.blocks.BaseBlock;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.worldgen.dimension.util.TeleporterDeeps;

public class BlockFissurePortal extends BaseBlock
{
	public BlockFissurePortal(Material mat)
	{
		super(mat, Steamcraft.instance);
		this.setBlockUnbreakable();
		this.setLightLevel(1F);
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getWorldInfo().getTerrainType() == WorldType.FLAT)
			return;
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
			if (thePlayer.timeUntilPortal > 0)
			{
				thePlayer.timeUntilPortal = 10;
			}
			else if (thePlayer.dimension != ConfigGeneral.deepsDimensionID)
			{
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, ConfigGeneral.deepsDimensionID,
						new TeleporterDeeps(thePlayer.mcServer.worldServerForDimension(ConfigGeneral.deepsDimensionID)));
			}
			else
			{
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0,
						new TeleporterDeeps(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

}
