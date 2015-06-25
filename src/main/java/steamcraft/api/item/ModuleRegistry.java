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

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.collect.Lists;

// TODO: Auto-generated Javadoc
/**
 * The Class ModuleRegistry.
 */
public class ModuleRegistry
{

	/** The modules. */
	private static HashMap<String, IModule> modules = new HashMap<String, IModule>();

	/** The module incompatibilities. */
	@SuppressWarnings("rawtypes")
	private static HashMap<String, ArrayList> moduleIncompatibilities = new HashMap<String, ArrayList>();

	/**
	 * Register module.
	 *
	 * @param module
	 *            the module
	 */
	public static void registerModule(IModule module)
	{
		modules.put(module.getModuleId(), module);
	}

	/**
	 * Gets the module.
	 *
	 * @param id
	 *            the id
	 * @return the module
	 */
	public static IModule getModule(String id)
	{
		return modules.get(id);
	}

	/**
	 * Sets the module incompatibilities.
	 *
	 * @param module
	 *            the module
	 * @param incompatibilities
	 *            the incompatibilities
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public static void setModuleIncompatibilities(IModule module, String... incompatibilities)
	{
		ArrayList list = Lists.newArrayList(incompatibilities);
		moduleIncompatibilities.put(module.getModuleId(), list);
	}

	/**
	 * Gets the module incompatibilities.
	 *
	 * @param id
	 *            the id
	 * @return the module incompatibilities
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public static ArrayList getModuleIncompatibilities(String id)
	{
		return moduleIncompatibilities.get(id);
	}
}
