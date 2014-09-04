package steamcraft.common.items.modules;

import java.util.HashMap;

import boilerplate.steamapi.item.IArmorModule;

public class ModuleRegistry
{
	private static HashMap<String, IArmorModule> modules = new HashMap<String, IArmorModule>();

	public static void registerModule(IArmorModule module)
	{
		modules.put(module.getModuleId(), module);
	}

	public static IArmorModule getModule(String id)
	{
		return modules.get(id);
	}
}
