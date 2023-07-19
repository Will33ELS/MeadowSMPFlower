package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CornflowerTranquilityEffect extends MEffect {

    private double reduceFallDamage;
    public CornflowerTranquilityEffect(ItemStack itemStack, double reduceFallDamage) {
        super("cornflower-tranquility", itemStack);
        this.reduceFallDamage = reduceFallDamage;
    }

    @Override
    public double onDamage(double damage, @Nullable Player victim, EntityDamageEvent.@NonNull DamageCause damageCause) {
        if(damageCause == EntityDamageEvent.DamageCause.FALL){
            return damage * (1 - this.reduceFallDamage);
        }
        return super.onDamage(damage, victim, damageCause);
    }
}
