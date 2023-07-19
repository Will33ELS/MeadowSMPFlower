package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

public class RoseBlossomEffect extends MEffect {
    public RoseBlossomEffect(ItemStack itemStack) {
        super("rose-blossom", itemStack);
    }

    @Override
    public void onTickEffect(@NonNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false, true));
    }
}
