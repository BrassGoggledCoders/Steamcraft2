package boilerplate.common;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Utils {
	public static void sparkle(World world, int x, int y, int z, String particleName)
	{
		Random random = world.rand;
		double offset = 0.0625D;

		for (int amount = 0; amount < 6; amount++)
		{
			double dx = x + random.nextFloat();
			double dy = y + random.nextFloat();
			double dz = z + random.nextFloat();

			if ((amount == 0) && (!world.isBlockNormalCubeDefault(x, y + 1, z, false)))
			{
				dy = y + 1 + offset;
			}
			
			if ((amount == 1) && (!world.isBlockNormalCubeDefault(x, y - 1, z, false)))
			{
				dy = y + 0 - offset;
			}
			
			if ((amount == 2) && (!world.isBlockNormalCubeDefault(x, y, z + 1, false)))
			{
				dz = z + 1 + offset;
			}
			
			if ((amount == 3) && (!world.isBlockNormalCubeDefault(x, y, z - 1, false)))
			{
				dz = z + 0 - offset;
			}
			
			if ((amount == 4) && (!world.isBlockNormalCubeDefault(x + 1, y, z, false)))
			{
				dx = x + 1 + offset;
			}
			
			if ((amount == 5) && (!world.isBlockNormalCubeDefault(x - 1, y, z, false)))
			{
				dx = x + 0 - offset;
			}
			
			if ((dx < x) || (dx > x + 1) || (dy < 0.0D) || (dy > y + 1) || (dz < z) || (dz > z + 1))
			{
				world.spawnParticle(particleName, dx, dy, dz, -1.0D, 1.0D, -1.0D);
				//Steamcraft.proxy.smokeFX(world, dx, dy, dz, FXSmoke.class);
			}
		}
	}
	
	public static Material getBlockMaterial(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z) != null)
			return world.getBlock(x, y, z).getMaterial();
		
		return Material.air;
	}
	public static void sendToPlayers(Packet packet, World world, int x, int y, int z, Integer maxDistance) 
	{
		if (maxDistance == null) 
		{
			maxDistance = Integer.valueOf(128);
		}

		Iterator<?> iterator;

		if (packet != null)
		{
			for (iterator = world.playerEntities.iterator(); iterator.hasNext();) 
			{ 
				Object player = iterator.next();
				EntityPlayerMP playerMP = (EntityPlayerMP)player;

				if ((Math.abs(playerMP.posX - x) <= maxDistance.intValue()) && (Math.abs(playerMP.posY - y) <= maxDistance.intValue()) && (Math.abs(playerMP.posZ - z) <= maxDistance.intValue())) 
				{
					playerMP.playerNetServerHandler.sendPacket(packet);
				}	
			}
		}
	}
}
