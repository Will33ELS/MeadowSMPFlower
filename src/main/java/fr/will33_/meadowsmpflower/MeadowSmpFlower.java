package fr.will33_.meadowsmpflower;

import fr.will33_.meadowsmpflower.database.SQLLiteDB;
import fr.will33_.meadowsmpflower.effect.*;
import fr.will33_.meadowsmpflower.exception.MeadowBuildItemException;
import fr.will33_.meadowsmpflower.exception.MeadowLoadPlayerDataException;
import fr.will33_.meadowsmpflower.listener.PlayerListener;
import fr.will33_.meadowsmpflower.model.MEffect;
import fr.will33_.meadowsmpflower.model.MPlayer;
import fr.will33_.meadowsmpflower.task.EffectTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MeadowSmpFlower extends JavaPlugin {

    private SQLLiteDB sqlLiteDB;
    private final List<MPlayer> mPlayers = new ArrayList<>();
    private final List<MEffect> mEffects = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.sqlLiteDB = new SQLLiteDB();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        try {
            this.mEffects.add(new AlliumEuphoriaEffect(this.getItem("allium-euphoria"), this.getConfig().getInt("gui.allium-euphoria.increaseAttackDamage")));
            this.mEffects.add(new AzureBluetPurityEffect(this.getItem("azure-bluet-purity")));
            this.mEffects.add(new BlueOrchidRefreshmentEffect(this.getItem("blue-orchid-refreshment")));
            this.mEffects.add(new CornflowerTranquilityEffect(this.getItem("cornflower-tranquility"), this.getConfig().getInt("gui.cornflower-tranquility.reduceFallDamage")));
            this.mEffects.add(new DandelionGustEffect(this.getItem("dandelion-gust")));
            this.mEffects.add(new LilacCamouflageEffect(this.getItem("lilac-camouflage")));
            this.mEffects.add(new LilyOfTheValleySerenityEffect(this.getItem("lily-of-the-valley-serenity")));
            this.mEffects.add(new OrangeTulipWarmthEffect(this.getItem("orange-tulip-warmth")));
            this.mEffects.add(new OxeyeDaisyClarityEffect(this.getItem("oxeye-daisy-clarity")));
            this.mEffects.add(new PeonyCharmEffect(this.getItem("peony-charm")));
            this.mEffects.add(new PinkTulipDelightEffect(this.getItem("pink-tulip-delight"), this.getConfig().getInt("gui.pink-tulip-delight.maxHealth")));
            this.mEffects.add(new PoppyVitalityEffect(this.getItem("poppy-vitality"), this.getConfig().getInt("gui.poppy-vitality.maxHealth")));
            this.mEffects.add(new RoseBlossomEffect(this.getItem("rose-blossom")));
            this.mEffects.add(new SunflowerRadianceEffect(this.getItem("sunflower-radiance")));
            this.mEffects.add(new TulipGraceEffect(this.getItem("tulip-grace")));
            this.mEffects.add(new WhiteTulipInnocenceEffect(this.getItem("white-tulip-innocence")));
            this.mEffects.add(new WitherRoseAfflictionEffect(this.getItem("wither-rose-affliction"), this.getConfig().getInt("gui.wither-rose-affliction.witherEffectInSeconds")));
        } catch (MeadowBuildItemException e) {
            e.printStackTrace();
        }

        new EffectTask().runTaskTimer(this, 20, 20);
    }

    private ItemStack getItem(String name) throws MeadowBuildItemException {
        return MEffect.build(
                getConfig().getString("gui." + name + ".material"),
                getConfig().getString("gui." + name + ".displayname"),
                getConfig().getStringList("gui." + name + ".lore"));
    }

    /**
     * Get all effects instance
     * @return
     */
    public List<MEffect> getMEffects() {
        return Collections.unmodifiableList(this.mEffects);
    }

    /**
     * Get all players instance
     * @return
     */
    public List<MPlayer> getMPlayers() {
        return Collections.unmodifiableList(this.mPlayers);
    }

    /**
     * Get player instance
     * @param uuid UUID of the player
     * @return
     */
    public @Nullable MPlayer getMPlayer(UUID uuid){
        return this.mPlayers.stream().filter(mPlayer -> mPlayer.getUUID().equals(uuid)).findFirst().orElse(null);
    }

    /**
     * Load player data
     * @param player Instance of the player
     * @return Instance of the player
     */
    public MPlayer loadPlayer(Player player) throws MeadowLoadPlayerDataException {
        MPlayer mPlayer = this.getSqlLiteDB().loadPlayerData(player);
        this.mPlayers.add(mPlayer);
        return mPlayer;
    }

    /**
     * unload player
     * @param mPlayer Instance of the Player
     */
    public void unloadPlayer(MPlayer mPlayer){
        this.mPlayers.remove(mPlayer);
    }

    /**
     * Get effect by name
     * @param name Name of the effect
     * @return
     */
    public @Nullable MEffect getEffectByName(String name){
        return this.mEffects.stream().filter(effect -> effect.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Get SQLLite instance
     * @return
     */
    public SQLLiteDB getSqlLiteDB() {
        return sqlLiteDB;
    }

    /**
     * Get plugin instance
     * @return
     */
    public static MeadowSmpFlower getInstance(){
        return MeadowSmpFlower.getPlugin(MeadowSmpFlower.class);
    }
}
