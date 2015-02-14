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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import steamcraft.common.Steamcraft;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;

public class ItemWingpackModule extends BaseArmorModule
{
	public ItemWingpackModule()
	{
		super();
		ModuleRegistry.registerModule(this);
		ModuleRegistry.setModuleIncompatibilities(this, "pistonplating");
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return 1;
	}

	@Override
	public String getName()
	{
		return "Wingpack";
	}

	@Override
	public String getModuleId()
	{
		return "wingpack";
	}

	@Override
	public boolean applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(!player.capabilities.allowFlying)
		{
			if(!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = stack.getTagCompound();

			if(((player.posY < 200) && Steamcraft.proxy.isKeyPressed(0)))
			{

				if(player.motionY > 0.0D)
					player.motionY += 0.08499999910593033D;
				else
					player.motionY += 0.11699999910593033D;

				world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);

				return true;
			}

			if((player.motionY < 0.0D) && player.isSneaking())
			{
				player.motionY /= 1.4D;

				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				return true;
			}

			if(!player.onGround)
			{
				player.motionX *= 1.04D;
				player.motionZ *= 1.04D;
			}

			if(player.fallDistance > 0.0F && !player.onGround)
			{
				player.fallDistance = 0.0F;
				return true;
			}
		}
		return false;
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

	@Override
	public int getSteamConsumedOnEffect()
	{
		return 5;
	}

	@Override
	public int getEnergyConsumedOnEffect()
	{
		return 0;
	}
}
