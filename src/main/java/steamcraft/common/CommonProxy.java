/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Mar 12, 2014, 4:19:50 PM]
 */
package steamcraft.common;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonProxy.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class CommonProxy implements IGuiHandler
{
	
	/**
	 * Register display information.
	 */
	public void registerDisplayInformation()
	{
	}

	/**
	 * Register renderers.
	 */
	public void registerRenderers()
	{
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.IGuiHandler#getClientGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
	 */
	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player,
			final World world, final int x, final int y, final int z)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.IGuiHandler#getServerGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
	 */
	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player,
			final World world, final int x, final int y, final int z)
	{
		switch (id)
		// Containers
		{
		case 0:
		}

		return null;
	}

	// ====================== WARNING: CLIENT-SIDE ====================== //

	/**
	 * Gets the client world.
	 *
	 * @return the client world
	 */
	public World getClientWorld()
	{
		return null;
	}

	/**
	 * Adds the s c2 capes.
	 *
	 * @return the object
	 */
	public Object addSC2Capes()
	{
		return null;
	}

	/**
	 * Ray fx.
	 *
	 * @param world the world
	 * @param player the player
	 * @param dx the dx
	 * @param dy the dy
	 * @param dz the dz
	 * @param type the type
	 * @param reverse the reverse
	 * @param endMod the end mod
	 * @param input the input
	 * @param impact the impact
	 * @return the object
	 */
	public Object rayFX(final World world, final EntityPlayer player,
			final double dx, final double dy, final double dz, final int type,
			final boolean reverse, final float endMod, final Object input,
			final int impact)
	{
		return null;
	}

	/**
	 * Smoke fx.
	 *
	 * @param world the world
	 * @param dx the dx
	 * @param dy the dy
	 * @param dz the dz
	 * @param input the input
	 * @return the object
	 */
	public Object smokeFX(final World world, final double dx, final double dy,
			final double dz, final Object input)
	{
		return null;
	}

	/**
	 * Gets the monocle armor model.
	 *
	 * @param id the id
	 * @return the monocle armor model
	 */
	public ModelBiped getMonocleArmorModel(final int id)
	{
		return null;
	}

	/**
	 * Gets the wings armor model.
	 *
	 * @param id the id
	 * @return the wings armor model
	 */
	public ModelBiped getWingsArmorModel(final int id)
	{
		return null;
	}

	/**
	 * Gets the cape armor model.
	 *
	 * @param id the id
	 * @return the cape armor model
	 */
	public ModelBiped getCapeArmorModel(final int id)
	{
		return null;
	}
}
