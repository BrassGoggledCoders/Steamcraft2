
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;

import boilerplate.common.baseclasses.blocks.BaseContainer;

/**
 * @author decebaldecebal
 *
 */
public class ContainerChangeExtractions extends BaseContainer
{
	public ContainerChangeExtractions()
	{

	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
}
