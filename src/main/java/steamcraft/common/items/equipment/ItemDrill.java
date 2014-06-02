package steamcraft.common.items.equipment;

import java.util.List;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base class for electric drills.
 *
 * @author Decebaldecebal
 *
 */
public class ItemDrill extends ItemCoreDrill /*extends ItemElectricMod*/
{
	//int energyPerBlock = 200; //same as IC2 drill(50 EU per block)
	int toolTier;

	/** An array of blocks the drill can mine. */
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.dirt, Blocks.stone, Blocks.sand, Blocks.clay, Blocks.ice, Blocks.snow, Blocks.netherrack, Blocks.grass, Blocks.gravel};

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		itemIcon = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	public ItemDrill(int toolTier)
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
		this.toolTier = toolTier;
	}

	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		for (int i = 0; i < ItemDrill.blocksEffectiveAgainst.length; ++i) {
            if (ItemDrill.blocksEffectiveAgainst[i] == block) {
                return true;
            }
        }

		return false;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		super.addInformation(stack, player, list, flag);
		list.add("Efficiency: " + getEfficiency());
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player) {
		//if(this.getEnergy(stack) > 0) {
			hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), (this.toolTier + 1));
			stack.damageItem(1, player);
			//	this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock * 2);
		//} else {
		//	hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 1);
		//}

		return false;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata) {
		return /*this.getEnergy(stack) != 0 &&*/ this.canHarvestBlock(block, stack) ? this.getEfficiency() : 1.0F;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		//if(block.getBlockHardness(world, x, y, z) != 0.0D)
			stack.damageItem(1, living);
			//this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock);
		//else
		//	stack.damageItem(1, living);
			//this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock/2);

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public float getEfficiency() {
		return ((this.toolTier + 1.5F) / 1.5F) *  7.5F;
	}
}
