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

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class CommonProxy implements IGuiHandler
{
	public void registerDisplayInformation() {}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (id) // Containers
		{
		case 0:
		} 
		
		return null;
	}
	
	public World getClientWorld()
	{
		return null;
	}

    public Object addSC2Capes()
    {
        return null;
    }

    public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input, int impact)
    {
        return null;
    }

    public Object smokeFX(World world, double dx, double dy, double dz, Object input)
    {
        return null;
    }
    
    public ModelBiped getMonocleArmorModel(int id) 
    {
		return null;
	}

	public ModelBiped getWingsArmorModel(int id) 
	{
		return null;
	}

	public ModelBiped getCapeArmorModel(int id) 
	{
		return null;
	}
}
