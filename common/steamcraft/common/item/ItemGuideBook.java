/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 18, 2014, 7:47:01 PM]
 */
package common.steamcraft.common.item;

import java.util.List;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
public class ItemGuideBook extends ItemMod {
	public ItemGuideBook(int id) {
		super(id);
		this.setMaxStackSize(1);
	}
	
	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add("Don't worry, this item");
		list.add("doesn't explode ;)");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
			player.openGui(SC2.instance, GuiIDs.GUI_ID_GUIDE_BOOK, player.worldObj, 0, 0, 0);
        }
        
        return stack;
    }

	@Override
    /** Sends NBT Tag to client. */
    public boolean getShareTag() {
        return true;
    }

    public static boolean validBookTagPages(NBTTagCompound tagCompound) {
        if (tagCompound == null) {
            return false;
        } else if (!tagCompound.hasKey("pages")) {
            return false;
        } else {
            NBTTagList nbttaglist = (NBTTagList)tagCompound.getTag("pages");

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagString nbttagstring = (NBTTagString)nbttaglist.tagAt(i);

                if (nbttagstring.data == null)
                {
                    return false;
                }

                if (nbttagstring.data.length() > 256)
                {
                    return false;
                }
            }

            return true;
        }
    }
}
