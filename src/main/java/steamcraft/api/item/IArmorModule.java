/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * The Interface IArmorModule.
 *
 * @author warlordjones
 */
public interface IArmorModule extends IModule
{
	/**
	 * Gets the armor piece the module can be applied to. 0 is helmet, 1 is
	 * plate, 2 is legs and 3 is boots. Return -1 for any piece
	 *
	 * @return the applicable piece
	 */
	public int getApplicablePiece();

	/**
	 * Gets the armor effect type. See @link EnumArmorEffectType
	 *
	 * @return the armor effect type
	 */
	public EnumArmorEffectType getArmorEffectType();

	/**
	 * EnumArmorEffectType. TODO: Allow multiple effect types
	 */
	public static enum EnumArmorEffectType
	{
		/** Called every tick while the armor is worn, server and clientside. */
		ONTICK,
		/** See @link IDefensiveArmorModule for more info on this */
		DEFENSIVE,
		/**
		 * Clientside. Allows you to display on the player's screen
		 */
		HUD,
		/** SC2 Use Only! */
		SPECIAL
	}

	@Deprecated
	public int getModuleWeight();

	public void onArmorEquipped(World world, EntityPlayer player);

	public void onArmorUnequipped(World world, EntityPlayer player);
}
