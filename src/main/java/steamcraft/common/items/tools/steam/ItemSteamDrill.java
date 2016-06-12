
package steamcraft.common.items.tools.steam;

import java.util.List;

import boilerplate.client.ClientHelper;
import boilerplate.common.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import steamcraft.common.lib.ModInfo;

/**
 * @author decebaldecebal
 *
 */
public class ItemSteamDrill extends ItemSteamTool
{
	public ItemSteamDrill(ToolMaterial mat)
	{
		super(1);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		super.addInformation(itemStack, entityPlayer, list, bool);

		if (!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}

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

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		float speed = super.getDigSpeed(stack, block, metadata);

		if ((speed > 1.0f) && stack.getTagCompound().getBoolean("active"))
			return speed / 3;

		return speed;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		if (itemStack.getTagCompound().getBoolean("active"))
		{
			World world = player.worldObj;

			MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, true, 5.0D);

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
				for (int xPos = x - xRange; xPos <= (x + xRange); xPos++)
					for (int yPos = y - yRange; yPos <= (y + yRange); yPos++)
						for (int zPos = z - zRange; zPos <= (z + zRange); zPos++)
						{
							Block nblock = world.getBlock(xPos, yPos, zPos);
							this.breakExtraBlock(world, xPos, yPos, zRange, mop.sideHit, player, x, y, z, itemStack);
						}
		}

		return false;
	}
	//Stolen from TConstruct...
	protected void breakExtraBlock(World world, int x, int y, int z, int sidehit, EntityPlayer playerEntity, int refX, int refY, int refZ, ItemStack stack) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if (world.isAirBlock(x, y, z))
            return;

        // what?
        if(!(playerEntity instanceof EntityPlayerMP))
            return;
        EntityPlayerMP player = (EntityPlayerMP) playerEntity;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        // only effective materials
        if (!this.canHarvestBlock(block, stack))
            return;

        Block refBlock = world.getBlock(refX, refY, refZ);
        float refStrength = ForgeHooks.blockStrength(refBlock, player, world, refX, refY, refZ);
        float strength = ForgeHooks.blockStrength(block, player, world, x,y,z);

        // only harvestable blocks that aren't impossibly slow to harvest
        if (!ForgeHooks.canHarvestBlock(block, player, meta) || refStrength/strength > 10f)
            return;

        // send the blockbreak event
        BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, player.theItemInWorldManager.getGameType(), player, x,y,z);
        if(event.isCanceled())
            return;

        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);

            // send update to client
            if (!world.isRemote) {
                player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            }
            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        player.getCurrentEquippedItem().func_150999_a(world, block, x, y, z, player);

        // server sided handling
        if (!world.isRemote) {
            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, x,y,z, meta, player);

            if(block.removedByPlayer(world, player, x,y,z, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer( world, x,y,z, meta);
                block.harvestBlock(world, player, x,y,z, meta);
                block.dropXpOnBlockBreak(world, x,y,z, event.getExpToDrop());
            }

            // always send block update to client
            player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        }
        // client sided handling
        else {
            //PlayerControllerMP pcmp = Minecraft.getMinecraft().playerController;
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if(block.removedByPlayer(world, player, x,y,z, true))
            {
                block.onBlockDestroyedByPlayer(world, x,y,z, meta);
            }
            
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x,y,z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block p_150894_3_, int x, int y, int z, EntityLivingBase living)
	{
		if (living instanceof EntityPlayer)
		{
			this.consumeSteamFromCanister((EntityPlayer) living);
			world.playSoundAtEntity(living, ModInfo.PREFIX + "drill.steam", 0.6F, 1.0F);
			world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, Item.itemRand.nextGaussian(), Item.itemRand.nextGaussian(),
					Item.itemRand.nextGaussian());
			return true;
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player.isSneaking())
		{
			NBTTagCompound tag = itemStack.getTagCompound();

			if (tag.getBoolean("active"))
				tag.setBoolean("active", false);
			else if (tag.getBoolean("hasCanister"))
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

		if (tag.getBoolean("active") && !tag.getBoolean("hasCanister"))
		{
			tag.setBoolean("active", false);
			itemStack.setTagCompound(tag);
		}
	}

	@Override
	public int getItemEnchantability()
	{
		return 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
	{
		return false;
	}
}