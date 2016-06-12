
package steamcraft.api.item;

import buildcraft.api.tools.IToolWrench;
import cofh.api.item.IToolHammer;
import cpw.mods.fml.common.Optional;
import mods.railcraft.api.core.items.IToolCrowbar;

/**
 * The Interface IUniversalWrench. Helper interface - implementing items will be
 * compatible with Railcraft, MFR, BC and TE wrenches as well as Steamcraft's
 * spanner
 *
 * @author warlordjones
 */
@Optional.InterfaceList({ @Optional.Interface(iface = "buildcraft.api.tools.IToolWrench", modid = "BuildCraft|Core"),
		@Optional.Interface(iface = "mods.railcraft.api.core.items.IToolCrowbar", modid = "Railcraft") })
public interface IUniversalWrench extends IToolHammer, IToolWrench, IToolCrowbar
{

}
