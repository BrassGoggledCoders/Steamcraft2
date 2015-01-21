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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 * 
 */
public class ItemEmergencyTank extends BaseArmorModule
{
	public ItemEmergencyTank()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
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
	public boolean applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.isBurning() && world.isAirBlock((int) Math.round(player.posX), (int) Math.round(player.posY), (int) Math.round(player.posZ)))
		{
			world.setBlock((int) Math.round(player.posX), (int) Math.round(player.posY), (int) Math.round(player.posZ), Blocks.flowing_water, 2, 2);
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

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}
}
