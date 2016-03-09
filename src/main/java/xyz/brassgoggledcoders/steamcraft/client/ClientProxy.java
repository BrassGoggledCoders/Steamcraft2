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
package xyz.brassgoggledcoders.steamcraft.client;

import java.awt.Color;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.client.fx.FXRaygun;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.block.RenderMinedBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.entity.EntityMinedBlock;
import xyz.brassgoggledcoders.steamcraft.client.lib.RenderIDs;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCastIronGateRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCastIronLampRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCastIronRailingRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCopperPipeRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCopperTankRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockCopperWireRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockHatchRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockSpiderEggRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockSteelPipeRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockSteelWireRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.block.BlockTransparentWithInsideRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderAbandonedGolem;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderBoar;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderFallingBoulder;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderFleshGolem;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderGhostSpider;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderGiantSpider;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderGrub;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderLostMiner;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderShroomSkeleton;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderShroomZombie;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderSpiderQueen;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderVampireBat;
import xyz.brassgoggledcoders.steamcraft.client.renderers.entity.RenderWhale;
import xyz.brassgoggledcoders.steamcraft.client.renderers.item.ModelBrassWings;
import xyz.brassgoggledcoders.steamcraft.client.renderers.item.ModelJetpack;
import xyz.brassgoggledcoders.steamcraft.client.renderers.item.ModelWingpack;
import xyz.brassgoggledcoders.steamcraft.client.renderers.models.ModelFleshGolem;
import xyz.brassgoggledcoders.steamcraft.client.renderers.models.ModelGrub;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileArmorEditorRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileBatteryRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileCastIronLampRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileChargerRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileCopperPipeRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileCopperTankRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileCopperWireRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileHatchRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileLightningRodRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileSteelPipeRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileSteelWireRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileTeslaCoilRenderer;
import xyz.brassgoggledcoders.steamcraft.client.renderers.tile.TileTrunkRenderer;
import xyz.brassgoggledcoders.steamcraft.common.CommonProxy;
import xyz.brassgoggledcoders.steamcraft.common.entities.EntityFallingBoulder;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityAbandonedGolem;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityBoar;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityFleshGolem;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGhostSpider;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGiantSpider;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityGrub;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityLostMiner;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityShroomSkeleton;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityShroomZombie;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntitySpiderQueen;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityVampireBat;
import xyz.brassgoggledcoders.steamcraft.common.entities.living.EntityWhale;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityBullet;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityFieldManipulator;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntityRocket;
import xyz.brassgoggledcoders.steamcraft.common.entities.projectile.EntitySplashLightningBottle;
import xyz.brassgoggledcoders.steamcraft.common.init.InitItems;
import xyz.brassgoggledcoders.steamcraft.common.tiles.EmptyTiles;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileArmorEditor;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileCopperPipe;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileCopperTank;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileSteelPipe;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileTrunk;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileBattery;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileCharger;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileCopperWire;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileLightningRod;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileSteelWire;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileTeslaCoil;

/**
 * @author Surseance
 *
 */
