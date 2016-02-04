
package steamcraft.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityTimeBomb extends EntityTNTPrimed
{
	public int fuse;

	public EntityTimeBomb(World p_i1730_1_, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_, int fuse)
	{
		super(p_i1730_1_, p_i1730_2_, p_i1730_4_, p_i1730_6_, p_i1730_8_);

		this.fuse = fuse;
	}

	public EntityTimeBomb(World p_i1730_1_)
	{
		super(p_i1730_1_);

		this.fuse = 10;
	}

}
