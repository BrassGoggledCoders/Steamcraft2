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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.items.BaseItem;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;

/**
 * @author warlordjones
 *
 */
public class ItemFlippers extends BaseItem implements IArmorModule
{
	public ItemFlippers()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
	}

	@Override
	public int getApplicablePiece()
	{
		return 3;
	}

	@Override
	public String getName()
	{
		return "Flippers";
	}

	@Override
	public String getModuleId()
	{
		return "flippers";
	}

	@Override
	public boolean applyArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.isInWater())
		{
			player.motionX *= 1.2D;
			player.motionY *= 1.2D;
			player.motionZ *= 1.2D;
			return true;
		}
		return false;
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

	@Override
	public ArrayList<IArmorModule> getListOfIncompatibleModules()
	{
		// ArrayList incompats = new ArrayList();
		// incompats.add(InitItems.itemPistonPlating);
		// return incompats;
		return null;
	}

	@Override
	public int getSteamConsumedOnEffect()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyConsumedOnEffect()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
