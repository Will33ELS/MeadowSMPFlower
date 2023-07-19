package fr.will33_.meadowsmpflower.gui;

import fr.will33_.meadowsmpflower.MeadowSmpFlower;
import fr.will33_.meadowsmpflower.model.MEffect;
import fr.will33_.meadowsmpflower.model.MPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectGUI {

    public static Map<UUID, EffectGUI> effectsGUI = new HashMap<>();
    private Map<Integer, String> actions = new HashMap<>();
    private Inventory inventory;

    public void openInventory(Player player){
        effectsGUI.put(player.getUniqueId(), this);
        this.inventory = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', MeadowSmpFlower.getInstance().getConfig().getString("gui.title")));

        int slot = 0;
        for(MEffect mEffect : MeadowSmpFlower.getInstance().getMEffects()){
            this.setSlotData(slot, mEffect.getItemStack(), mEffect.getName());
            slot ++;
        }

        player.openInventory(inventory);
    }

    public void onClick(Player player, int slot){
        MPlayer mPlayer = MeadowSmpFlower.getInstance().getMPlayer(player.getUniqueId());
        if(mPlayer != null){
            MEffect mEffect = MeadowSmpFlower.getInstance().getEffectByName(this.actions.get(slot));
            if(mEffect != null){
                boolean alreadySelected = MeadowSmpFlower.getInstance().getSqlLiteDB().isAlreadySelected(mEffect);
                if(alreadySelected){
                    player.sendMessage(MeadowSmpFlower.getInstance().getConfig().getString("messages.alreadySelected"));
                } else {
                    mPlayer.setEffect(mEffect);
                    mPlayer.saveData();
                    player.closeInventory();
                }
            }
        }
    }

    /**
     * Get inventory instance
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }

    private void setSlotData(int slot, ItemStack itemStack, String action){
        this.inventory.setItem(slot, itemStack);
        this.actions.put(slot, action);
    }
}
