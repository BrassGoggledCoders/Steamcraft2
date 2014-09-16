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
import java.util.List;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import boilerplate.common.baseclasses.BaseModule;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.IArmorModule.EnumArmorEffectType;
import boilerplate.steamapi.item.ModuleRegistry;

/**
 * @author warlordjones
 *
 */
public class ItemReactivePistonPlating extends BaseModule
{
	public ItemReactivePistonPlating()
	{
		super();
		ModuleRegistry.registerModule(this);
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
		return "Reactive Piston Plating";
	}

	@Override
	public String getModuleId()
	{
		return "pistonplating";
	}

	@Override
	public boolean applyArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		AxisAlignedBB axisalignedbb = null;
		axisalignedbb = player.boundingBox.expand(1.0D, 0.5D, 1.0D);
		List list = world.getEntitiesWithinAABBExcludingEntity(player, axisalignedbb);

		if(list != null)
		{
			for(int i = 0; i < list.size(); ++i)
			{
				Entity entity = (Entity) list.get(i);

				if(!entity.isDead && (entity instanceof EntityLiving))
				{
					entity.setVelocity(-entity.motionX - 0.3F, 0.3F, -entity.motionZ - 0.3F);
					return true;
				}
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
	public ArrayList<IArmorModule> getListOfIncompatibleModules()
	{
		// ArrayList incompats = new ArrayList();
		// incompats.add(InitItems.itemAqualung);
		// return incompats;
		return null;
	}

	@Override
	public int getSteamConsumedOnEffect()
	{
		return 100;
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
		this.itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}
}
