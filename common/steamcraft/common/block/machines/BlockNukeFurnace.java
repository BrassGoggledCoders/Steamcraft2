package common.steamcraft.common.block.machines;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.tile.TileEntityNukeFurnace;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNukeFurnace extends BlockContainerMod
{
	private Random furnaceRand;
	private final boolean isActive;
	private static boolean keepFurnaceInventory = false;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconTop;
	@SideOnly(Side.CLIENT)
	private Icon furnaceIconFront;

	protected BlockNukeFurnace(int id, boolean flag)
	{
		super(id, Material.iron);
		this.furnaceRand = new Random();
		this.isActive = flag;
		this.setHardness(5F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("nukefurnace");
		
		if(flag)
		{
			this.setLightValue(0.9375F);
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
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + "nukefurnaceside");
		this.furnaceIconFront = icon.registerIcon(this.isActive ? LibInfo.SC2_PREFIX + "nukefurnacefronton" : LibInfo.SC2_PREFIX + "nukefurnacefrontoff");
		this.furnaceIconTop = icon.registerIcon(LibInfo.SC2_PREFIX + "nukefurnacetop");
	}

	@Override
	public int tickRate(World world)
	{
		return 40;
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ModMachines.nukeOvenIdle.blockID;
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

		int l = world.getBlockMetadata(i, j, k);
		float f = (float)i + 0.5F;
		float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 16F;
		float f2 = (float)k + 0.5F;
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;

		if(l == 4)
		{
			world.spawnParticle("reddust", f - f3, f1, f2 + f4, -1.0D, 1.0D, 0.0D);
		} else if(l == 5)
		{
			world.spawnParticle("reddust", f + f3, f1, f2 + f4, -1.0D, 1.0D, 0.0D);
		} else if(l == 2)
		{
			world.spawnParticle("reddust", f + f4, f1, f2 - f3, -1.0D, 1.0D, 0.0D);
		} else if(l == 3)
		{
			world.spawnParticle("reddust", f + f4, f1, f2 + f3, -1.0D, 1.0D, 0.0D);
		}
		
		world.spawnParticle("reddust", f, f1+0.6F, f2, 0.0D, 0.0D, 1.0D);
		world.spawnParticle("reddust", f, f1+0.6F, f2, 0.0D, 0.0D, 1.0D);
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int l, float f1, float f2, float f3)
	{
		if(world.isRemote)
		{
			return true;
		}
		
		TileEntityNukeFurnace furnace = (TileEntityNukeFurnace) world.getBlockTileEntity(i, j, k);
		
		if(furnace != null)
		{
			player.openGui(SC2.instance, GuiIDs.GUI_ID_NUKE_OVEN, world, i, j, k);
		}
		
		return true;
	}

	public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k)
	{
		int meta = world.getBlockMetadata(i, j, k);
		TileEntity tile = world.getBlockTileEntity(i, j, k);
		keepFurnaceInventory = true;
		
		if(flag)
		{
			world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "mob.ghast.fireball", 1.0F, 0.8F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F);
			world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "mob.zombiepig.zpigdeath", 0.1F, 0.1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.6F);
			world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "fire.ignite", 1.5F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			world.setBlock(i, j, k, ModMachines.nukeOvenActive.blockID);
		} else
		{
			world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "ambient.cave.cave", 0.1F, 0.1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			world.setBlock(i, j, k, ModMachines.nukeOvenIdle.blockID);
		}
		
		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(i, j, k, ModMachines.nukeOvenIdle.blockID, meta);
		
		if(tile != null)
		{
			tile.validate();
			world.setBlockTileEntity(i, j, k, tile);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityNukeFurnace();
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
	
	public static void meltdown(World world, int i, int j, int k)
	{
		//world.playSoundEffect((float)i, (float)j, (float)k, "ambient.weather.thunder", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.9F);
		//ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_RuinedEverything);
		world.createExplosion(null, i, j, k, 25F, true);
		double d = (double)((float)i + 0.5F) + (double)(0.5F) * 2.0000000000000001D;
		double d1 = (double)((float)j + 0.7F) + (double)(0.5F) * 2.0000000000000001D;
		double d2 = (double)((float)k + 0.5F) + (double)(0.5F) * 2.0000000000000001D;
		world.spawnParticle("reddust", d, d1, d2, -1.0D, 1.0D, 0.0D);
	}

	public void spawnSmoke(World world, int i, int j, int k, Random random)
	{
		double d = (double)((float)i + 0.5F) + (double)(random.nextFloat() - 0.5F);
		double d1 = (double)((float)j + 0.7F) + (double)(random.nextFloat() - 0.3F);
		double d2 = (double)((float)k + 0.5F) + (double)(random.nextFloat() - 0.5F);
		world.spawnParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random)
	{
		TileEntityNukeFurnace furnace = (TileEntityNukeFurnace)world.getBlockTileEntity(i, j, k);
		int i1 = world.getBlockId(i + 1, j, k);
		int i2 = world.getBlockId(i - 1, j, k);
		int j1 = world.getBlockId(i, j + 1, k);
		int j2 = world.getBlockId(i, j - 1, k);
		int k1 = world.getBlockId(i, j, k + 1);
		int k2 = world.getBlockId(i, j, k - 1);
		
		if(furnace.getHeat() >= 1000)
		{
			if(i1 == waterStill.blockID)
			{
				furnace.addHeat(-10);
				world.setBlockMetadataWithNotify(i + 1, j, k, 0, world.getBlockMetadata(i + 1, j, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
			}
			if(i2 == waterStill.blockID)
			{
				furnace.addHeat(-10);
				world.setBlockMetadataWithNotify(i - 1, j, k, 0, world.getBlockMetadata(i - 1, j, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
			}
			if(j1 == waterStill.blockID)
			{
				furnace.addHeat(-10);
				world.setBlockMetadataWithNotify(i, j + 1, k, 0, world.getBlockMetadata(i, j + 1, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
			}
			if(k1 == waterStill.blockID)
			{
				furnace.addHeat(-10);
				world.setBlockMetadataWithNotify(i, j, k + 1, 0, world.getBlockMetadata(i, j, k + 1));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
			}
			if(k2 == waterStill.blockID)
			{
				furnace.addHeat(-10);
				world.setBlockMetadataWithNotify(i, j, k - 1, 0, world.getBlockMetadata(i, j, k - 1));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
			}
			if(i1 == waterMoving.blockID)
			{
				furnace.addHeat(-5);
				world.setBlockMetadataWithNotify(i + 1, j, k, 0, world.getBlockMetadata(i + 1, j, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
				spawnSmoke(world, i + 1, j, k, random);
			}
			if(i2 == waterMoving.blockID)
			{
				furnace.addHeat(-5);
				world.setBlockMetadataWithNotify(i - 1, j, k, 0, world.getBlockMetadata(i - 1, j, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
				spawnSmoke(world, i - 1, j, k, random);
			}
			if(j1 == waterMoving.blockID)
			{
				furnace.addHeat(-5);
				world.setBlockMetadataWithNotify(i, j + 1, k, 0, world.getBlockMetadata(i, j + 1, k));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
				spawnSmoke(world, i, j + 1, k, random);
			}
			if(k1 == waterMoving.blockID)
			{
				furnace.addHeat(-5);
				world.setBlockMetadataWithNotify(i, j, k + 1, 0, world.getBlockMetadata(i, j, k + 1));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
				spawnSmoke(world, i, j, k + 1, random);
			}
			if(k2 == waterMoving.blockID)
			{
				furnace.addHeat(-5);
				world.setBlockMetadataWithNotify(i, j, k - 1, 0, world.getBlockMetadata(i, j, k - 1));
				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
				spawnSmoke(world, i, j, k - 1, random);
			}
		}
		if(i1 == lavaStill.blockID || i1 == lavaMoving.blockID || i2 == lavaStill.blockID || i2 == lavaMoving.blockID || j1 == lavaStill.blockID || j1 == lavaMoving.blockID || j2 == lavaStill.blockID || j2 == lavaMoving.blockID || k1 == lavaStill.blockID || k1 == lavaMoving.blockID || k2 == lavaStill.blockID || k2 == lavaMoving.blockID)
		{
			furnace.addHeat(160);
		}
		if(i1 == fire.blockID || i2 == fire.blockID || j2 == fire.blockID || k1 == fire.blockID || k2 == fire.blockID)
		{
			furnace.addHeat(80);
		}
		
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
	{

		super.onNeighborBlockChange(world, i, j, k, l);
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int l, int m)
	{
		if(!keepFurnaceInventory)
		{
			TileEntityNukeFurnace furnace = (TileEntityNukeFurnace)world.getBlockTileEntity(i, j, k);
		
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
							
							int i1 = furnaceRand.nextInt(21) + 10;
							
							if(i1 > stack.stackSize)
							{
								i1 = stack.stackSize;
							}
							
							stack.stackSize -= i1;
							EntityItem item = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(stack.itemID, i1, stack.getItemDamage()));
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
	
	@Override
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int i, int j, int k, int l)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getBlockTileEntity(i, j, k));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return ModMachines.nukeOvenIdle.blockID;
    }
}