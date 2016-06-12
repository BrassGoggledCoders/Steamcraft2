
package steamcraft.common.entities.living;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;
import steamcraft.common.init.InitItems;

public class EntityWhale extends EntityWaterMob
{

	public EntityWhale(World p_i1689_1_)
	{
		super(p_i1689_1_);
		this.setSize(6F, 3F);
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity
	 * has recently been hit by a player. @param par2 - Level of Looting used to
	 * kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootingLevel)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLevel);
		int k;

		for (k = 0; k < j; ++k)
		{
			this.dropItem(InitItems.itemWhalebone, this.rand.nextInt(10) + 3);
			this.dropItem(InitItems.itemWhaleBlubber, this.rand.nextInt(10) + 5);
		}

		j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootingLevel);

		for (k = 0; k < j; ++k)
		{
			// if (this.isBurning())
			// {
			// this.dropItem(InitItems.itemCookedWhaleMeat,
			// this.rand.nextInt(15) + 5);
			// }
			// else
			// {
			this.dropItem(InitItems.itemWhaleMeat, this.rand.nextInt(15) + 5);
			// }
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
	}

}
