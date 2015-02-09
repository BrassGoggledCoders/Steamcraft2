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
package steamcraft.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import net.minecraftforge.common.IExtendedEntityProperties;

import steamcraft.common.container.InventoryVanity;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 * 
 */
// TODO: Add packets
public class EntityPlayerExtended implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = ModInfo.PREFIX + "EntityPlayerExtended";

	private final InventoryVanity inventory = new InventoryVanity();
	private int cooldown = 0;

	private final EntityPlayer player;

	public EntityPlayerExtended(final EntityPlayer player)
	{
		this.player = player;
	}

	public static final void register(final EntityPlayer player)
	{
		player.registerExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME, new EntityPlayerExtended(player));
	}

	public static final EntityPlayerExtended get(final EntityPlayer player)
	{
		return (EntityPlayerExtended) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(final NBTTagCompound tagCompound)
	{
		final NBTTagCompound properties = new NBTTagCompound();
		tagCompound.setTag(EXT_PROP_NAME, properties);
		this.inventory.writeToNBT(properties);
		tagCompound.setInteger("cooldown", this.cooldown);
	}

	@Override
	public void loadNBTData(final NBTTagCompound tagCompound)
	{
		final NBTTagCompound properties = (NBTTagCompound) tagCompound.getTag(EXT_PROP_NAME);
		this.inventory.readFromNBT(properties);
		this.cooldown = tagCompound.getInteger("cooldown");
	}

	@Override
	public void init(final Entity entity, final World world)
	{
	}

	public InventoryVanity getInventory()
	{
		return this.inventory;
	}

	public int getCooldown()
	{
		return this.cooldown;
	}

	public void setCooldown(int newCooldown)
	{
		this.cooldown = newCooldown;
	}
}
