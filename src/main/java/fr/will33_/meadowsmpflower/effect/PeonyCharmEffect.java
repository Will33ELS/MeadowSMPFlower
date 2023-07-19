package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PeonyCharmEffect extends MEffect {
    public PeonyCharmEffect(ItemStack itemStack) {
        super("peony-charm", itemStack);
    }

    @Override
    public void onTickEffect(@NonNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 1, false, false, true));
    }
}
