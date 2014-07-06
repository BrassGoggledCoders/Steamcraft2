/*
 * 
 */
package steamcraft.common.items.modules;

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
	public String getStringForHashMap()
	{
		return "aqualung";
	}

	@Override
	public void getArmorEffect()
	{
		// TODO
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

}
