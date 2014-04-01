package common.steamcraft.common.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import common.steamcraft.client.core.helper.ClientHelper;
import common.steamcraft.common.core.helper.CommonHelper;
import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSteamDrill extends ItemMod
{
	private Random random = new Random();
	public static final Block[] blocksEffectiveAgainst = new Block[] {
		Block.cobblestone, Block.dirt, Block.stone, Block.sand, Block.blockClay, Block.ice,
		Block.snow, Block.netherrack, Block.grass
	}; 
	protected EnumToolMaterial toolMaterial;

	protected ItemSteamDrill(int id)
	{
		super(id);
		this.setMaxDamage(321);
	}
	
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) 
	{
		if(!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}
		
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		if(itemStack.getTagCompound().getBoolean("active"))
		{
			list.add("\u00A7c"+ "Active");
			list.add("\u00A77"+ "Will try to mine a 3x3 area if");
			list.add("\u00A77"+ "you have Steam Canisters in your inventory.");
		}
		else
		{
			list.add("\u00A7c"+ "Inactive");
			list.add("\u00A77"+ "Sneak + Right Click to activate the drill.");
			list.add("\u00A77"+ "While active, it will try to mine a 3x3 area");
			list.add("\u00A77"+ "if you have Steam Canisters in your inventory.");
		}
	}	
	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		if(itemStack.getTagCompound().getBoolean("active") && this.searchForCanister(player))
		{
			World world = player.worldObj;
			
			MovingObjectPosition mop = CommonHelper.raytraceFromEntity(world, player, true, 5.0D);
			
	        if (mop == null)
	            return super.onBlockStartBreak(itemStack, x, y, z, player);
	        
	        int xRange = 1;
	        int yRange = 1;
	        int zRange = 1;
	        switch (mop.sideHit)
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
	        final int blockID = world.getBlockId(x, y, z);
	        Block block = Block.blocksList[blockID];
	        if(block!=null && block.getBlockHardness(world, x, y, z)!=0 && this.canHarvestBlock(block))
	        {
		        for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
		            for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
		                for (int zPos = z - zRange; zPos <= z + zRange; zPos++)
		                {
		                	int localblockID = world.getBlockId(xPos, yPos, zPos);
		                	Block nblock = Block.blocksList[localblockID];
		                	
		                	if(block==nblock)
		                	{
		                		int meta = world.getBlockMetadata(xPos, yPos, zPos);
		                		
		                		ItemStack result = new ItemStack(nblock.idDropped(meta, random, 0), nblock.quantityDropped(meta, 0, random), nblock.damageDropped(meta));

		                		if ((double)nblock.getBlockHardness(world, xPos, yPos, zPos) != 0.0D)
		                			itemStack.damageItem(1, player);
		                					                		
		                		if(!world.isRemote && result!=null)
		                		{
			                		world.setBlockToAir(xPos, yPos, zPos);
		                			world.spawnEntityInWorld(new EntityItem(world, xPos+0.5, yPos+0.5, zPos+0.5, result.copy()));
		                		}
		                		
		                		world.playAuxSFX(2001, xPos, yPos, zPos, blockID + (meta << 12));
		                	}
		                }
	        }
		}
		return false;
	}
	
	private boolean searchForCanister(EntityPlayer player) 
	{
		if(!player.worldObj.isRemote)
		{
			if(player.inventory.hasItem(ModItems.canisterSteam.itemID))
			{
				int steamPerUse = 7;
				int i = 0, j=0;

				while(i < 36 && j<steamPerUse)
				{
					if(player.inventory.mainInventory[i]!=null &&
							player.inventory.mainInventory[i].itemID == ModItems.canisterSteam.itemID)
					{
						while(player.inventory.mainInventory[i].getItemDamage() < ModItems.canisterSteam.getMaxDamage() && j<steamPerUse)
						{
							player.inventory.mainInventory[i].damageItem(1, player);
							j++;
						}
					}
					
					i++;
				}
				
				if(j==steamPerUse)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int i, int j, int k, int l, EntityLivingBase living)
    {
		stack.damageItem(1, living);
		world.playSoundAtEntity((EntityPlayer)living, LibInfo.SC2_PREFIX + "drill", 1.0F, 1.0F);
		world.spawnParticle("smoke", i + 0.5, j + 0.5, k + 0.5, random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
        return true;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{		
		if(player.isSneaking())
		{
			if(!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());
			
			NBTTagCompound tag = itemStack.getTagCompound();
			if(tag.getBoolean("active"))
				tag.setBoolean("active", false);
			else
				tag.setBoolean("active", true);
			itemStack.setTagCompound(tag);
				
		}
		else
			if(!world.isRemote)
			{
				if(player.inventory.hasItem(ModItems.canisterSteam.itemID))
				{
					int i = 0;

					while(itemStack.getItemDamage() != 0 && i < 36)
					{
						if(player.inventory.mainInventory[i]!=null &&
								player.inventory.mainInventory[i].itemID == ModItems.canisterSteam.itemID)
						{
							while(player.inventory.mainInventory[i].getItemDamage() < ModItems.canisterSteam.getMaxDamage() && itemStack.getItemDamage() > 0)
							{
								player.inventory.mainInventory[i].damageItem(1, player);
								itemStack.setItemDamage(itemStack.getItemDamage() - 1);
							}
						}
						
						i++;
					}
				}
			}
		return itemStack;
	}

	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block) 
	{
		for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) 
		{
            if (this.blocksEffectiveAgainst[i] == block)
            {
                return true;
            }
        } 
		
		return false;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player) 
	{
		stack.damageItem(2, player);
		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 2);
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{	
		if(this.canHarvestBlock(block))
			return 8.0F;
		return 1.0F;
	}
}