package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BlueOrchidRefreshmentEffect extends MEffect {
    public BlueOrchidRefreshmentEffect(ItemStack itemStack) {
        super("blue-orchid-refreshment", itemStack);
    }

    @Override
    public void onTickEffect(@NonNull Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20L);
    }
}
