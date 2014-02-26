package common.steamcraft.common.item;

import common.steamcraft.common.lib2.ItemIDs;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ModGuns 
{
	public static Item smoothBarrel;
	public static Item rifledBarrel;
	public static Item woodenStock;
	public static Item percussionLock;
	public static Item flintlockMusket;
	public static Item matchlockMusket;
	public static Item percussionCapMusket;
	public static Item flintlockRifle;
	public static Item matchlockRifle;
	public static Item percussionCapRifle;
	public static Item musketRound;
	public static Item percussionRound;
	public static Item rifleRound;
	public static Item rayGun;

	public static void initGuns()
	{
		musketRound = new ItemMod(ItemIDs.musketRoundID).setUnlocalizedName("musketcartridge");
		rifleRound = new ItemMod(ItemIDs.rifleRoundID).setUnlocalizedName("rifleround");
		percussionRound = new ItemMod(ItemIDs.percussionRoundID).setUnlocalizedName("percussioncap");
		percussionLock = new ItemMod(ItemIDs.percussionLockID).setUnlocalizedName("percussionlock");
		smoothBarrel = new ItemMod(ItemIDs.smoothBarrelID).setUnlocalizedName("smoothbarrel");
		rifledBarrel = new ItemMod(ItemIDs.rifledBarrelID).setUnlocalizedName("rifledbarrel");
		woodenStock = new ItemMod(ItemIDs.woodenStockID).setUnlocalizedName("woodenstock");
		// int id, int damage, int delay, int ammoID, String fireSound, String reloadSound
		// multiplied the delay 20 times for balance
		flintlockMusket = new ItemGun(ItemIDs.flintlockMusketID, 6, 60, musketRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("flintlockmusket");
		matchlockMusket = new ItemGun(ItemIDs.matchlockMusketID, 7, 40, musketRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("matchlockmusket");
		percussionCapMusket = new ItemGun(ItemIDs.percussionCapMusketID, 8, 20, percussionRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("percussionmusket");
		flintlockRifle = new ItemGun(ItemIDs.flintlockRifleID, 9, 60, rifleRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("flintlockrifle");
		matchlockRifle = new ItemGun(ItemIDs.matchlockRifleID, 10, 40, rifleRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("matchlockrifle");
		percussionCapRifle = new ItemGun(ItemIDs.percussionCapRifleID, 11, 20, percussionRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("percussionrifle");
		
		// Highly experimental!!
		rayGun = new ItemRayGun(ItemIDs.rayGunID, LibInfo.SC2_PREFIX + "raygun");
	}
}