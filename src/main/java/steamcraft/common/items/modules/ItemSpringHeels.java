
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
public class ItemSpringHeels extends PoweredArmorModule
{
	public ItemSpringHeels()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return 3;
	}

	@Override
	public String getName()
	{
		return "Spring Heels";
	}

	@Override
	public String getModuleId()
	{
		return "springheels";
	}

	@Override
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		return;
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.SPECIAL;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	public void onArmorEquipped(World world, EntityPlayer player)
	{
		// player.stepHeight = 1.0F;
	}

	@Override
	public void onArmorUnequipped(World world, EntityPlayer player)
	{
		// player.stepHeight = 0.5F;
	}
}
