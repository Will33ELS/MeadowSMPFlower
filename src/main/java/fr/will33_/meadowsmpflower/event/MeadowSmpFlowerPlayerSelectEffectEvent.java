package fr.will33_.meadowsmpflower.event;

import com.google.common.base.Preconditions;
import fr.will33_.meadowsmpflower.model.MEffect;
import fr.will33_.meadowsmpflower.model.MPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Event called when a player selects an effect
 */
public class MeadowSmpFlowerPlayerSelectEffectEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final MPlayer mPlayer;
    private final MEffect effect;
    private boolean cancel = false;

    public MeadowSmpFlowerPlayerSelectEffectEvent(@NonNull MPlayer mPlayer, @NonNull MEffect effect) {
        Preconditions.checkNotNull(mPlayer);
        Preconditions.checkNotNull(effect);
        this.mPlayer = mPlayer;
        this.effect = effect;
    }

    /**
     * Get player instance
     * @return
     */
    public @NonNull MPlayer getMPlayer() {
        return mPlayer;
    }

    /**
     * Get effect selected
     * @return
     */
    public @NonNull MEffect getEffect() {
        return effect;
    }

    /**
     * @exclude
     */
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @exclude
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }


    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
