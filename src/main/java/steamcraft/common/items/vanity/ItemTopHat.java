package steamcraft.common.items.vanity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.items.BaseItem;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.vanity.EnumVanityType;
import boilerplate.steamapi.vanity.IVanityItem;

public class ItemTopHat extends BaseItem implements IVanityItem
{
	@Override
	public ModelBase getVanityItemModel()
	{
		//return new ModelTopHat(1);
		return null;
	}

	@Override
	public ResourceLocation getVanityTextureLocation()
	{
		return new ResourceLocation(LibInfo.PREFIX + "/textures/models/vanity/tophat.png");
	}

	@Override
	public EnumVanityType getVanityItemType()
	{
		return EnumVanityType.HAT;
	}

}
