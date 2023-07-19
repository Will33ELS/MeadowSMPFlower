package fr.will33_.meadowsmpflower.listener;

import fr.will33_.meadowsmpflower.MeadowSmpFlower;
import fr.will33_.meadowsmpflower.exception.MeadowLoadPlayerDataException;
import fr.will33_.meadowsmpflower.gui.EffectGUI;
import fr.will33_.meadowsmpflower.model.MPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class PlayerListener implements Listener {

    private final MeadowSmpFlower instance = MeadowSmpFlower.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        try {
            MPlayer mPlayer = this.instance.loadPlayer(player);
            Optional.ofNullable(mPlayer.getEffect()).ifPresent(effect -> {
                effect.onJoin(player);
                effect.onTickEffect(player);
            });
            if(mPlayer.getEffect() == null){
                new EffectGUI().openInventory(player);
            }
        } catch (MeadowLoadPlayerDataException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        this.instance.unloadPlayer(this.instance.getMPlayer(event.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player && event.getEntity() instanceof Player victim){
            MPlayer mPlayer = this.instance.getMPlayer(player.getUniqueId());
            if(mPlayer != null && mPlayer.getEffect() != null){
                double damage = mPlayer.getEffect().onDamage(event.getDamage(), victim, event.getCause());
                if(damage == 0){
                    event.setCancelled(true);
                } else {
                    event.setDamage(damage);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player player){
            MPlayer mPlayer = this.instance.getMPlayer(player.getUniqueId());
            if(mPlayer != null && mPlayer.getEffect() != null){
                double damage = mPlayer.getEffect().onDamage(event.getDamage(), null, event.getCause());
                if(damage == 0){
                    event.setCancelled(true);
                } else {
                    event.setDamage(damage);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(EffectGUI.effectsGUI.containsKey(player.getUniqueId()) && event.getClickedInventory() != null){
            EffectGUI effectGUI = EffectGUI.effectsGUI.get(player.getUniqueId());
            if(effectGUI.getInventory().equals(event.getClickedInventory())){
                effectGUI.onClick(player, event.getSlot());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        EffectGUI.effectsGUI.remove(player.getUniqueId());
    }

}
