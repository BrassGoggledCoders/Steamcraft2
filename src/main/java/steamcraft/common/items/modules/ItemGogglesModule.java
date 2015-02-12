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
package steamcraft.common.items.modules;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.items.armor.ItemBrassGoggles;
import steamcraft.common.lib.ModInfo;
import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;

/**
 * @author warlordjones
 * 
 */
public class ItemGogglesModule extends BaseArmorModule
{
	public ItemGogglesModule()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return 0;
	}

	@Override
	public String getName()
	{
		return "Goggles Module";
	}

	@Override
	public String getModuleId()
	{
		return "goggles";
	}

	@Override
	public boolean applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		this.addOverlay(player);
		player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 1, true));
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addOverlay(EntityPlayer player)
	{
		if((player == null) || (ClientHelper.screen() != null))
			return;

		ItemStack helmet = player.inventory.armorItemInSlot(3);

		if((ClientHelper.settings().thirdPersonView == 0) && (helmet != null) && (helmet.getItem() == InitItems.brassGoggles))
		{
			ClientHelper.textureManager().bindTexture(ItemBrassGoggles.overlay);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = ClientHelper.resolution();
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);

			GL11.glDisable(GL11.GL_ALPHA_TEST);
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

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.HUD;
	}

	@Override
	public int getSteamConsumedOnEffect()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyConsumedOnEffect()
	{
		return 5;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

}
