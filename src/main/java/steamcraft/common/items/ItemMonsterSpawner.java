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
package steamcraft.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.entities.living.EntityAbandonedGolem;
import steamcraft.common.entities.living.EntityBoar;
import steamcraft.common.entities.living.EntityGhostSpider;
import steamcraft.common.entities.living.EntityGiantSpider;
import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import steamcraft.common.entities.living.EntityShroomSkeleton;
import steamcraft.common.entities.living.EntityShroomZombie;
import steamcraft.common.entities.living.EntitySpiderQueen;
import steamcraft.common.entities.living.EntityVampireBat;
import steamcraft.common.entities.living.EntityWhale;
import steamcraft.common.init.InitItems;

public class ItemMonsterSpawner extends BaseItemWithMetadata
{
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}

		if (!world.isRemote)
		{
			EntityLivingBase entity = null;
			switch (stack.getItemDamage())
			{
			case 0:
				entity = new EntityGrub(world);
				break;
			case 1:
				entity = new EntityLostMiner(world);
				break;
			case 2:
				entity = new EntityBoar(world);
				break;
			case 3:
				entity = new EntityGhostSpider(world);
				break;
			case 4:
				entity = new EntityGiantSpider(world);
				break;
			case 5:
				entity = new EntitySpiderQueen(world);
				break;
			case 6:
				entity = new EntityAbandonedGolem(world);
				break;
			case 7:
				entity = new EntityShroomSkeleton(world);
				break;
			case 8:
				entity = new EntityShroomZombie(world);
				break;
			case 9:
				entity = new EntityVampireBat(world);
				break;
			case 10:
				entity = new EntityWhale(world);
				break;
			default:
				entity = new EntitySheep(world);
				break;
			}
			entity.setPosition(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(entity);
		}
		return stack;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 11; ++var4)
			l.add(new ItemStack(InitItems.itemMonsterSpawner, 1, var4));
	}
}
