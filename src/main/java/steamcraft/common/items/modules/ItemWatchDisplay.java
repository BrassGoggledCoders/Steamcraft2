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

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.api.item.ModuleRegistry;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;
import boilerplate.client.ClientHelper;

/**
 * @author warlordjones
 *
 */
public class ItemWatchDisplay extends PoweredArmorModule
{
	public ItemWatchDisplay()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setRFToConsume(5);
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
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if((ClientHelper.player() == null) || (ClientHelper.screen() != null))
			return;

		ItemStack helmet = ClientHelper.player().inventory.armorItemInSlot(3);

		if((ClientHelper.settings().thirdPersonView == 0) && this.doConsumption(player, stack))
		{
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = ClientHelper.resolution();
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			FontRenderer fontRenderer = ClientHelper.fontRenderer();
			ClientHelper.entityRenderer().setupOverlayRendering();
			int color = 0xCCFF00;

			final long mcTime = world.getWorldTime();
			final Calendar cal = Calendar.getInstance();
			cal.getTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			fontRenderer.drawString("In-Game Time: " + mcTime + " (" + this.getGeneralTime(mcTime) + ")", 5, 5, color);
			fontRenderer.drawString("Real-World Time: " + sdf.format(cal.getTime()), 5, 15, color);
		}
	}

	private String getGeneralTime(long mcTime)
	{
		if((mcTime >= 0) && (mcTime <= 14000))
			return "Day";
		else
			return "Night";
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.HUD;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

}
