
package steamcraft.client.renderers.tile;

import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class TileSteelPipeRenderer extends TileCopperPipeRenderer
{
	public TileSteelPipeRenderer()
	{
		this.texture = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockSteelPipe.png");
	}
}
