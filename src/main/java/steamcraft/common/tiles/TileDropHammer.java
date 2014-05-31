package steamcraft.common.tiles;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.tileentity.TileEntity;

public class TileDropHammer extends TileEntity
{
	@Override
	public void updateEntity()
	{
		if(!isInvalid())
		{
		EntityCreeper ent = new EntityCreeper(worldObj);
		ent.setPosition(xCoord, yCoord, zCoord);
		worldObj.spawnEntityInWorld(ent);
		}
	}
}
