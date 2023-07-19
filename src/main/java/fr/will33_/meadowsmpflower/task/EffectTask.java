package fr.will33_.meadowsmpflower.task;

import fr.will33_.meadowsmpflower.MeadowSmpFlower;
import fr.will33_.meadowsmpflower.model.MPlayer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class EffectTask extends BukkitRunnable {
    @Override
    public void run() {
        for(MPlayer mPlayer : MeadowSmpFlower.getInstance().getMPlayers()){
            Optional.ofNullable(mPlayer.getEffect()).ifPresent(effect -> Optional.ofNullable(Bukkit.getPlayer(mPlayer.getUUID())).ifPresent(effect::onTickEffect));
        }
    }
}
