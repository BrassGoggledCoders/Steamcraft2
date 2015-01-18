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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 * 
 */
public class ItemWatchDisplay extends BaseArmorModule
{
	public ItemWatchDisplay()
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
		return "Time Display";
	}

	@Override
	public String getModuleId()
	{
		return "clock";
	}

	@Override
	public boolean applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if((Minecraft.getMinecraft().thePlayer == null) || (Minecraft.getMinecraft().currentScreen != null))
			return false;

		ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);

		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
		{
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth,
					Minecraft.getMinecraft().displayHeight);
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
			Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
			int color = 0xCCFF00;

			final long mcTime = world.getTotalWorldTime();
			final Calendar cal = Calendar.getInstance();
			cal.getTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			fontRenderer.drawString("In-Game Time: " + mcTime, 5, 5, color);
			fontRenderer.drawString("Real-World Time: " + sdf.format(cal.getTime()), 5, 15, color);
			return true;
		}
		return false;
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
		this.itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

}
