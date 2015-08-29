/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.block;

import net.minecraft.item.ItemStack;

/**
 * The Interface IHammerable.
 *
 * @author warlordjones
 */
@Deprecated
public interface IHammerable
{

	/**
	 * Gets the output when the Drop Hammer (NYI) is used on the implementing
	 * Block
	 *
	 * @param meta
	 *            the meta
	 * @return the output
	 */
	public abstract ItemStack getOutput(int meta);
}
