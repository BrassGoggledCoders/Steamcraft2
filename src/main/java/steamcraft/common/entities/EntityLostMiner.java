package steamcraft.common.entities;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLostMiner extends EntityZombie
{

	public EntityLostMiner(World p_i1745_1_)
	{
		super(p_i1745_1_);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Makes entity wear random armor based on difficulty
	 */
	@Override
	protected void addRandomArmor()
	{
		super.addRandomArmor();
		// this.setCurrentItemOrArmor(3, new ItemStack(InitItems.itemMinerHelmet));

		if(this.rand.nextFloat() < (0.5F))
		{
			int i = this.rand.nextInt(6);

			switch(i)
			{
				case 0:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
					break;
				case 1:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_pickaxe));
					break;
				case 2:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_pickaxe));
					break;
				case 3:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_shovel));
					break;
				case 4:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
					break;
				case 5:
					this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_shovel));
					break;
			}
		}
	}

}
