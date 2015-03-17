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

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import steamcraft.common.init.InitItems;

public class EntityWhale extends EntitySquid
{

	public EntityWhale(World p_i1689_1_)
	{
		super(p_i1689_1_);
	}

	@Override
	protected Item getDropItem()
	{
		return InitItems.itemWhaleMeat;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill
	 * this mob.
	 */
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootingLevel)
	{
		this.entityDropItem(new ItemStack(InitItems.itemWhaleBlubber, this.rand.nextInt(5) * lootingLevel), 0);
		this.entityDropItem(new ItemStack(InitItems.itemWhalebone, this.rand.nextInt(5) * lootingLevel), 0);
	}

}
