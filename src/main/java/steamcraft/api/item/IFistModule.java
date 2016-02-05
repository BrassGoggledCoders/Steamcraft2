
package steamcraft.api.item;

/**
 * The Interface IFistModule.
 */
public interface IFistModule extends IModule
{
	// The type of effect (see below)
	/**
	 * Gets the module effect type.
	 *
	 * @return the module effect type
	 */
	EnumModuleEffectType getModuleEffectType();

	//
	/**
	 * The Enum EnumModuleEffectType.
	 */
	enum EnumModuleEffectType
	{
		/** The rightclick. */
		RIGHTCLICK,
		/** The attack. */
		ATTACK
	}
}
