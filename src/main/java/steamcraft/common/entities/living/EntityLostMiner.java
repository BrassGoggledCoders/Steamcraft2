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

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

	@Override
	protected void dropRareDrop(int p_70600_1_)
	{
		int rand = this.rand.nextInt(3);
		switch(rand)
		{
			case 0:
				this.dropItem(Item.getItemFromBlock(Blocks.iron_ore), rand);
				break;
			case 1:
				this.dropItem(Item.getItemFromBlock(Blocks.gold_ore), rand);
				break;
			case 2:
				this.dropItem(Items.coal, rand);
		}
	}

}
