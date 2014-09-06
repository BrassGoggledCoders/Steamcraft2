/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.items.modules;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.items.BaseItem;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;

/**
 * @author warlordjones
 *
 */
public class ItemLegBraces extends BaseItem implements IArmorModule
{
	public ItemLegBraces()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
	}

	@Override
	public int getApplicablePiece()
	{
		return 2;
	}

	@Override
	public String getName()
	{
		return "Leg Bracing";
	}

	@Override
	public String getModuleId()
	{
		return "legbraces";
	}

	@Override
	public void applyArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		final float distToFall = player.fallDistance;

		if (distToFall > 3.0F)
		{
			player.fallDistance = distToFall * 0.888F;
		}
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}
	@Override
	public ArrayList<IArmorModule> getListOfIncompatibleModules()
	{
		return null;
	}

}
