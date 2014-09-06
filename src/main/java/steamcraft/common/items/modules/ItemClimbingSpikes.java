/**
 * player class was created by BrassGoggledCoders modding team.
 * player class is available as part of the Steamcraft 2 Mod for Minecraft.
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

import net.minecraft.block.Block;
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
public class ItemClimbingSpikes extends BaseItem implements IArmorModule
{
	public ItemClimbingSpikes()
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
		return "Climbing Spikes";
	}

	@Override
	public String getModuleId()
	{
		return "spikes";
	}

	@Override
	public void applyArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.isCollidedHorizontally)
		{
			player.motionY = 0.2D;

			float f5 = 0.15F;

            if (player.motionX < (double)(-f5))
            {
                player.motionX = (double)(-f5);
            }

            if (player.motionX > (double)f5)
            {
                player.motionX = (double)f5;
            }

            if (player.motionZ < (double)(-f5))
            {
                player.motionZ = (double)(-f5);
            }

            if (player.motionZ > (double)f5)
            {
                player.motionZ = (double)f5;
            }

            if (player.motionY < -0.15D)
            {
                player.motionY = -0.15D;
            }

            if (player.isSneaking() && player.motionY < 0.0D)
            {
                player.motionY = 0.0D;
            }

            player.fallDistance = 0.0F;
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
		//ArrayList incompats = new ArrayList();
		//incompats.add(InitItems.itemPistonPlating);
		//return incompats;
		return null;
	}

}
