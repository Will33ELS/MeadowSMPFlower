package fr.will33_.meadowsmpflower.model;

import com.google.common.base.Preconditions;
import fr.will33_.meadowsmpflower.MeadowSmpFlower;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class MPlayer {

    private final UUID uuid;
    private MEffect effect;

    public MPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Get player UUID
     * @return
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Get player effect
     * @return
     */
    public @Nullable MEffect getEffect(){
        return this.effect;
    }

    /**
     * Define player effect
     * @param effect Instance of the effect
     */
    public void setEffect(@NonNull MEffect effect) {
        Preconditions.checkNotNull(effect);
        this.effect = effect;
    }

    /**
     * Save player data in the SQLLite database
     */
    public void saveData(){
        Optional.ofNullable(this.effect).ifPresent(effect -> MeadowSmpFlower.getInstance().getSqlLiteDB().savePlayerData(this));
    }
}
