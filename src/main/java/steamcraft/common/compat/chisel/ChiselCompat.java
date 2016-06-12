
package steamcraft.common.compat.chisel;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import steamcraft.common.compat.ModCompat;

/**
 * Created by Skylar on 9/2/2015.
 */
public class ChiselCompat extends ModCompat
{
	public ItemSteamChisel itemSteamChisel;

	@Override
	public String getName()
	{
		return "Chisel";
	}

	@Override
	public boolean areRequirementsMet()
	{
		return Loader.isModLoaded("chisel");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		this.itemSteamChisel = new ItemSteamChisel();
		GameRegistry.registerItem(this.itemSteamChisel, this.itemSteamChisel.getUnlocalizedName());
	}
}
