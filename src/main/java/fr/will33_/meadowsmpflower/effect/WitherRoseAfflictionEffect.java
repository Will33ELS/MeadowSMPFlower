package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class WitherRoseAfflictionEffect extends MEffect {
    private final int witherEffectInSeconds;
    public WitherRoseAfflictionEffect(ItemStack itemStack, int witherEffectInSeconds) {
        super("wither-rose-affliction", itemStack);
        this.witherEffectInSeconds = witherEffectInSeconds;
    }

    @Override
    public double onDamage(double damage, @Nullable Player victim, EntityDamageEvent.@NonNull DamageCause damageCause) {
        if(damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK && victim != null){
            victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, this.witherEffectInSeconds * 20, 1, true, true, true));
        }
        return super.onDamage(damage, victim, damageCause);
    }
}
