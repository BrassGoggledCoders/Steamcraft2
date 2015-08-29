/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package steamcraft.api.item;

/**
 * The Interface IDefensiveArmorModule.
 *
 * {@link EnumArmorEffectType} should always return DEFENSIVE when using this
 * interface
 *
 * @author warlordjones
 */
public interface IDefensiveArmorModule extends IArmorModule
{

	/**
	 * Gets the max damage absorbed by the armour.
	 *
	 * @return the max damage absorbed
	 */
	public int getMaxDamageAbsorb();

	/**
	 * Gets the damage absorb ratio.
	 *
	 * @return the damage absorb ratio
	 */
	public int getDamageAbsorbRatio();

	/**
	 * Gets the armor to display on the player's hud. 1 = half a chestplate
	 *
	 * @return the armor to display
	 */
	public int getArmorToDisplay();

}
