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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.api.item.ModuleRegistry;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemClimbingSpikes extends BaseArmorModule
{
	public ItemClimbingSpikes()
	{
		super();
		ModuleRegistry.registerModule(this);
		ModuleRegistry.setModuleIncompatibilities(this, "pistonboots");
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
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
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.isCollidedHorizontally)
		{
			player.motionY = 0.2D;

			float f5 = 0.15F;

			if(player.motionX < (-f5))
			{
				player.motionX = (-f5);
			}

			if(player.motionX > f5)
			{
				player.motionX = f5;
			}

			if(player.motionZ < (-f5))
			{
				player.motionZ = (-f5);
			}

			if(player.motionZ > f5)
			{
				player.motionZ = f5;
			}

			if(player.motionY < -0.15D)
			{
				player.motionY = -0.15D;
			}

			if(player.isSneaking() && (player.motionY < 0.0D))
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

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}
}
