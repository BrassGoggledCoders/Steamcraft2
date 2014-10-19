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

/**
 * @author warlordjones & Decebaldecebal
 *
 */
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.entities.projectile.EntityRocket;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.baseclasses.BaseFirearm;
import boilerplate.common.utils.InventoryUtils;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRocketLauncher extends BaseFirearm
{
	public ItemRocketLauncher(int reloadTime, Item ammo, String fireSound, String reloadSound)
	{
		super(0, reloadTime, ammo, null, fireSound, reloadSound);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setUnlocalizedName("itemClockworkRocketLauncher");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	protected void shotBullet(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();
		tag.setShort("reloadTime", this.reloadTime);
		tag.setBoolean("canFire", false);
		stack.setTagCompound(tag);

		player.inventory.consumeInventoryItem(this.ammo);

		if(!world.isRemote && (InventoryUtils.getItemStackInInventory(player, stack) != null))
		{
			FMLLog.bigWarning(InventoryUtils.getItemStackInInventory(player, stack).getUnlocalizedName(), InventoryUtils.getItemStackInInventory(player, stack).getUnlocalizedName());
			world.spawnEntityInWorld(new EntityRocket(world, player, InventoryUtils.getItemStackInInventory(player, stack).getItemDamage(), 10));
		}

		world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
	}

	@Override
	/**
	 * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
	 */
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();

		if((tag.getShort("reloadTime") == 0) && player.inventory.hasItem(this.ammo))
			this.shotBullet(stack, world, player);
		return stack;
	}
}
