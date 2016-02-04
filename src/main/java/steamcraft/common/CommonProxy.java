
package steamcraft.common;

import java.awt.Color;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class CommonProxy
{
	public void init()
	{
	}

	public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input,
			int impact, Color rayColor)
	{
		return null;
	}

	public ModelBiped getWingsArmorModel(int id)
	{
		return null;
	}

	public ModelBiped getJetpackArmorModel(int id)
	{
		return null;
	}

	public ModelBiped getWingpackArmorModel(int id)
	{
		return null;
	}

	public boolean isKeyPressed(int id)
	{
		return true;
	}

	public boolean isScreenEmpty()
	{
		return true;
	}
}
