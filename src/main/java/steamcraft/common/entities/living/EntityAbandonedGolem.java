package steamcraft.common.entities.living;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.world.World;

public class EntityAbandonedGolem extends EntityIronGolem
{

	public EntityAbandonedGolem(World world)
	{
		super(world);
		this.setBeenAttacked();
		this.setPlayerCreated(false);
	}

	/**
	 * Returns true if this entity can attack entities of the specified class.
	 */
	@Override
	public boolean canAttackClass(Class p_70686_1_)
	{
		return true;
	}
}
