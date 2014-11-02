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
package steamcraft.client;

import java.awt.Color;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.client.renderers.block.BlockBatteryRenderer;
import steamcraft.client.renderers.block.BlockCastIronLampRenderer;
import steamcraft.client.renderers.block.BlockChargerRenderer;
import steamcraft.client.renderers.block.BlockCopperPipeRenderer;
import steamcraft.client.renderers.block.BlockCopperWireRenderer;
import steamcraft.client.renderers.block.BlockCrystalRenderer;
import steamcraft.client.renderers.block.BlockHatchRenderer;
import steamcraft.client.renderers.block.BlockLightningRodRenderer;
import steamcraft.client.renderers.block.BlockPlankStackRenderer;
import steamcraft.client.renderers.block.BlockRailingRenderer;
import steamcraft.client.renderers.block.BlockTeslaCoilRenderer;
import steamcraft.client.renderers.entity.RenderFleshGolem;
import steamcraft.client.renderers.entity.RenderGrapplingHook;
import steamcraft.client.renderers.item.ModelBrassWings;
import steamcraft.client.renderers.item.ModelJetpack;
import steamcraft.client.renderers.item.ModelWingpack;
import steamcraft.client.renderers.models.ModelFleshGolem;
import steamcraft.client.renderers.tile.TileBatteryRenderer;
import steamcraft.client.renderers.tile.TileCastIronLampRenderer;
import steamcraft.client.renderers.tile.TileCastIronLampRenderer.TileCastIronLamp;
import steamcraft.client.renderers.tile.TileChargerRenderer;
import steamcraft.client.renderers.tile.TileCopperPipeRenderer;
import steamcraft.client.renderers.tile.TileCopperWireRenderer;
import steamcraft.client.renderers.tile.TileCrystalRenderer;
import steamcraft.client.renderers.tile.TileCrystalRenderer.TileCrystal;
import steamcraft.client.renderers.tile.TileHatchRenderer;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import steamcraft.client.renderers.tile.TileLightningRodRenderer;
import steamcraft.client.renderers.tile.TileTeslaCoilRenderer;
import steamcraft.common.CommonProxy;
import steamcraft.common.InitItems;
import steamcraft.common.entities.EntityFleshGolem;
import steamcraft.common.entities.EntityGrapplingHook;
import steamcraft.common.entities.projectile.EntityBullet;
import steamcraft.common.entities.projectile.EntityRocket;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.TileCharger;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.TileCopperWire;
import steamcraft.common.tiles.TileLightningRod;
import steamcraft.common.tiles.TileTeslaCoil;
import boilerplate.client.fx.FXRaygun;
import boilerplate.client.renderers.block.RenderMinedBlock;
import boilerplate.common.entity.EntityMinedBlock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author Surseance
 * 
 */
public class ClientProxy extends CommonProxy
{
	/*
	 * @Override public void registerKeys() { FMLCommonHandler.instance().bus().register(new InitKeyBindings()); }
	 */

	@Override
	public void registerDisplayInformation()
	{
	}

	@Override
	public void registerRenderers()
	{
		this.registerBlockRenderers();
		this.registerEntityRenderers();
	}

	private void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());

		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, /* new RenderBullet() */new RenderSnowball(InitItems.itemMusketBall));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, new RenderGrapplingHook());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderSnowball(InitItems.itemRocket)); // TODO

		RenderingRegistry.registerEntityRenderingHandler(EntityFleshGolem.class, new RenderFleshGolem(new ModelFleshGolem(), 0));
	}

	private void registerBlockRenderers()
	{
		// Crystal
		RenderIDs.blockCrystalRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCrystalRenderer());
		// Copper Pipe
		RenderIDs.blockCopperPipeRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperPipe.class, new TileCopperPipeRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperPipeRenderer());
		// Copper Wire
		RenderIDs.blockCopperWireRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperWire.class, new TileCopperWireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperWireRenderer());
		// Lightning Rod
		RenderIDs.blockLightningRodRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileLightningRod.class, new TileLightningRodRenderer());
		RenderingRegistry.registerBlockHandler(new BlockLightningRodRenderer());
		// TeslaCoil
		RenderIDs.blockTeslaCoilRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileTeslaCoil.class, new TileTeslaCoilRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTeslaCoilRenderer());
		// Battery
		RenderIDs.blockBatteryRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileBattery.class, new TileBatteryRenderer());
		RenderingRegistry.registerBlockHandler(new BlockBatteryRenderer());
		// Charger
		RenderIDs.blockChargerRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCharger.class, new TileChargerRenderer());
		RenderingRegistry.registerBlockHandler(new BlockChargerRenderer());
		// Cast Iron Railing
		RenderIDs.blockCastIronRailingRI = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlockRailingRenderer());
		// Cast Iron Lamp
		RenderIDs.blockCastIronLampRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCastIronLamp.class, new TileCastIronLampRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCastIronLampRenderer());
		// Hatch
		RenderIDs.blockHatchRI = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileHatch.class, new TileHatchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockHatchRenderer());
		// Plank Stack
		RenderIDs.blockPlankStackRI = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlockPlankStackRenderer());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input,
			int impact, Color rayColor)
	{
		FXRaygun ray = null;
		Color color = rayColor;

		if(input instanceof FXRaygun)
			ray = (FXRaygun) input;
		if((ray == null) || ray.isDead)
		{
			ray = new FXRaygun(world, player, dx, dy, dz, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 9);
			ray.setType(type);
			ray.setEndMod(endMod);
			ray.setReverse(reverse);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(ray);
		}
		else
		{
			ray.updateRay(dx, dy, dz);
			ray.setEndMod(endMod);
			ray.impact = impact;
		}

		return ray;
	}

	@Override
	public ModelBiped getWingsArmorModel(int id)
	{
		switch(id)
		{
			case 0:
				return new ModelBrassWings(1.0F);
			case 1:
				return new ModelBrassWings(0.5F);
			default:
				break;
		}

		return new ModelBrassWings(1.0F);
	}

	@Override
	public ModelBiped getJetpackArmorModel(int id)
	{
		switch(id)
		{
			case 0:
				return new ModelJetpack(1.0F);
			case 1:
				return new ModelJetpack(0.5F);
			default:
				break;
		}

		return new ModelJetpack(1.0F);
	}

	@Override
	public ModelBiped getWingpackArmorModel(int id)
	{
		switch(id)
		{
			case 0:
				return new ModelWingpack(1.0F);
			case 1:
				return new ModelWingpack(0.5F);
			default:
				break;
		}

		return new ModelWingpack(1.0F);
	}
}
