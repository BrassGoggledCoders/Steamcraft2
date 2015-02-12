/**
 * This class was created by BrassGoggledCoders modding team.
 *
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
	void init()
	{
		this.registerDisplayInformation();
		this.registerRenderers();
		this.registerKeys();
	}

	public void registerDisplayInformation()
	{
	}

	public void registerRenderers()
	{
	}

	public void registerKeys()
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
