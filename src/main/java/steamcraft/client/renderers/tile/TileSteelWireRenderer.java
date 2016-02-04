
package steamcraft.client.renderers.tile;

import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class TileSteelWireRenderer extends TileCopperWireRenderer
{
	public TileSteelWireRenderer()
	{
		this.texture = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockSteelWire.png");
		this.texture1 = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/blockInsulatedSteelWire.png");
	}
}
