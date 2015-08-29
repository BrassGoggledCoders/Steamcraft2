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
package steamcraft.api.item;

/**
 * The Interface IFistModule.
 */
@Deprecated
public interface IFistModule extends IModule
{
	// The type of effect (see below)
	/**
	 * Gets the module effect type.
	 *
	 * @return the module effect type
	 */
	public EnumModuleEffectType getModuleEffectType();

	//
	/**
	 * The Enum EnumModuleEffectType.
	 */
	public static enum EnumModuleEffectType
	{

		/** The rightclick. */
		RIGHTCLICK, /** The attack. */
		ATTACK
	}
}
