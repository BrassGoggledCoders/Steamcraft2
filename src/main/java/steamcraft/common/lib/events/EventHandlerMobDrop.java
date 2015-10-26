package steamcraft.common.lib.events;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import steamcraft.common.init.InitItems;

public class EventHandlerMobDrop {

	public Random rand = new Random();

	@SubscribeEvent
	public void onMobDrops(LivingDropsEvent event) {
		if (event.entity instanceof EntityIronGolem) {
			if (event.entity.isBurning()) {
				event.drops.clear();
				ItemStack ingot = new ItemStack(InitItems.itemIngot, 3 + this.rand.nextInt(3), 7);
				ItemStack poppy = new ItemStack(Item.getItemFromBlock(Blocks.red_flower), this.rand.nextInt(3), 0);

				EntityItem ingotDrop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, ingot);
				EntityItem poppyDrop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, poppy);

				event.drops.add(ingotDrop);
				event.drops.add(poppyDrop);
			}
		}
	}
}