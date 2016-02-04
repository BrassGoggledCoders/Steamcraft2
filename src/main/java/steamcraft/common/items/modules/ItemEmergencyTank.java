
package steamcraft.common.items.modules;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
public class ItemEmergencyTank extends BaseArmorModule
{
	public ItemEmergencyTank()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return 2;
	}

	@Override
	public String getName()
	{
		return "Emergency Water Tank";
	}

	@Override
	public String getModuleId()
	{
		return "watertank";
	}

	@Override
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		if (player.isBurning())
		{
			player.extinguish();
			int x = (int) Math.floor(player.posX);
			int y = (int) Math.floor(player.posY);
			int z = (int) Math.floor(player.posZ);
			for (int i = 0; i < 20; i++)
				world.spawnParticle("splash", x + world.rand.nextFloat(), y + world.rand.nextFloat(), z + world.rand.nextFloat(),
						0 + world.rand.nextFloat(), 0 + world.rand.nextFloat(), 0 + world.rand.nextFloat());
			for (int xOff = -3; xOff <= 3; xOff++)
			{
				for (int yOff = -3; yOff <= 3; yOff++)
				{
					for (int zOff = -3; zOff <= 3; zOff++)
						if (player.worldObj.getBlock(x + xOff, y + yOff, z + zOff) == Blocks.fire)
						{
							player.worldObj.setBlock(x + xOff, y + yOff, z + zOff, Blocks.air);
							player.worldObj.playSoundEffect(x + xOff + 0.5D, y + yOff + 0.5D, z + zOff + 0.5D, "random.fizz", 0.5F,
									2.6F + ((player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.8F));
						}
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
