package fr.will33_.meadowsmpflower.effect;

import fr.will33_.meadowsmpflower.model.MEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class AzureBluetPurityEffect extends MEffect {
    public AzureBluetPurityEffect(ItemStack itemStack) {
        super("azure-bluet-purity", itemStack);
    }

    @Override
    public void onTickEffect(@NonNull Player player) {
        player.setRemainingAir(player.getMaximumAir());
    }
}
