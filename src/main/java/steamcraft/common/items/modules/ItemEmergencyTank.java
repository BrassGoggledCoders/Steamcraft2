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
public class ItemEmergencyTank extends BaseItem implements IArmorModule
{
	public ItemEmergencyTank()
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
		return "Emergency Water Tank";
	}

	@Override
	public String getModuleId()
	{
		return "watertank";
	}

	@Override
	public void applyArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.isBurning())
		{
			//Hmm. Dosn't work.
			//player.worldObj.setBlock(player.serverPosX, player.serverPosY+1, player.serverPosZ, Blocks.flowing_water);
			player.extinguish();
			for(float i = 0; i < 1; i += 0.1F)
			{
				world.spawnParticle("splash", player.posX + i + world.rand.nextFloat(), player.posY + i + world.rand.nextFloat(), player.posZ + i + world.rand.nextFloat(), 0, 0, 0);
				world.spawnParticle("splash", player.posX - i - world.rand.nextFloat(), player.posY - i - world.rand.nextFloat(), player.posZ - i - world.rand.nextFloat(), 0, 0, 0);
			}
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
