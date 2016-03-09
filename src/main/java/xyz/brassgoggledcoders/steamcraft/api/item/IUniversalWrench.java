/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.steamcraft.api.item;

import cpw.mods.fml.common.Optional;

import buildcraft.api.tools.IToolWrench;

import cofh.api.item.IToolHammer;
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
