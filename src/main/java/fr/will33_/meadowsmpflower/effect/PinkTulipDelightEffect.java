package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PinkTulipDelightEffect extends MEffect {
    private final int maxHealth;
    public PinkTulipDelightEffect(ItemStack itemStack, int maxHealth) {
        super("pink-tulip-delight", itemStack);
        this.maxHealth = maxHealth;
    }

    @Override
    public void onJoin(@NonNull Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.maxHealth);
    }

    @Override
    public void onTickEffect(@NonNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, false, false, true));
    }
}
