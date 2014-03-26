package common.steamcraft.common.block.machines;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.tile.TileEntitySteamFurnace;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSteamFurnace extends BlockContainer //Should be: extends BlockContainerMod ?
{
	private Random furnaceRand;
	private final boolean isActive;
	private static boolean keepFurnaceInventory = false;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconTop;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconFront;

	protected BlockSteamFurnace(int id, boolean flag)
	{
		super(id, Material.iron);
		this.furnaceRand = new Random();
		this.isActive = flag;
		this.setHardness(4F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("steamfurnace");

		if(flag)
		{
			this.setLightValue(0.875F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j)
	{	
		return i == 1 ? this.furnaceIconTop : (i == 0 ? this.blockIcon : (i != j ? this.blockIcon : this.furnaceIconFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + "steamfurnaceside");
		this.furnaceIconFront = icon.registerIcon(this.isActive ? LibInfo.SC2_PREFIX + "steamfurnacefronton" : LibInfo.SC2_PREFIX + "steamfurnacefrontoff");
		this.furnaceIconTop = icon.registerIcon(LibInfo.SC2_PREFIX + "steamfurnacetop");
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ModMachines.steamOvenIdle.blockID;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		super.onBlockAdded(world, i, j, k);
		this.setDefaultDirection(world, i, j, k);
	}

	private void setDefaultDirection(World world, int i, int j, int k)
	{
		if(!world.isRemote)
		{
			int l = world.getBlockId(i, j, k - 1);
			int i1 = world.getBlockId(i, j, k + 1);
			int j1 = world.getBlockId(i - 1, j, k);
			int k1 = world.getBlockId(i + 1, j, k);
			byte b0 = 3;

			if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}
			if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}
			if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}
			if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(i, j, k, b0, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		if(!isActive)
		{
			return;
		}

		TileEntitySteamFurnace furnace = (TileEntitySteamFurnace) world.getBlockTileEntity(i, j, k);
		int meta = world.getBlockMetadata(i, j, k);
		float f = (float)i + 0.5F;
		float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 16F;
		float f2 = (float)k + 0.5F;
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;

		if(meta == 4)
		{
			world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if(meta == 5)
		{
			world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if(meta == 2)
		{
			world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
		} else if(meta == 3)
		{
			world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
		}
		if(furnace.getWater() > 0)
		{
			world.spawnParticle("smoke", f, f1 + 1, f2, 0.0D, 0.1D, 0.0D);
			world.spawnParticle("smoke", f, f1 + 1, f2, 0.0D, 0.1D, 0.0D);
		}
	}

	public static void playSound(World world, int i, int j, int k, String s)
	{
		world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, s, 0.5F, 2.6F * 0.8F);
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int l, float f1, float f2, float f3)
	{
		if(world.isRemote)
		{
			return true;
		}
		
		TileEntitySteamFurnace furnace = (TileEntitySteamFurnace) world.getBlockTileEntity(i, j, k);
		
		if(furnace != null)
		{
			player.openGui(SC2.instance, GuiIDs.GUI_ID_STEAM_OVEN, world, i, j, k);
		}
		
		return true;
		
		/*
		TileEntitySteamFurnace furnace = (TileEntitySteamFurnace)world.getBlockTileEntity(i, j, k);
		
		if(world.isRemote && furnace != null)
		{
			Random random = new Random();
			int randomInt1 = random.nextInt();
			int randomInt2 = random.nextInt();
			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
			DataOutputStream outputStream = new DataOutputStream(bos);

			try {
				outputStream.writeInt(randomInt1);
				outputStream.writeInt(randomInt2);
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = SC2_Info.NETWORK_CHANNEL;
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			Side side = FMLCommonHandler.instance().getEffectiveSide();

			if(side == Side.SERVER) 
			{
				EntityPlayerMP player = (EntityPlayerMP) playerEntity;
				player.openGui(SC2.instance, SC2_GuiIDs.GUI_ID_SteamOven, world, i, j, k);
				return true;
			} else if(side == Side.CLIENT) 
			{
				EntityClientPlayerMP player = (EntityClientPlayerMP) playerEntity;
				player.openGui(SC2.instance, SC2_GuiIDs.GUI_ID_SteamOven, world, i, j, k);
				player.sendQueue.addToSendQueue(packet);
				return true;
			} else 
			{
				// We are on the Bukkit server
				return false;
			}
		}*/
	}

	public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k)
	{
		int meta = world.getBlockMetadata(i, j, k);
		TileEntity tile = world.getBlockTileEntity(i, j, k);
		keepFurnaceInventory = true;

		if(flag)
		{
			world.setBlock(i, j, k, ModMachines.steamOvenActive.blockID);
		} else
		{
			world.setBlock(i, j, k, ModMachines.steamOvenIdle.blockID);
		}

		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(i, j, k, meta, 2);

		if(tile != null)
		{
			tile.validate();
			world.setBlockTileEntity(i, j, k, tile);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySteamFurnace();
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack stack)
	{
		int l = MathHelper.floor_double((double)((living.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		if(l == 0)
		{
			world.setBlockMetadataWithNotify(i, j, k, 2, 2);
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(i, j, k, 5, 2);
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, 3, 2);
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(i, j, k, 4, 2);
		}
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int l, int m)
	{
		if(!keepFurnaceInventory)
		{
			TileEntitySteamFurnace furnace = (TileEntitySteamFurnace)world.getBlockTileEntity(i, j, k);

			if(furnace != null)
			{
				label0:
					for(int size = 0; size < furnace.getSizeInventory(); size++)
					{
						ItemStack stack = furnace.getStackInSlot(size);

						if(stack == null)
						{
							continue;
						}

						float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
						float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
						float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;

						do
						{
							if(stack.stackSize <= 0)
							{
								continue label0;
							}

							int num = furnaceRand.nextInt(21) + 10;

							if(num > stack.stackSize)
							{
								num = stack.stackSize;
							}

							stack.stackSize -= num;
							EntityItem item = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(stack.itemID, num, stack.getItemDamage()));
							float f3 = 0.05F;
							item.motionX = (float)furnaceRand.nextGaussian() * f3;
							item.motionY = (float)furnaceRand.nextGaussian() * f3 + 0.2F;
							item.motionZ = (float)furnaceRand.nextGaussian() * f3;
							world.spawnEntityInWorld(item);
						} while(true);
					}
			}
		}
		super.breakBlock(world, i, j, k, l, m);
	}
}