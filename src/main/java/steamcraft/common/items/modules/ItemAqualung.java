/*
 *
 */
package steamcraft.common.items.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.items.BaseItem;
import boilerplate.steamapi.EnumApplicablePiece;
import boilerplate.steamapi.EnumArmorEffectType;
import boilerplate.steamapi.IModule;

public class ItemAqualung extends BaseItem implements IModule
{

	@Override
	public EnumApplicablePiece getApplicablePiece()
	{
		return EnumApplicablePiece.CHESTPLATE;
	}

	@Override
	public String getName()
	{
		return "Aqualung";
	}

	@Override
	public void getArmorEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.getAir() < 0)
		{
			player.setAir(300);
		}
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

}
