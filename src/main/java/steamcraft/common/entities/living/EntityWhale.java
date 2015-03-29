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
 */
package steamcraft.common.entities.living;

import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;

import steamcraft.common.init.InitItems;

public class EntityWhale extends EntityWaterMob
{

	public EntityWhale(World p_i1689_1_)
	{
		super(p_i1689_1_);
		this.setSize(4F, 3F);
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill
	 * this mob.
	 */
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootingLevel)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLevel);
		int k;

		for(k = 0; k < j; ++k)
		{
			this.dropItem(InitItems.itemWhalebone, this.rand.nextInt(10) + 3);
			this.dropItem(InitItems.itemWhaleBlubber, this.rand.nextInt(10) + 5);
		}

		j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootingLevel);

		for(k = 0; k < j; ++k)
		{
			if(this.isBurning())
			{
				this.dropItem(InitItems.itemCookedWhaleMeat, this.rand.nextInt(15) + 5);
			}
			else
			{
				this.dropItem(InitItems.itemWhaleMeat, this.rand.nextInt(15) + 5);
			}
		}
	}

}
