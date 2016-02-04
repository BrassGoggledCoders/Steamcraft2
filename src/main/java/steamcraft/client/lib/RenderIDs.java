
package steamcraft.client.lib;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderIDs
{

	public static int blockCastIronLampRI;
	public static int blockCrystalRI;
	public static int blockDropHammerRI;
	public static int blockHatchRI;
	public static int blockLightningRodRI;
	public static int blockCopperPipeRI;
	public static int blockSteelPipeRI;
	public static int blockCopperTankRI;
	public static int blockTeslaCoilRI;
	public static int blockBatteryRI;
	public static int blockChargerRI;
	public static int blockCastIronRailingRI;
	public static int blockCopperWireRI;
	public static int blockSteelWireRI;
	public static int blockPlankStackRI;
	public static int blockArmorEditorRI;
	public static int blockSpiderEggRI;
	public static int blockTrunkRI;
	public static int blockCastIronGateRI;
	public static int blockTransWithInsideRI;

	public static void setIDs()
	{
		blockCopperPipeRI = RenderingRegistry.getNextAvailableRenderId();
		blockSteelPipeRI = RenderingRegistry.getNextAvailableRenderId();
		blockCopperTankRI = RenderingRegistry.getNextAvailableRenderId();
		blockCopperWireRI = RenderingRegistry.getNextAvailableRenderId();
		blockSteelWireRI = RenderingRegistry.getNextAvailableRenderId();
		blockLightningRodRI = RenderingRegistry.getNextAvailableRenderId();
		blockTeslaCoilRI = RenderingRegistry.getNextAvailableRenderId();
		blockBatteryRI = RenderingRegistry.getNextAvailableRenderId();
		blockChargerRI = RenderingRegistry.getNextAvailableRenderId();
		blockCastIronRailingRI = RenderingRegistry.getNextAvailableRenderId();
		blockCastIronLampRI = RenderingRegistry.getNextAvailableRenderId();
		blockHatchRI = RenderingRegistry.getNextAvailableRenderId();
		blockPlankStackRI = RenderingRegistry.getNextAvailableRenderId();
		blockCrystalRI = RenderingRegistry.getNextAvailableRenderId();
		blockArmorEditorRI = RenderingRegistry.getNextAvailableRenderId();
		blockSpiderEggRI = RenderingRegistry.getNextAvailableRenderId();
		blockTrunkRI = RenderingRegistry.getNextAvailableRenderId();
		blockCastIronGateRI = RenderingRegistry.getNextAvailableRenderId();
		blockTransWithInsideRI = RenderingRegistry.getNextAvailableRenderId();
	}
}
