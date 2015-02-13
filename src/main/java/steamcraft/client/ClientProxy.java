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
import java.util.HashMap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import org.lwjgl.input.Keyboard;
import steamcraft.client.lib.RenderIDs;
import steamcraft.client.renderers.block.BlockCastIronLampRenderer;
import steamcraft.client.renderers.block.BlockCopperPipeRenderer;
import steamcraft.client.renderers.block.BlockCopperWireRenderer;
import steamcraft.client.renderers.block.BlockRailingRenderer;
import steamcraft.client.renderers.block.BlockSpiderEggRenderer;
import steamcraft.client.renderers.entity.RenderBoar;
import steamcraft.client.renderers.entity.RenderFallingBoulder;
import steamcraft.client.renderers.entity.RenderFleshGolem;
import steamcraft.client.renderers.entity.RenderGhostSpider;
import steamcraft.client.renderers.entity.RenderGrapplingHook;
import steamcraft.client.renderers.entity.RenderGrub;
import steamcraft.client.renderers.entity.RenderLostMiner;
import steamcraft.client.renderers.entity.RenderShroomSkeleton;
import steamcraft.client.renderers.entity.RenderShroomZombie;
import steamcraft.client.renderers.entity.RenderVampireBat;
import steamcraft.client.renderers.item.ModelBrassWings;
import steamcraft.client.renderers.item.ModelJetpack;
import steamcraft.client.renderers.item.ModelWingpack;
import steamcraft.client.renderers.models.ModelFleshGolem;
import steamcraft.client.renderers.models.ModelGrub;
import steamcraft.client.renderers.tile.TileArmorEditorRenderer;
import steamcraft.client.renderers.tile.TileBatteryRenderer;
import steamcraft.client.renderers.tile.TileCastIronLampRenderer;
import steamcraft.client.renderers.tile.TileChargerRenderer;
import steamcraft.client.renderers.tile.TileCopperPipeRenderer;
import steamcraft.client.renderers.tile.TileCopperWireRenderer;
import steamcraft.client.renderers.tile.TileCrystalRenderer;
import steamcraft.client.renderers.tile.TileHatchRenderer;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import steamcraft.client.renderers.tile.TileLightningRodRenderer;
import steamcraft.client.renderers.tile.TileTeslaCoilRenderer;
import steamcraft.common.CommonProxy;
import steamcraft.common.entities.EntityFallingBoulder;
import steamcraft.common.entities.EntityGrapplingHook;
import steamcraft.common.entities.living.EntityBoar;
import steamcraft.common.entities.living.EntityFleshGolem;
import steamcraft.common.entities.living.EntityGhostSpider;
import steamcraft.common.entities.living.EntityGiantSpider;
import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import steamcraft.common.entities.living.EntityShroomSkeleton;
import steamcraft.common.entities.living.EntityShroomZombie;
import steamcraft.common.entities.living.EntitySpiderQueen;
import steamcraft.common.entities.living.EntityVampireBat;
import steamcraft.common.entities.projectile.EntityBullet;
import steamcraft.common.entities.projectile.EntityFieldManipulator;
import steamcraft.common.entities.projectile.EntityRocket;
import steamcraft.common.entities.projectile.EntitySplashLightningBottle;
import steamcraft.common.init.InitItems;
import steamcraft.common.tiles.EmptyTiles;
import steamcraft.common.tiles.EmptyTiles.TileCastIronLamp;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.energy.TileBattery;
import steamcraft.common.tiles.energy.TileCharger;
import steamcraft.common.tiles.energy.TileCopperWire;
import steamcraft.common.tiles.energy.TileLightningRod;
import steamcraft.common.tiles.energy.TileTeslaCoil;
import boilerplate.client.ClientHelper;
import boilerplate.client.fx.FXRaygun;
import boilerplate.client.renderers.block.BlockTESRRenderer;
import boilerplate.client.renderers.block.RenderMinedBlock;
import boilerplate.common.entity.EntityMinedBlock;

/**
 * @author Surseance
 * 
 */
public class ClientProxy extends CommonProxy
{
	public static final HashMap<String, KeyBinding> keyBindings = new HashMap();

	@Override
	public void init()
	{
		super.init();
		//this.registerDisplayInformation();
		this.registerBlockRenderers();
		this.registerEntityRenderers();
		this.registerKeys();
	}

	public void registerKeys()
	{
		keyBindings.put("vanity", new KeyBinding("key.vanity.desc", Keyboard.KEY_V, "key.steamcraft.category"));

		// register all the key bindings
		for(KeyBinding keyBinding : keyBindings.values())
		{
			ClientRegistry.registerKeyBinding(keyBinding);
		}
	}

	private void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderSnowball(InitItems.itemMusketBall));
		RenderingRegistry.registerEntityRenderingHandler(EntityFieldManipulator.class, new RenderSnowball(
				InitItems.itemFieldManipulator));
		RenderingRegistry.registerEntityRenderingHandler(EntitySplashLightningBottle.class, new RenderSnowball(
				InitItems.itemSplashLightningBottle));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, new RenderGrapplingHook());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderSnowball(InitItems.itemRocket));
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingBoulder.class, new RenderFallingBoulder());

		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());

		RenderingRegistry.registerEntityRenderingHandler(EntityFleshGolem.class, new RenderFleshGolem(new ModelFleshGolem(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityLostMiner.class, new RenderLostMiner(new ModelZombie(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrub.class, new RenderGrub(new ModelGrub(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new ModelPig(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostSpider.class, new RenderGhostSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, new RenderVampireBat());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantSpider.class, new RenderGhostSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderQueen.class, new RenderGhostSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityShroomZombie.class, new RenderShroomZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityShroomSkeleton.class, new RenderShroomSkeleton());
	}

	private void registerBlockRenderers()
	{
		RenderIDs.setIDs();
		// Copper Pipe
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperPipe.class, new TileCopperPipeRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperPipeRenderer());
		// Copper Wire
		ClientRegistry.bindTileEntitySpecialRenderer(TileCopperWire.class, new TileCopperWireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCopperWireRenderer());
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
		RenderingRegistry.registerBlockHandler(new BlockRailingRenderer());
		// Cast Iron Lamp
		ClientRegistry.bindTileEntitySpecialRenderer(EmptyTiles.TileCastIronLamp.class, new TileCastIronLampRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCastIronLampRenderer());
		// Hatch
		ClientRegistry.bindTileEntitySpecialRenderer(TileHatch.class, new TileHatchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileHatch(), RenderIDs.blockHatchRI));
		// Crystal
		ClientRegistry.bindTileEntitySpecialRenderer(EmptyTiles.TileCrystal.class, new TileCrystalRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new EmptyTiles.TileCrystal(), RenderIDs.blockCrystalRI));
		// Armor Editor
		ClientRegistry.bindTileEntitySpecialRenderer(TileArmorEditor.class, new TileArmorEditorRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTESRRenderer(new TileArmorEditor(), RenderIDs.blockArmorEditorRI));
		// Spider Egg
		RenderingRegistry.registerBlockHandler(RenderIDs.blockSpiderEggRI, new BlockSpiderEggRenderer());
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
		switch(id)
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
		switch(id)
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
		switch(id)
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
		switch(id)
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
