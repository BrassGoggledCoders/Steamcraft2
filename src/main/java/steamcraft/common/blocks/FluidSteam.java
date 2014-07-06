/*
 * 
 */
package steamcraft.common.blocks;

import net.minecraftforge.fluids.Fluid;

public class FluidSteam extends Fluid
{
	public FluidSteam(String fluidName)
	{
		super(fluidName);
		this.setGaseous(true);
		this.setTemperature(110);
		this.setDensity(-100);
		this.setViscosity(500);
		this.setLuminosity(1);
		this.setUnlocalizedName("steamFluid");
		// registerBlockIcons(null);
		this.setIcons(this.stillIcon, this.flowingIcon);
		// setStillIcon(BlockFluidSteam.icon[1]);
		// setStillIcon(BlockFluidSteam.icon[0]);
	}

}