public class ClientProxy extends CommonProxy
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final HashMap<String, KeyBinding> keyBindings = new HashMap();

	@Override
	public void init()
	{
		
	}

	private void registerKeys()
	{
		keyBindings.put("vanity", new KeyBinding("key.vanity.desc", Keyboard.KEY_V, "key.steamcraft.category"));

		// register all the key bindings
		for (KeyBinding keyBinding : keyBindings.values())
		{
			ClientRegistry.registerKeyBinding(keyBinding);
		}
	}

	private void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFleshGolem.class, new RenderFleshGolem(new ModelFleshGolem(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityLostMiner.class, new RenderLostMiner(new ModelZombie(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrub.class, new RenderGrub(new ModelGrub(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new ModelPig(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostSpider.class, new RenderGhostSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, new RenderVampireBat());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantSpider.class, new RenderGiantSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderQueen.class, new RenderSpiderQueen());
		RenderingRegistry.registerEntityRenderingHandler(EntityShroomZombie.class, new RenderShroomZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityShroomSkeleton.class, new RenderShroomSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityAbandonedGolem.class, new RenderAbandonedGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityWhale.class, new RenderWhale());

		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderSnowball(InitItems.itemMusketBall));
		RenderingRegistry.registerEntityRenderingHandler(EntityFieldManipulator.class, new RenderSnowball(InitItems.itemFieldManipulator));
		RenderingRegistry.registerEntityRenderingHandler(EntitySplashLightningBottle.class, new RenderSnowball(InitItems.itemSplashLightningBottle));
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderSnowball(InitItems.itemRocket));
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingBoulder.class, new RenderFallingBoulder());
		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());
	}

	private void registerBlockRenderers()
	{
		RenderIDs.setIDs();
		// Copper Pipe
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperPipe.class, new TileCopperPipeRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperPipeRenderer());
		// Steel Pipe
		ClientRegistry.bindTileEntitySpecialRenderer(TileSteelPipe.class, new TileSteelPipeRenderer());
		RenderingRegistry.registerBlockHandler(new BlockSteelPipeRenderer());
		// Copper Tank
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperTank.class, new TileCopperTankRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperTankRenderer());
		// Copper Wire
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperWire.class, new TileCopperWireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperWireRenderer());
		// Steel Wire
		ClientRegistry.bindTileEntitySpecialRenderer(TileSteelWire.class, new TileSteelWireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockSteelWireRenderer());
		// Lightning Rod
		ClientRegistry.bindTileEntitySpecialRenderer(TileLightningRod.class, new TileLightningRodRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileLightningRod(), RenderIDs.blockLightningRodRI));
		// TeslaCoil
		ClientRegistry.bindTileEntitySpecialRenderer(TileTeslaCoil.class, new TileTeslaCoilRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileTeslaCoil(), RenderIDs.blockTeslaCoilRI));
		// Battery
		ClientRegistry.bindTileEntitySpecialRenderer(TileBattery.class, new TileBatteryRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileBattery(), RenderIDs.blockBatteryRI));
		// Charger
		ClientRegistry.bindTileEntitySpecialRenderer(TileCharger.class, new TileChargerRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileCharger(), RenderIDs.blockChargerRI));
		// Cast Iron Railing
		RenderingRegistry.registerBlockHandler(new BlockCastIronRailingRenderer());
		// Cast Iron Lamp
		ClientRegistry.bindTileEntitySpecialRenderer(EmptyTiles.TileCastIronLamp.class, new TileCastIronLampRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCastIronLampRenderer());
		// Hatch
		ClientRegistry.bindTileEntitySpecialRenderer(TileHatch.class, new TileHatchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockHatchRenderer());
		// Armor Editor
		ClientRegistry.bindTileEntitySpecialRenderer(TileArmorEditor.class, new TileArmorEditorRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileArmorEditor(), RenderIDs.blockArmorEditorRI));
		// Spider Egg
		RenderingRegistry.registerBlockHandler(RenderIDs.blockSpiderEggRI, new BlockSpiderEggRenderer());
		// Trunk
		ClientRegistry.bindTileEntitySpecialRenderer(TileTrunk.class, new TileTrunkRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileTrunk(), RenderIDs.blockTrunkRI));
		// Cast Iron Gate
		RenderingRegistry.registerBlockHandler(new BlockCastIronGateRenderer());
		// Lattice
		RenderingRegistry.registerBlockHandler(new BlockTransparentWithInsideRenderer());
	}

	@Override
	public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input,
			int impact, Color rayColor)
	{
		FXRaygun ray = null;
		Color color = rayColor;

		if (input instanceof FXRaygun)
			ray = (FXRaygun) input;
		if ((ray == null) || ray.isDead)
		{
			ray = new FXRaygun(world, player, dx, dy, dz, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 9);
			ray.setType(type);
			ray.setEndMod(endMod);
			ray.setReverse(reverse);
			ClientHelper.effectRenderer().addEffect(ray);
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
		switch (id)
		{
		case 1:
			return new ModelBrassWings(0.5F);
		default:
			return new ModelBrassWings(1.0F);
		}
	}

	@Override
	public ModelBiped getJetpackArmorModel(int id)
	{
		switch (id)
		{
		case 1:
			return new ModelJetpack(0.5F);
		default:
			return new ModelJetpack(1.0F);
		}
	}

	@Override
	public ModelBiped getWingpackArmorModel(int id)
	{
		switch (id)
		{
		case 1:
			return new ModelWingpack(0.5F);
		default:
			return new ModelWingpack(1.0F);
		}
	}

	@Override
	public boolean isKeyPressed(int id)
	{
		switch (id)
		{
		case 0:
			return ClientHelper.settings().keyBindJump.getIsKeyPressed();
		default:
			return false;
		}
	}

	@Override
	public boolean isScreenEmpty()
	{
		return ClientHelper.screen() == null;
	}
}
