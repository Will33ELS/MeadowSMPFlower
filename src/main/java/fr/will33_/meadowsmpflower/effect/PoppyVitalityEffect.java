package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PoppyVitalityEffect extends MEffect {
    private final int maxHealth;
    public PoppyVitalityEffect(ItemStack itemStack, int maxHealth) {
        super("poppy-vitality", itemStack);
        this.maxHealth = maxHealth;
    }

    @Override
    public void onJoin(@NonNull Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.maxHealth);
    }
}
