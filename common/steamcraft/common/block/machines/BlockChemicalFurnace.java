package common.steamcraft.common.block.machines;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.tile.TileEntityChemicalFurnace;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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

public class BlockChemicalFurnace extends BlockContainerMod
{
	private Random random = new Random();
	private final boolean isActive;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconTop;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconFront;

	public BlockChemicalFurnace(int id, boolean flag)
	{
		super(id, Material.iron);
		this.isActive = flag;
		this.setHardness(4.5F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("chemfurnace");
		
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
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + "chemfurnaceside");
		this.furnaceIconFront = icon.registerIcon(this.isActive ? LibInfo.SC2_PREFIX + "chemfurnacefronton" : LibInfo.SC2_PREFIX + "chemfurnacefrontoff");
		this.furnaceIconTop = icon.registerIcon(LibInfo.SC2_PREFIX + "castironblock");
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ModMachines.chemOvenIdle.blockID;
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
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		if(!isActive)
		{
			return;
		}

		int l = world.getBlockMetadata(i, j, k);
		float f = (float)i + 0.5F;
		float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 16F;
		float f2 = (float)k + 0.5F;
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;

		if(l == 4)
		{
			world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if(l == 5)
		{
			world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if(l == 2)
		{
			world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
		} else if(l == 3)
		{
			world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
		{
			return true;
		}
		
		TileEntityChemicalFurnace furnace = (TileEntityChemicalFurnace) world.getBlockTileEntity(i, j, k);
		
		if(furnace != null)
		{
			player.openGui(SC2.instance, GuiIDs.GUI_ID_CHEM_OVEN, world, i, j, k);
		}
		
		return true;
	}

	public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k)
	{
		int l = world.getBlockMetadata(i, j, k);
		TileEntity tile = world.getBlockTileEntity(i, j, k);
		keepInventory = true;
		
		if(flag)
		{
			world.setBlock(i, j, k, ModMachines.chemOvenActive.blockID);
		} else
		{
			world.setBlock(i, j, k, ModMachines.chemOvenIdle.blockID);
		}
		
		keepInventory = false;
		world.setBlock(i, j, k, l);
		
		if(tile != null)
		{
			tile.validate();
			world.setBlockTileEntity(i, j, k, tile);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityChemicalFurnace();
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
		if(!keepInventory)
		{
			TileEntityChemicalFurnace furnace = (TileEntityChemicalFurnace) world.getBlockTileEntity(i, j, k);
			
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
						
						float f = random.nextFloat() * 0.8F + 0.1F;
						float f1 = random.nextFloat() * 0.8F + 0.1F;
						float f2 = random.nextFloat() * 0.8F + 0.1F;
						
						do
						{
							if(stack.stackSize <= 0)
							{
								continue label0;
							}
							
							int i1 = random.nextInt(21) + 10;
							
							if(i1 > stack.stackSize)
							{
								i1 = stack.stackSize;
							}
							
							stack.stackSize -= i1;
							EntityItem item = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(stack.itemID, i1, stack.getItemDamage()));
							float f3 = 0.05F;
							item.motionX = (float)random.nextGaussian() * f3;
							item.motionY = (float)random.nextGaussian() * f3 + 0.2F;
							item.motionZ = (float)random.nextGaussian() * f3;
							world.spawnEntityInWorld(item);
						} while(true);
					}
			}
		}
		super.breakBlock(world, i, j, k, l, m);
	}
}