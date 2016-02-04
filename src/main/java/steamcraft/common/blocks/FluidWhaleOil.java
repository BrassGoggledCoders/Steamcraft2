
package steamcraft.common.blocks;

import net.minecraftforge.fluids.Fluid;

/**
 * @author warlordjones
 *
 */
public class FluidWhaleOil extends Fluid
{
	public FluidWhaleOil(String fluidName)
	{
		super(fluidName);
		this.setIcons(this.stillIcon, this.flowingIcon);
	}

}
