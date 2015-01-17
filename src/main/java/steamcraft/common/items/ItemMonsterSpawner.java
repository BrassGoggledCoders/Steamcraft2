package steamcraft.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.InitItems;
import steamcraft.common.entities.EntityGrub;
import steamcraft.common.entities.EntityLostMiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMonsterSpawner extends BaseItemWithMetadata
{
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(world.isRemote)
		{
			EntityLivingBase entity = null;
			if(stack.getItemDamage() == 0)
			{
				entity = new EntityGrub(world);
			}
			else
			{
				entity = new EntityLostMiner(world);
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
		for(int var4 = 0; var4 < 2; ++var4)
			l.add(new ItemStack(InitItems.itemMonsterSpawner, 1, var4));
	}
}
