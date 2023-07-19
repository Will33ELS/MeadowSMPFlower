package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class WhiteTulipInnocenceEffect extends MEffect {
    public WhiteTulipInnocenceEffect(ItemStack itemStack) {
        super("white-tulip-innocence", itemStack);
    }

    @Override
    public double onDamage(double damage, @Nullable Player victim, EntityDamageEvent.@NonNull DamageCause damageCause) {
        if(damageCause == EntityDamageEvent.DamageCause.POISON){
            return 0;
        }
        return super.onDamage(damage, victim, damageCause);
    }
}
