/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 6:25:21 PM]
 */
package common.steamcraft.common.block.tile;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import common.steamcraft.common.block.BlockChimney;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
public class TileEntityChimney extends TileEntity
{
	public static Random random;

	public TileEntityChimney()
	{
		this.random = new Random();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	public void updateEntity()
	{
		if(random.nextInt(6) == 0)
		{
			this.smoke(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.random);
		}
	}

	@SideOnly(Side.CLIENT)
	private void smoke(World world, int i, int j, int k)
	{
		for(int num = 0; num < 12; num++)
		{
			float smI = (float) i + random.nextFloat() * 0.4F + 0.2F;
			float smJ = (float) j + 0.4F + random.nextFloat() * 0.6F;
			float smK = (float) k + random.nextFloat() * 0.4F + 0.2F;

			Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(world, smI, smJ, smK, 0, 0, 0, 2.0F));
		}

	}

	// TODO: Need to add all types of furnaces
	private boolean isBlockChimney(int id)
	{
		return (Block.blocksList[id] instanceof BlockChimney);
	}

	private boolean isBlockFurnace(int id)
	{
		return (id == 61 || id == 62);
	}

	private boolean isBlockFurnaceActive(int id)
	{
		return id == 62;
	}

	// Extra pain-in-the-ass method:
	private void smoke(World world, int i, int j, int k, Random random)
	{
		if(world.isAirBlock(i, j + 1, k))
		{	    
			if((world.getBlockMetadata(i, j, k) & 8) != 0)
				smoke(world,i,j,k);

			int sources = 0;

			for(int y = j; y > 1; y--)
			{
				if(isBlockFurnaceActive(world.getBlockId(i, y, k)))
				{ 
					sources++;
				} else if(this.isBlockChimney(world.getBlockId(i, y, k)))
				{
					if((world.getBlockMetadata(i, y, k) & 8) != 0)
						smoke(world,i,j,k);
					
					// CHIMNEY BELOW: only furnaces connected by side (or connected by chimney or connected by other furnace)
					sources += (world.getBlockId(i - 1, y, k - 1) == 62 && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i - 1, y, k)) || isBlockFurnace(world.getBlockId(i, y, k - 1)) || isBlockFurnace(world.getBlockId(i - 1, y, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y, k - 1) == 62 )?1:0;
					sources += (world.getBlockId(i + 1, y, k - 1) == 62  && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i + 1, y, k)) || isBlockFurnace(world.getBlockId(i, y, k - 1)) || isBlockFurnace(world.getBlockId(i + 1, y, k)))) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y, k) == 62 ) ? 1 : 0;
					sources += (world.getBlockId(i, y, k) == 62 ) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y, k) == 62 ) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y, k + 1) == 62  && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i - 1, y, k)) ||  isBlockFurnace(world.getBlockId(i, y, k + 1)) || isBlockFurnace(world.getBlockId(i - 1, y, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y, k + 1) == 62 ) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y, k + 1) == 62  && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i + 1, y, k)))) ? 1 : 0;
				} else if(world.getBlockId(i, y, k) == 51)
				{
					// fire check
					sources++;
				} else if(world.getBlockId(i, y, k) == 0)
				{
					// AIR BELOW: checks fires connected by side or below connected by side
					sources += (world.getBlockId(i - 1, y, k - 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i - 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y, k - 1) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y, k - 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i + 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i, y, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y, k + 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i - 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y, k + 1) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y, k + 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i + 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y - 1, k - 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i - 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y - 1, k - 1) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y - 1, k - 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k - 1)) || this.isBlockChimney(world.getBlockId(i + 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y - 1, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i, y - 1, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y - 1, k) == 51) ? 1 : 0;
					sources += (world.getBlockId(i - 1, y - 1, k + 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i - 1, y + 1, k)))) ? 1 : 0;
					sources += (world.getBlockId(i, y - 1, k + 1) == 51) ? 1 : 0;
					sources += (world.getBlockId(i + 1, y - 1, k + 1) == 51 && (this.isBlockChimney(world.getBlockId(i, y, k + 1)) || this.isBlockChimney(world.getBlockId(i + 1, y + 1, k)))) ? 1 : 0;

					if(sources == 0)
						break;
				} else
					break;

				if(sources > 0)
				{
					this.smoke(world,i,j,k);
					break;
				}
			}
		}
	}
}