package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.SC2_Info;
import common.steamcraft.mod.common.lib.SC2_ItemIDs;

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
		musketRound = new ItemSC2(SC2_ItemIDs.musketRoundID).setUnlocalizedName("musketcartridge");
		rifleRound = new ItemSC2(SC2_ItemIDs.rifleRoundID).setUnlocalizedName("rifleround");
		percussionRound = new ItemSC2(SC2_ItemIDs.percussionRoundID).setUnlocalizedName("percussioncap");
		percussionLock = new ItemSC2(SC2_ItemIDs.percussionLockID).setUnlocalizedName("percussionlock");
		smoothBarrel = new ItemSC2(SC2_ItemIDs.smoothBarrelID).setUnlocalizedName("smoothbarrel");
		rifledBarrel = new ItemSC2(SC2_ItemIDs.rifledBarrelID).setUnlocalizedName("rifledbarrel");
		woodenStock = new ItemSC2(SC2_ItemIDs.woodenStockID).setUnlocalizedName("woodenstock");
		// int id, int damage, int delay, int ammoID, String fireSound, String reloadSound
		// multiplied the delay 20 times for balance
		flintlockMusket = new ItemGun(SC2_ItemIDs.flintlockMusketID, 6, 60, musketRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("flintlockmusket");
		matchlockMusket = new ItemGun(SC2_ItemIDs.matchlockMusketID, 7, 40, musketRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("matchlockmusket");
		percussionCapMusket = new ItemGun(SC2_ItemIDs.percussionCapMusketID, 8, 20, percussionRound.itemID, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("percussionmusket");
		flintlockRifle = new ItemGun(SC2_ItemIDs.flintlockRifleID, 9, 60, rifleRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("flintlockrifle");
		matchlockRifle = new ItemGun(SC2_ItemIDs.matchlockRifleID, 10, 40, rifleRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("matchlockrifle");
		percussionCapRifle = new ItemGun(SC2_ItemIDs.percussionCapRifleID, 11, 20, percussionRound.itemID, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("percussionrifle");
		
		// Highly experimental!!
		rayGun = new ItemRayGun(SC2_ItemIDs.rayGunID, SC2_Info.SC2_PREFIX + "raygun");
		
		initGunNames();
	}
	
	public static void initGunNames()
	{
		LanguageRegistry.addName(smoothBarrel, "Smooth Barrel");
		LanguageRegistry.addName(rifledBarrel, "Rifled Barrel");
		LanguageRegistry.addName(woodenStock, "Wooden Stock");
		LanguageRegistry.addName(percussionLock, "Percussion Lock");
		LanguageRegistry.addName(flintlockMusket, "Flintlock Musket");
		LanguageRegistry.addName(matchlockMusket, "Matchlock Musket");
		LanguageRegistry.addName(percussionCapMusket, "Percussion-Cap Musket");
		LanguageRegistry.addName(flintlockRifle, "Flintlock Rifle");
		LanguageRegistry.addName(matchlockRifle, "Matchlock Rifle");
		LanguageRegistry.addName(percussionCapRifle, "Percussion-Cap Rifle");
		LanguageRegistry.addName(musketRound, "Musket Round");
		LanguageRegistry.addName(percussionRound, "Percussion Cap");
		LanguageRegistry.addName(rifleRound, "Rifle Round");
		LanguageRegistry.addName(rayGun, "Ray Gun");
	}
}