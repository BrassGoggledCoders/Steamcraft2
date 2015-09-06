package steamcraft.common.compat;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 9/2/2015.
 */
public abstract class ModCompat
{
	boolean isActive = true;

	public abstract String getName();

	public boolean areRequirementsMet() {
		return true;
	}



	public String dependencies()
	{
		return "";
	}

	public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent)
	{

	}

	public void init(FMLInitializationEvent fmlInitializationEvent)
	{

	}

	public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent)
	{

	}

	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}
}
