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
package steamcraft.common.items.vanity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.items.BaseItem;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.vanity.EnumVanityType;
import boilerplate.steamapi.vanity.IVanityItem;

/**
 * @author warlordjones
 * 
 */
public class ItemTopHat extends BaseItem implements IVanityItem
{
	@Override
	public ModelBase getVanityItemModel()
	{
		return new ModelBoat();
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

	@Override
	public float getModelOffsetX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getModelOffsetY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getModelOffsetZ()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
