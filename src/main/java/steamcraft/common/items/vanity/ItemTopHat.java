
package steamcraft.common.items.vanity;

import boilerplate.common.baseclasses.items.BaseItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import steamcraft.api.vanity.EnumVanityType;
import steamcraft.api.vanity.IVanityItem;
import steamcraft.client.renderers.models.ModelTopHat;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemTopHat extends BaseItem implements IVanityItem
{
	public ItemTopHat()
	{
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getVanityItemModel()
	{
		return new ModelTopHat();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getItemTextureLocation()
	{
		return new ResourceLocation(ModInfo.PREFIX + "/textures/models/vanity/tophat-normal.png");
	}

	@Override
	public EnumVanityType getVanityItemType()
	{
		return EnumVanityType.HAT;
	}

	@Override
	public float getModelOffsetX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getModelOffsetY()
	{
		return -0.3F;
	}

	@Override
	public float getModelOffsetZ()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
