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
package xyz.brassgoggledcoders.steamcraft.common.items.modules;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.api.item.ModuleRegistry;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.BaseDefensiveModule;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemSteelPlating extends BaseDefensiveModule
{
	public ItemSteelPlating()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return -1;
	}

	@Override
	public String getName()
	{
		return "Steel Plating";
	}

	@Override
	public String getModuleId()
	{
		return "plating";
	}

	@Override
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{

	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.DEFENSIVE;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	public int getMaxDamageAbsorb()
	{
		return 5;
	}

	@Override
	public int getDamageAbsorbRatio()
	{
		return 3;
	}

	@Override
	public int getArmorToDisplay()
	{
		return 3;
	}

	@Override
	public int getModuleWeight()
	{
		return 40;
	}

	@Override
	public void onArmorEquipped(World world, EntityPlayer player)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onArmorUnequipped(World world, EntityPlayer player)
	{
		// TODO Auto-generated method stub

	}
}
