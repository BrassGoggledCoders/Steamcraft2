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
package xyz.brassgoggledcoders.steamcraft.common.entities.living;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityShroomSkeleton extends EntitySkeleton
{

	public EntityShroomSkeleton(World world)
	{
		super(world);
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
				((EntityLivingBase) p_70652_1_).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 10, 1));
			}

			return true;
		}
		else
		{
			return false;
		}
	}
}
