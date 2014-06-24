package steamcraft.common.blocks;

import net.minecraftforge.fluids.Fluid;

public class FluidSteam extends Fluid
{
	public FluidSteam(String fluidName)
	{
		super(fluidName);
		setGaseous(true);
		setTemperature(110);
		setDensity(-100);
		setViscosity(500);
		setLuminosity(1);
		setUnlocalizedName("steamFluid");
		//registerBlockIcons(null);
		setIcons(stillIcon, flowingIcon);
		//setStillIcon(BlockFluidSteam.icon[1]);
		//setStillIcon(BlockFluidSteam.icon[0]);
	}

}
