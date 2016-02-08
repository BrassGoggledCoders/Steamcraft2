
package steamcraft.common.items.modules;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.api.item.ModuleRegistry;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

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
