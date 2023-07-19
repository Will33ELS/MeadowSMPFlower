package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TulipGraceEffect extends MEffect {
    public TulipGraceEffect(ItemStack itemStack) {
        super("tulip-grace", itemStack);
    }

    @Override
    public void onJoin(@NonNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 1, false, false, true));
    }
}
