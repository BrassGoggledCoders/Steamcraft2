
package steamcraft.common.lib;

import boilerplate.common.IModInfo;

public class ModInfo implements IModInfo
{
	public static final String ID = "steamcraft2";
	public static final String NAME = "Steamcraft 2";
	public static final String VERSION = "B5.0.1";

	public static final String CLIENT_PROXY = "steamcraft.client.ClientProxy";
	public static final String COMMON_PROXY = "steamcraft.common.CommonProxy";
	public static final String CONFIG_GUI = "steamcraft.client.gui.GuiFactorySteamcraft";

	public static final String PREFIX = "steamcraft:";

	@Override
	public String getID()
	{
		return ID;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public String getVersion()
	{
		return VERSION;
	}

	@Override
	public String getPrefix()
	{
		return PREFIX;
	}

	@Override
	public String getClientProxyPath()
	{
		return CLIENT_PROXY;
	}

	@Override
	public String getCommonProxyPath()
	{
		return COMMON_PROXY;
	}
}
