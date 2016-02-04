
package steamcraft.common.tiles.container;

import boilerplate.common.baseclasses.blocks.BaseContainer;
import net.minecraft.entity.player.EntityPlayer;

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
