
package steamcraft.common.blocks;

import net.minecraftforge.fluids.Fluid;

/**
 * @author warlordjones
 *
 */
public class FluidBoiling extends Fluid
{
	public FluidBoiling(String fluidName)
	{
		super(fluidName);
		this.setTemperature(373);
		this.setDensity(900);
		this.setViscosity(800);
		this.setIcons(this.stillIcon, this.flowingIcon);
	}

}
