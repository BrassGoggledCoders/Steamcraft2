
package steamcraft.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import steamcraft.common.container.InventoryVanity;

/**
 * @author warlordjones
 *
 */
// TODO: Add packets
public class EntityPlayerExtended implements IExtendedEntityProperties {
	public final static String EXT_PROP_NAME = "SteamcraftPlayerExtended";

	private final InventoryVanity inventory = new InventoryVanity();

	private final EntityPlayer player;

	public EntityPlayerExtended(final EntityPlayer player) {
		this.player = player;
	}

	public static void register(final EntityPlayer player) {
		player.registerExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME, new EntityPlayerExtended(player));
	}

	public static EntityPlayerExtended get(final EntityPlayer player) {
		return (EntityPlayerExtended) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(final NBTTagCompound tagCompound) {
		final NBTTagCompound properties = new NBTTagCompound();
		tagCompound.setTag(EXT_PROP_NAME, properties);
		this.inventory.writeToNBT(properties);
	}

	@Override
	public void loadNBTData(final NBTTagCompound tagCompound) {
		final NBTTagCompound properties = (NBTTagCompound) tagCompound.getTag(EXT_PROP_NAME);
		if (properties != null)
			this.inventory.readFromNBT(properties);
	}

	@Override
	public void init(final Entity entity, final World world) {
	}

	public InventoryVanity getInventory() {
		return this.inventory;
	}
}
