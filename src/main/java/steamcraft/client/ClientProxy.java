/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 12, 2014, 4:18:37 PM]
 */
package steamcraft.client;

import java.awt.Color;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import steamcraft.client.fx.FXRaygun;
import steamcraft.client.fx.FXSmoke;
import steamcraft.client.renderers.block.BlockCrystalRenderer;
import steamcraft.client.renderers.block.BlockHatchRenderer;
import steamcraft.client.renderers.tile.TileCrystalRenderer;
import steamcraft.client.renderers.tile.TileHatchRenderer;
import steamcraft.common.CommonProxy;
import steamcraft.common.lib.Utils;
import steamcraft.common.tiles.TileCrystal;
import steamcraft.common.tiles.TileHatch;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ClientProxy extends CommonProxy
{

	@Override
    public void registerDisplayInformation()
    {
        Utils.downloadCapes();
    }
	@Override
	public void registerRenderers()
	{
        ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileHatch.class, new TileHatchRenderer());
        
        RenderingRegistry.registerBlockHandler(new BlockCrystalRenderer());
        RenderingRegistry.registerBlockHandler(new BlockHatchRenderer());
	}

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if ((world instanceof WorldClient))
        {
            switch (ID)
            {

            }
        }

        return null;
    }

    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

	/*
	public void registerKeyBindings()
	{
		KeyBinding[] key = { new KeyBinding("", 00) };
		boolean[] repeat = { false };
		KeyBindingRegistry.registerKeyBinding(new SCKeyHandler(key, repeat));
		super.registerKeyBindings();
	}
	*/

    // ========== Warning: PAIN IN THE ASS MATERIAL ========== //

    @Override
    public Object rayFX(World world, EntityPlayer player, double dx, double dy, double dz, int type, boolean reverse, float endMod, Object input, int impact)
    {
        FXRaygun ray = null;
        Color color = Color.RED;

        if (input instanceof FXRaygun)
        {
            ray = (FXRaygun)input;
        }
        if ((ray == null) || (ray.isDead))
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
    public Object smokeFX(World world, double dx, double dy, double dz, Object input)
    {
        FXSmoke smoke = null;
        Color color = Color.BLUE;

        if (input instanceof  FXSmoke)
        {
            smoke = (FXSmoke)input;
        }
        if ((smoke == null) || (smoke.isDead))
        {
            smoke = new FXSmoke(world, dx, dy, dz, color.getBlue() / 255.0F, color.getBlue() / 255.0F, color.getBlue() / 255.0F);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(smoke);
        }
        else
        {
            smoke.onUpdate();
        }

        return smoke;
    }
    
    /*
    private static final ModelBrassMonocle chest = new ModelBrassMonocle(1.0F);
	private static final ModelBrassMonocle legs = new ModelBrassMonocle(0.5F);

	@Override
	public ModelBiped getMonocleArmorModel(int id) {
		switch (id) 
		{
		case 0:
			return chest;
		case 1:
			return legs;
		default:
			break;
		}

		return chest; // Default, if the wrong ID is passed
	}

	/*
	private static final ModelBrassWings chest1 = new ModelBrassWings(1.0F);
	private static final ModelBrassWings legs1 = new ModelBrassWings(0.5F);

	@Override
	public ModelBiped getWingsArmorModel(int id) {
		switch (id) 
		{
		case 0:
			return chest1;
		case 1:
			return legs1;
		default:
			break;
		}

		return chest1; // Default, if the wrong ID is passed
	}

	private static final ModelCape chest2 = new ModelCape(1.0F);
	private static final ModelCape legs2 = new ModelCape(0.5F);

	@Override
	public ModelBiped getCapeArmorModel(int id) {
		switch(id)
		{
		case 0:
			return chest2;
		case 1:
			return legs2;
		default:
			break;
		}

		return chest2; // Default, if the wrong ID is passed
	}*/
}
