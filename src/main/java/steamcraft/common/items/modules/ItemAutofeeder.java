
package steamcraft.common.items.modules;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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
public class ItemAutofeeder extends BaseArmorModule
{
	public ItemAutofeeder()
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
		return "Autofeeder";
	}

	@Override
	public String getModuleId()
	{
		return "autofeeder";
	}

	@Override
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		for (ItemStack stack1 : player.inventory.mainInventory)
		{
			if (stack1 != null)
			{
				Item item = stack1.getItem();
				if ((item instanceof ItemFood) && player.getFoodStats().needFood())
				{
					ItemFood food = (ItemFood) item;
					food.onEaten(stack1, world, player);
				}
			}
		}
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

}
