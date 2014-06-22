/*
 *
 */
package steamcraft.common.items.equipment;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.PlayerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemSteamDrill.
 */
public class ItemSteamDrill extends ItemDrill
{

	/** The random. */
	private Random random = new Random();

	/** The blocks effective against. */
	public static Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.dirt, Blocks.stone, Blocks.sand, Blocks.clay, Blocks.ice, Blocks.snow, Blocks.netherrack, Blocks.grass };

	/**
	 * Instantiates a new item steam drill.
	 *
	 * @param mat
	 *            the mat
	 */
	public ItemSteamDrill(ToolMaterial mat)
	{
		super(mat);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemDrill#addInformation(net.minecraft
	 * .item.ItemStack, net.minecraft.entity.player.EntityPlayer,
	 * java.util.List, boolean)
	 */
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}

		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());

		if (itemStack.getTagCompound().getBoolean("active"))
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#onBlockStartBreak(net.minecraft.item.ItemStack,
	 * int, int, int, net.minecraft.entity.player.EntityPlayer)
	 */
	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());

		if (itemStack.getTagCompound().getBoolean("active"))
		{
			World world = player.worldObj;

			MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, false);

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
			Block block = world.getBlock(x, y, z);
			if ((block != null) && (block.getBlockHardness(world, x, y, z) != 0) && this.canHarvestBlock(block, itemStack))
			{
				for (int xPos = x - xRange; xPos <= (x + xRange); xPos++)
					for (int yPos = y - yRange; yPos <= (y + yRange); yPos++)
						for (int zPos = z - zRange; zPos <= (z + zRange); zPos++)
						{
							Block nblock = world.getBlock(xPos, yPos, zPos);

							if (block == nblock)
							{
								int meta = world.getBlockMetadata(xPos, yPos, zPos);

								ItemStack result = new ItemStack(nblock.getItemDropped(meta, this.random, 0), nblock.quantityDropped(meta, 0,
										this.random), nblock.damageDropped(meta));

								if (nblock.getBlockHardness(world, xPos, yPos, zPos) != 0.0D)
									itemStack.damageItem(1, player);

								if (!world.isRemote && (result != null))
								{
									world.setBlockToAir(xPos, yPos, zPos);
									world.spawnEntityInWorld(new EntityItem(world, xPos + 0.5, yPos + 0.5, zPos + 0.5, result.copy()));
								}
								// Broke
								// world.playAuxSFX(2001, xPos, yPos, zPos,
								// block.getIdFromBlock(block) + (meta << 12));
							}
						}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemDrill#onBlockDestroyed(net.minecraft
	 * .item.ItemStack, net.minecraft.world.World, net.minecraft.block.Block,
	 * int, int, int, net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block p_150894_3_, int x, int y, int z, EntityLivingBase living)
	{
		 if(living instanceof EntityPlayer)
		 {
			 for(int i=0; i<5; i++)
			 consumeSteamFromCanister((EntityPlayer) living);
			 stack.damageItem(1, living);
			 world.playSoundAtEntity(living, LibInfo.PREFIX + "drill", 1.0F, 1.0F);
			 world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, this.random.nextGaussian(), this.random.nextGaussian(), this.random.nextGaussian());
			 return true;
		 }
		 else return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer)
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player.isSneaking())
		{
			if (!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = itemStack.getTagCompound();
			if (tag.getBoolean("active"))
				tag.setBoolean("active", false);
			else
				tag.setBoolean("active", true);
			itemStack.setTagCompound(tag);

		}
		return itemStack;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemDrill#canHarvestBlock(net.minecraft
	 * .block.Block, net.minecraft.item.ItemStack)
	 */
	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		for (Block element : ItemSteamDrill.blocksEffectiveAgainst)
		{
			if (element == block)
			{
				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemDrill#hitEntity(net.minecraft.item
	 * .ItemStack, net.minecraft.entity.EntityLivingBase,
	 * net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player)
	{
		stack.damageItem(2, player);
		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 2);
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemDrill#getDigSpeed(net.minecraft
	 * .item.ItemStack, net.minecraft.block.Block, int)
	 */
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (this.canHarvestBlock(block, stack))
			return 8.0F;
		return 1.0F;
	}
}