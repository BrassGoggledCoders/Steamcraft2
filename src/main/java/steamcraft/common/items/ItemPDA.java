package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;

public class ItemPDA extends BaseItem
{
	public ItemPDA()
	{
		super();
		this.setMaxStackSize(1);
	}
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player)
    {
    	player.openGui(Steamcraft.instance, GuiIDs.PDA, world, 0, 0, 0);
        return p_77659_1_;
    }
}
