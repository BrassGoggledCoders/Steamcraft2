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
 * File created @ [Mar 12, 2014, 5:30:13 PM]
 */
package steamcraft.common.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

/**
 *
 * @author Surseance (Johnny Eatmon)
 */
public class FakeSteamcraftPlayer extends EntityPlayer
{
	public FakeSteamcraftPlayer(World world, GameProfile profile)
	{
		super(world, profile);
	}

	@Override
	public void addChatMessage(IChatComponent chat)
	{
	}

	@Override
	public boolean canCommandSenderUseCommand(int i, String s)
	{
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates()
	{
		return null;
	}
}
