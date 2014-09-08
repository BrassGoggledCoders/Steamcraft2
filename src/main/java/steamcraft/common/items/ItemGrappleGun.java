package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.entities.EntityGrapplingHook;

public class ItemGrappleGun extends BaseItem
{
	public EntityGrapplingHook hook;

	public ItemGrappleGun()
	{
		hook = null;
	}
	 /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	if (hook != null)
        {
            int i = hook.func_146034_e();
            stack.damageItem(i, player);
            player.swingItem();
        }
        else
        {
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityGrapplingHook(world, player, this));
            }

            player.swingItem();
        }
        return stack;
    }

}
