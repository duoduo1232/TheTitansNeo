package net.byAqua3.thetitansneo.loader;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TheTitansNeoSounds {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, TheTitansNeo.MODID);
	
	public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_LIVING = SOUNDS.register("gargoyleliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "gargoyleliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GRUNT = SOUNDS.register("gargoylegrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "gargoylegrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_DEATH = SOUNDS.register("gargoyledeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "gargoyledeath")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CRUSH_WOOD = SOUNDS.register("titancrushwood", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancrushwood")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_HIT_METAL = SOUNDS.register("titanhitmetal", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanhitmetal")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_PRESS = SOUNDS.register("titanpress", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanpress")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SWORD_DRAG = SOUNDS.register("sworddrag", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "sworddrag")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SWING = SOUNDS.register("titanswing", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanswing")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SLASH_FLESH = SOUNDS.register("slashflesh", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "slashflesh")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> HARCACADIUM_HUM = SOUNDS.register("harcadiumhum", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "harcadiumhum")));
	public static final DeferredHolder<SoundEvent, SoundEvent> HARCACADIUM_BLOCK_HUM = SOUNDS.register("harcadiumblockhum", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "harcadiumblockhum")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> LARGE_FALL = SOUNDS.register("largefall", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "largefall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> QUICK_APPERENCE = SOUNDS.register("quickapperence", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "quickapperence")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_BIRTH = SOUNDS.register("titanbirth", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanbirth")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_QUAKE = SOUNDS.register("titanquake", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanquake")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_RUMBLE = SOUNDS.register("titanrumble", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanrumble")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> DISTANT_LARGE_FALL = SOUNDS.register("distantlargefall", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "distantlargefall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_FALL = SOUNDS.register("titanfall", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanfall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SLAM = SOUNDS.register("titanslam", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanslam")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_STRIKE = SOUNDS.register("titanstrike", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanstrike")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GROUND_SMASH = SOUNDS.register("groundsmash", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "groundsmash")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_PUNCH = SOUNDS.register("titanpunch", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanpunch")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_LAND = SOUNDS.register("titanland", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanland")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_STEP = SOUNDS.register("titanstep", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanstep")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SUMMON_MINION = SOUNDS.register("titansummonminion", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titansummonminion")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_SHOOT = SOUNDS.register("turretshoot", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretshoot")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_DEATH = SOUNDS.register("turretdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretdeath")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_SHOOT_2 = SOUNDS.register("turretshoot2", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretshoot2")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_DEATH_2 = SOUNDS.register("turretdeath2", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretdeath2")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_SHOOT_3 = SOUNDS.register("turretshoot3", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretshoot3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TURRET_DEATH_3 = SOUNDS.register("turretdeath3", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "turretdeath3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MORTAR_SHOOT = SOUNDS.register("mortarshoot", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "mortarshoot")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MORTAR_HIT = SOUNDS.register("mortarhit", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "mortarhit")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> LIGHTNING_THROW = SOUNDS.register("lightningthrow", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "lightningthrow")));
	public static final DeferredHolder<SoundEvent, SoundEvent> LIGHTNING_CHARGE = SOUNDS.register("lightningcharge", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "lightningcharge")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIE_LIVING = SOUNDS.register("titanzombieliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanzombieliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIE_GRUNT = SOUNDS.register("titanzombiegrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanzombiegrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIE_ROAR = SOUNDS.register("titanzombieroar", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanzombieroar")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIE_DEATH = SOUNDS.register("titanzombiedeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanzombiedeath")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIE_CREATION = SOUNDS.register("titanzombiecreation", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanzombiecreation")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SILVERFISH_LIVING = SOUNDS.register("titansilverfishliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titansilverfishliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SILVERFISH_GRUNT = SOUNDS.register("titansilverfishgrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titansilverfishgrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SILVERFISH_DEATH = SOUNDS.register("titansilverfishdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titansilverfishdeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_LIVING = SOUNDS.register("titanskeletonliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletonliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_GRUNT = SOUNDS.register("titanskeletongrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletongrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_DEATH = SOUNDS.register("titanskeletondeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletondeath")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_AWAKEN = SOUNDS.register("titanskeletonawaken", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletonawaken")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_BEGIN_MOVE = SOUNDS.register("titanskeletonbeginmove", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletonbeginmove")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_GETUP = SOUNDS.register("titanskeletongetup", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletongetup")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SKELETON_BONE_CRUNCH = SOUNDS.register("titanskeletonbonecrunch", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanskeletonbonecrunch")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_WITHER_SKELETON_LIVING = SOUNDS.register("titanwitherskeletonliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanwitherskeletonliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_WITHER_SKELETON_GRUNT = SOUNDS.register("titanwitherskeletongrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanwitherskeletongrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_WITHER_SKELETON_DEATH = SOUNDS.register("titanwitherskeletondeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanwitherskeletondeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_LIVING = SOUNDS.register("titancreeperliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_GRUNT = SOUNDS.register("titancreepergrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreepergrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_DEATH = SOUNDS.register("titancreeperdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperdeath")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_STUN = SOUNDS.register("titancreeperstun", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperstun")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_WARNING = SOUNDS.register("titancreeperwarning", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperwarning")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_EXPLOSION = SOUNDS.register("titancreeperexplosion", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperexplosion")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_AWAKEN = SOUNDS.register("titancreeperawaken", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperawaken")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_CREEPER_BEGINMOVE = SOUNDS.register("titancreeperbeginmove", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titancreeperbeginmove")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SPIDER_LIVING = SOUNDS.register("titanspiderliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanspiderliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SPIDER_GRUNT = SOUNDS.register("titanspidergrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanspidergrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_SPIDER_DEATH = SOUNDS.register("titanspiderdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanspiderdeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIFIED_PIGLIN_LIVING = SOUNDS.register("titanpigzombieliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanpigzombieliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIFIED_PIGLIN_GRUNT = SOUNDS.register("titanpigzombiegrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanpigzombiegrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ZOMBIFIED_PIGLIN_DEATH = SOUNDS.register("titanpigzombiedeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanpigzombiedeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_BLAZE_BREATHE = SOUNDS.register("titanblazebreathe", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanblazebreathe")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_BLAZE_GRUNT = SOUNDS.register("titanblazegrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanblazegrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_BLAZE_DEATH = SOUNDS.register("titanblazedeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanblazedeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_SCREAM = SOUNDS.register("titanendercolossusscream", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusscream")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_SCREAM_LONG = SOUNDS.register("titanendercolossusscreamlong", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusscreamlong")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_CHOMP = SOUNDS.register("titanendercolossuschomp", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossuschomp")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_STARE = SOUNDS.register("titanendercolossusstare", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusstare")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_ROAR = SOUNDS.register("titanendercolossusroar", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusroar")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_LIVING = SOUNDS.register("titanendercolossusliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_GRUNT = SOUNDS.register("titanendercolossusgrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusgrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_ENDER_COLOSUS_DEATH = SOUNDS.register("titanendercolossusdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanendercolossusdeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_GHAST_FIREBALL = SOUNDS.register("titanghastfireball", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanghastfireball")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_GHAST_CHARGE = SOUNDS.register("titanghastcharge", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanghastcharge")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_GHAST_LIVING = SOUNDS.register("titanghastliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanghastliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_GHAST_GRUNT = SOUNDS.register("titanghastgrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanghastgrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TITAN_GHAST_DEATH = SOUNDS.register("titanghastdeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "titanghastdeath")));
	
	public static final DeferredHolder<SoundEvent, SoundEvent> WITHERZILLA_SPAWN = SOUNDS.register("witherzillaspawn", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "witherzillaspawn")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WITHERZILLA_LIVING = SOUNDS.register("witherzillaliving", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "witherzillaliving")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WITHERZILLA_GRUNT = SOUNDS.register("witherzillagrunt", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "witherzillagrunt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WITHERZILLA_DEATH = SOUNDS.register("witherzilladeath", () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(TheTitansNeo.MODID, "witherzilladeath")));
	
	public static void registerSounds(IEventBus modEventBus) {
		SOUNDS.register(modEventBus);
	}
}
