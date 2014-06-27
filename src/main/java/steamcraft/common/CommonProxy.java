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

import java.awt.Color;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cpw.mods.fml.common.network.IGuiHandler#getClientGuiElement(int,
	 * net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int,
	 * int, int)
	 */
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cpw.mods.fml.common.network.IGuiHandler#getServerGuiElement(int,
	 * net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int,
	 * int, int)
	 */
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
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
	 * @param world
	 *            the world
	 * @param player
	 *            the player
	 * @param dx
	 *            the dx
	 * @param dy
	 *            the dy
	 * @param dz
	 *            the dz
	 * @param type
	 *            the type
	 * @param reverse
	 *            the reverse
	 * @param endMod
	 *            the end mod
	 * @param input
	 *            the input
	 * @param impact
	 *            the impact
	 * @param rayColor
	 *            Color of Raygun beam
	 * @return the object
	 */
	public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input,
			int impact, Color rayColor)
	{
		return null;
	}

	/**
	 * Smoke fx.
	 * 
	 * @param world
	 *            the world
	 * @param dx
	 *            the dx
	 * @param dy
	 *            the dy
	 * @param dz
	 *            the dz
	 * @param input
	 *            the input
	 * @return the object
	 */
	public Object smokeFX(World world, double dx, double dy, double dz, Object input)
	{
		return null;
	}

	/**
	 * Gets the monocle armor model.
	 * 
	 * @param id
	 *            the id
	 * @return the monocle armor model
	 */
	public ModelBiped getMonocleArmorModel(int id)
	{
		return null;
	}

	/**
	 * Gets the wings armor model.
	 * 
	 * @param id
	 *            the id
	 * @return the wings armor model
	 */
	public ModelBiped getWingsArmorModel(int id)
	{
		return null;
	}

	/**
	 * Gets the wings armor model.
	 * 
	 * @param id
	 *            the id
	 * @return the wings armor model
	 */
	public ModelBiped getJetpackArmorModel(int id)
	{
		return null;
	}

	/**
	 * Gets the wings armor model.
	 * 
	 * @param id
	 *            the id
	 * @return the wings armor model
	 */
	public ModelBiped getWingpackArmorModel(int id)
	{
		return null;
	}

	/**
	 * Gets the cape armor model.
	 * 
	 * @param id
	 *            the id
	 * @return the cape armor model
	 */
	public ModelBiped getCapeArmorModel(int id)
	{
		return null;
	}

	/**
	 * Register keys.
	 */
	public void registerKeys()
	{

	}
}
