package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class AlliumEuphoriaEffect extends MEffect {

    private final double increaseAttackDamage;
    public AlliumEuphoriaEffect(ItemStack itemStack, double increaseAttackDamage) {
        super("allium-euphoria", itemStack);
        this.increaseAttackDamage = increaseAttackDamage;
    }

    @Override
    public double onDamage(double damage, @Nullable Player player, EntityDamageEvent.@NonNull DamageCause cause) {
        if(cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            return damage * (this.increaseAttackDamage + 1);
        }
        return super.onDamage(damage, player, cause);
    }
}
