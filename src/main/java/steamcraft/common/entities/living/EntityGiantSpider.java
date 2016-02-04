
package steamcraft.common.entities.living;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGiantSpider extends EntitySpider
{

	public EntityGiantSpider(World p_i1743_1_)
	{
		super(p_i1743_1_);
		this.setSize(2.4F, 1.9F);
	}

	@Override
	public boolean attackEntityAsMob(Entity p_70652_1_)
	{
		if (super.attackEntityAsMob(p_70652_1_))
		{
			if (p_70652_1_ instanceof EntityLivingBase)
			{
				byte b0 = 1;

				if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
				{
					b0 = 7;
				}
				else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
				{
					b0 = 15;
				}
				((EntityLivingBase) p_70652_1_).addPotionEffect(new PotionEffect(Potion.blindness.id, b0 * 10, 1));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(22.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7D);
	}
}
