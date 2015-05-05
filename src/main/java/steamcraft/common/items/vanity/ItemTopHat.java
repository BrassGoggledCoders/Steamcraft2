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
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.api.vanity.EnumVanityType;
import steamcraft.api.vanity.IVanityItem;
import steamcraft.client.renderers.models.ModelTopHat;
import steamcraft.common.items.BaseItem;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 * 
 */
public class ItemTopHat extends BaseItem implements IVanityItem
{
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
		return new ResourceLocation(ModInfo.PREFIX + "/textures/blocks/metal/blockCastIron.png");
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
