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
package steamcraft.common.items.tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.PlayerUtils;

/**
 * @author decebaldecebal
 * 
 */
public class ItemSteamDrill extends ItemDrill
{
	public ItemSteamDrill(ToolMaterial mat)
	{
		super(mat);
		setHarvestLevel("pickaxe", mat.getHarvestLevel());
		setHarvestLevel("shovel", mat.getHarvestLevel());
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		super.addInformation(itemStack, entityPlayer, list, bool);

		if(!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}

		if(itemStack.getTagCompound().getBoolean("active"))
		{
			list.add("\u00A7c" + "Active");
			list.add("\u00A77" + "Will try to mine a 3x3 area if");
			list.add("\u00A77" + "you have Steam Canisters in your inventory.");
		}
		else
		{
			list.add("\u00A7c" + "Inactive");
			list.add("\u00A77" + "Sneak + Right Click to activate the drill.");
			list.add("\u00A77" + "While active, it will try to mine a 3x3 area");
			list.add("\u00A77" + "if you have Steam Canisters in your inventory.");
		}
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		float speed = super.getDigSpeed(stack, block, metadata);

		if((speed > 1.0f) && stack.getTagCompound().getBoolean("active"))
			return speed / 3;

		return speed;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		if(itemStack.getTagCompound().getBoolean("active"))
		{
			World world = player.worldObj;

			MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, true, 5.0D);

			if(mop == null)
				return super.onBlockStartBreak(itemStack, x, y, z, player);

			int xRange = 1;
			int yRange = 1;
			int zRange = 1;
			switch(mop.sideHit)
			{
				case 0:
				case 1:
					yRange = 0;
					break;

				case 2:
				case 3:
					zRange = 0;
					break;

				case 4:
				case 5:
					xRange = 0;
					break;
			}
			Block block = world.getBlock(x, y, z);
			if((block != null) && (block.getBlockHardness(world, x, y, z) != 0) && this.canHarvestBlock(block, itemStack))
				for(int xPos = x - xRange; xPos <= (x + xRange); xPos++)
					for(int yPos = y - yRange; yPos <= (y + yRange); yPos++)
						for(int zPos = z - zRange; zPos <= (z + zRange); zPos++)
						{
							Block nblock = world.getBlock(xPos, yPos, zPos);

							if(block == nblock)
							{
								int meta = world.getBlockMetadata(xPos, yPos, zPos);

								ItemStack result = new ItemStack(nblock.getItemDropped(meta, this.itemRand, 0), nblock.quantityDropped(meta, 0,
										this.itemRand), nblock.damageDropped(meta));

								if(nblock.getBlockHardness(world, xPos, yPos, zPos) != 0.0D)
									this.consumeSteamFromCanister(player);

								if(!world.isRemote && (result != null))
								{
									world.setBlockToAir(xPos, yPos, zPos);
									world.spawnEntityInWorld(new EntityItem(world, xPos + 0.5, yPos + 0.5, zPos + 0.5, result.copy()));
								}
							}
						}
		}

		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block p_150894_3_, int x, int y, int z, EntityLivingBase living)
	{
		if(living instanceof EntityPlayer)
		{
			this.consumeSteamFromCanister((EntityPlayer) living);

			stack.damageItem(1, living);
			world.playSoundAtEntity(living, LibInfo.PREFIX + "drill.steam", 0.6F, 1.0F);
			world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, this.itemRand.nextGaussian(), this.itemRand.nextGaussian(),
					this.itemRand.nextGaussian());
			return true;
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(player.isSneaking())
		{
			NBTTagCompound tag = itemStack.getTagCompound();

			if(tag.getBoolean("active"))
				tag.setBoolean("active", false);
			else if(tag.getBoolean("hasCanister"))
				tag.setBoolean("active", true);

			itemStack.setTagCompound(tag);
		}
		return itemStack;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		super.onUpdate(itemStack, par2World, par3Entity, par4, par5);

		NBTTagCompound tag = itemStack.getTagCompound();

		if(tag.getBoolean("active") && !tag.getBoolean("hasCanister"))
		{
			tag.setBoolean("active", false);
			itemStack.setTagCompound(tag);
		}
	}
}