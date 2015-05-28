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
package steamcraft.common.lib.events;

import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import cofh.api.energy.IEnergyHandler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import steamcraft.api.vanity.IVanityItem;
import steamcraft.client.ClientProxy;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import steamcraft.common.init.InitPackets;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.packets.OpenContainerFromClientPacket;
import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.BaseTileWithInventory;

public class EventHandlerClient
{

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void zoom(FOVUpdateEvent event)
	{
		if((event.entity.inventory.getCurrentItem() != null) && (event.entity.inventory.getCurrentItem().getItem() == InitItems.itemSpyglass))
		{
			event.newfov = (float) ConfigGeneral.spyglassZoom;
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(KeyInputEvent event)
	{
		HashMap<String, KeyBinding> keyBindings = ClientProxy.keyBindings;

		if(keyBindings.get("vanity").isPressed())
		{
			EntityPlayer player = ClientHelper.player();
			InitPackets.network.sendToServer(new OpenContainerFromClientPacket(player.getEntityId(), GuiIDs.VANITY, player.dimension));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(receiveCanceled = true)
	public void onPlayerRender(RenderPlayerEvent.Specials.Post event)
	{
		if(event.entityLiving.getActivePotionEffect(Potion.invisibility) != null)
			return;

		EntityPlayerExtended props = ((EntityPlayerExtended) event.entityPlayer.getExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME));
		InventoryVanity inventory = props.getInventory();
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if(inventory.getStackInSlot(i) != null)
			{
				EntityPlayer p = event.entityPlayer;
				IVanityItem item = (IVanityItem) inventory.getStackInSlot(i).getItem();
				ModelBase model = item.getVanityItemModel();
				if(Arrays.asList(LibInfo.names).contains(p.getUniqueID().toString()))
				{
					ClientHelper.textureManager().bindTexture(new ResourceLocation(ModInfo.PREFIX + "/textures/models/vanity/tophat-gold.png"));
				}
				else
					ClientHelper.textureManager().bindTexture(item.getItemTextureLocation());
				GL11.glPushMatrix();
				model.render(null, event.renderer.modelBipedMain.bipedHead.rotateAngleX, event.renderer.modelBipedMain.bipedHead.rotateAngleY,
						event.renderer.modelBipedMain.bipedHead.rotateAngleZ, -1, 0, 0.06F);
				GL11.glPopMatrix();
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	// hacky hack to make the texture work in BC tanks.
	public void textureHook(TextureStitchEvent.Post event)
	{
		if(event.map.getTextureType() == 0)
		{
			InitBlocks.steamFluid.setIcons(InitBlocks.blockSteam.getBlockTextureFromSide(1));
			InitBlocks.boilingWaterFluid.setIcons(InitBlocks.blockBoilingWater.getBlockTextureFromSide(1));
			InitBlocks.boilingMudFluid.setIcons(InitBlocks.blockBoilingMud.getBlockTextureFromSide(1));
			// InitBlocks.whaleOilFluid.setIcons(InitBlocks.blockWhaleOil.getBlockTextureFromSide(1));
		}
	}

	// TODO seems to me like this is a nasty way of doing things
	Block block;
	TileEntity tile;
	int x, y, z;
	EntityPlayer player;

	@SubscribeEvent(priority = EventPriority.HIGH)
	@SideOnly(Side.CLIENT)
	public void renderOverlay(RenderGameOverlayEvent.Text event)
	{
		if((this.player != null) && (this.player.inventory.armorItemInSlot(3) != null))
		{
			ItemStack helmet = this.player.inventory.armorItemInSlot(3);
			if((helmet != null) && (helmet.getItem() == InitItems.itemMonocle))
			{
				ScaledResolution res = ClientHelper.resolution();
				FontRenderer fontRenderer = ClientHelper.fontRenderer();
				res.getScaledWidth();
				res.getScaledHeight();
				ClientHelper.entityRenderer().setupOverlayRendering();

				int posX = 5;
				int posY = 5;
				int posY2 = 15;
				int posY3 = 25;
				int posY4 = 35;
				int posY5 = 45;
				int posY6 = 55;
				int posY7 = 65;
				int posY8 = 75;

				int color = 0xCCFF00;

				if((this.block != null) && !this.player.worldObj.isAirBlock(this.x, this.y, this.z))
				{
					fontRenderer.drawString("Block: " + this.block.getUnlocalizedName().substring(5), posX, posY, color);
					fontRenderer.drawString("Metadata: " + this.block.getDamageValue(ClientHelper.world(), this.x, this.y, this.z), posX, posY2, color);
					fontRenderer.drawString("Hardness: " + this.block.getBlockHardness(ClientHelper.world(), this.x, this.y, this.z), posX, posY3, color);
					fontRenderer.drawString("Light Value: " + this.block.getLightValue(), posX, posY4, color);
					// TODO
					if(this.block instanceof BlockContainer)
					{
						if(this.tile instanceof IEnergyHandler)
						{
							IEnergyHandler energytile = (IEnergyHandler) this.tile;
							fontRenderer.drawString(
									"Energy: " + Integer.toString(energytile.getEnergyStored(ForgeDirection.UP)) + "/"
											+ Integer.toString(energytile.getMaxEnergyStored(ForgeDirection.UP)) + "RF", posX, posY5, color);
						}
						// TODO
						if(this.tile instanceof BaseTileWithInventory)
						{
							BaseTileWithInventory te = (BaseTileWithInventory) this.tile;

							if(te.inventory.length < 0)
							{

								String[] names = new String[te.inventory.length];

								for(int i = 0; i < te.inventory.length; i++)
								{
									names[i] = te.inventory[i].getUnlocalizedName();
								}
								fontRenderer.drawSplitString("Inventory: " + StringUtils.join(names, ","), posX, posY6, color, 20);
							}
						}
					}
					String docs = StatCollector.translateToLocal(this.block.getUnlocalizedName() + ".documentation");
					if(!docs.contains("tile"))
						fontRenderer.drawSplitString(docs, posX, posY8, 100, color);
				}
				/*
				 * TODO if(this.entity != null) { String text = this.entity.getCommandSenderName(); fontRenderer.drawString("Entity: ", mc.displayWidth - 5 -
				 * text.length(), posY, color); String text1 = Integer.toString(this.entity.getEntityId()); fontRenderer.drawString("ID: ", mc.displayWidth - 5
				 * - text1.length(), posY, color); }
				 */
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onDrawBlockSelectionBox(DrawBlockHighlightEvent event)
	{
		if((event.player.inventory.armorItemInSlot(3) != null) && (event.player.inventory.armorItemInSlot(3).getItem() == InitItems.itemBrassGoggles))
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}
		else if((event.player.inventory.armorItemInSlot(3) != null)
				&& (event.player.inventory.armorItemInSlot(3).getItem() == InitItems.itemMonocle))
		{
			this.drawSelectionBox(event.player, event.target, 0, event.currentItem, event.partialTicks);
			event.setCanceled(true);
		}

		this.block = event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
		this.player = event.player;
		this.x = event.target.blockX;
		this.y = event.target.blockY;
		this.z = event.target.blockZ;
		this.tile = event.player.worldObj.getTileEntity(event.target.blockX, event.target.blockY, event.target.blockZ);
	}

	private void drawSelectionBox(EntityPlayer player, MovingObjectPosition mop, int i, ItemStack is, float partialTicks)
	{
		if((i == 0) && (mop.typeOfHit == MovingObjectType.BLOCK))
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			Block block = player.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);

			if(block != Blocks.air)
			{
				block.setBlockBoundsBasedOnState(player.worldObj, mop.blockX, mop.blockY, mop.blockZ);
				double dx = player.lastTickPosX + ((player.posX - player.lastTickPosX) * partialTicks);
				double dy = player.lastTickPosY + ((player.posY - player.lastTickPosY) * partialTicks);
				double dz = player.lastTickPosZ + ((player.posZ - player.lastTickPosZ) * partialTicks);
				this.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(player.worldObj, mop.blockX, mop.blockY, mop.blockZ)
						.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		if((i == 0) && (mop.typeOfHit == MovingObjectType.ENTITY))
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
			GL11.glLineWidth(3.5F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			float offset = 0.002F;
			Entity entity = mop.entityHit;

			if(entity != null)
			{
				entity.setPosition(entity.posX, entity.posY, entity.posZ);
				double dx = player.lastTickPosX + ((player.posX - player.lastTickPosX) * partialTicks);
				double dy = player.lastTickPosY + ((player.posY - player.lastTickPosY) * partialTicks);
				double dz = player.lastTickPosZ + ((player.posZ - player.lastTickPosZ) * partialTicks);
				this.drawOutlinedBoundingBox(entity.boundingBox.expand(offset, offset, offset).getOffsetBoundingBox(-dx, -dy, -dz));
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	private void drawOutlinedBoundingBox(AxisAlignedBB aaBB)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.minZ);
		tessellator.addVertex(aaBB.maxX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.maxX, aaBB.maxY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.minY, aaBB.maxZ);
		tessellator.addVertex(aaBB.minX, aaBB.maxY, aaBB.maxZ);
		tessellator.draw();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		ResourceLocation overlay = new ResourceLocation(ModInfo.PREFIX + "textures/misc/spyglass.png");

		if(event.type != RenderGameOverlayEvent.ElementType.HELMET)
			return;

		EntityClientPlayerMP player = ClientHelper.player();

		if((player != null) && (ClientHelper.screen() == null) &&
				(player.inventory.getCurrentItem() != null) && (player.inventory.
						getCurrentItem().getItem() == InitItems.itemSpyglass) && (ClientHelper.settings().thirdPersonView == 0))
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);

			ClientHelper.textureManager().bindTexture(overlay);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = ClientHelper.resolution();
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			GL11.glDepthMask(false);
			GL11.glClearDepth(1.0D);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0.0D, height, 90.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(width, height, 90.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(width, 0.0D, 90.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
			tessellator.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}
}