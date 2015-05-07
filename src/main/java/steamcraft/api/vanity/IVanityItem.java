/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.vanity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Interface IVanityItem.
 *
 * @author warlordjones
 */
public interface IVanityItem
{

	/**
	 * Gets the vanity item model.
	 *
	 * @return the vanity item model
	 */
	@SideOnly(Side.CLIENT)
	public ModelBase getVanityItemModel();

	/**
	 * Gets the item texture location.
	 *
	 * @return the item texture location
	 */
	@SideOnly(Side.CLIENT)
	public ResourceLocation getItemTextureLocation();

	/**
	 * Gets the vanity item type.
	 *
	 * @return the vanity item type
	 */
	public EnumVanityType getVanityItemType();

	/**
	 * Gets the model offset x.
	 *
	 * @return the model offset x
	 */
	public float getModelOffsetX();

	/**
	 * Gets the model offset y.
	 *
	 * @return the model offset y
	 */
	public float getModelOffsetY();

	/**
	 * Gets the model offset z.
	 *
	 * @return the model offset z
	 */
	public float getModelOffsetZ();
}
