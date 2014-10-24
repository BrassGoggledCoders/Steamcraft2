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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.ClientHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemTimeClock extends BaseItem
{
	private IIcon readyIcon;

	public ItemTimeClock()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
	   return EnumAction.bow;
	}
	 public int getMaxItemUseDuration(ItemStack p_77626_1_)
	 {
	   return 72000;
	 }
	 public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int useCount) {
		 if(!world.isRemote)
		 {
			if(useCount > 70000)
			{
				 if(ClientHelper.isShiftKeyDown())
					{
						world.setWorldTime(13000);
						ChatComponentText text = new ChatComponentText("Time set to Night");
						player.addChatComponentMessage(text);
						player.playSound(LibInfo.PREFIX + "warp", 1, 1);
					}
					else
					{
						ChatComponentText text = new ChatComponentText("Time set to Day");
						player.addChatComponentMessage(text);
						world.setWorldTime(1000);
						player.playSound(LibInfo.PREFIX + "warp", 1, 1);
					}
			 }
			else player.playSound("minecraft:mob.wolf.whine", 1, 1);
		 }
	 }
	 public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	    {
	        return p_77654_1_;
	    }
	 public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	    {
	        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));

	        return p_77659_1_;
	    }
	 @SideOnly(Side.CLIENT)
	    public IIcon getItemIconForUseDuration(int use)
	    {
		  if(use > 70000)
		  {
			  return readyIcon;
		  }
		  else return itemIcon;
	    }

		@SideOnly(Side.CLIENT)
		@Override
		public void registerIcons(IIconRegister ir)
		{
			itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemTimeClock");
			readyIcon = ir.registerIcon(LibInfo.PREFIX + "itemTimeClockReady");
		}
}
