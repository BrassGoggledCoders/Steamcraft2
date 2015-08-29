/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.block;

import net.minecraft.block.Block;

/**
 * The Interface IChiselable.
 *
 * @author warlordjones
 */
public interface IChiselable
{

	/**
	 *
	 * @return the block created when our chisel is used on this block
	 */
	public Block getChiseledVariant();

	/**
	 * @return the metadata of the block created when our chisel is used on this
	 *         block
	 */
	public int getChiseledVariantMeta();
}
