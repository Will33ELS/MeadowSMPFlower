package fr.will33_.meadowsmpflower.model;

import fr.will33_.meadowsmpflower.exception.MeadowBuildItemException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public abstract class MEffect{

    private final String name;
    private final ItemStack itemStack;

    public MEffect(String name, ItemStack itemStack) {
        this.name = name;
        this.itemStack = itemStack;
    }

    /**
     * Get effect name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get itemstack to display
     * @return
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Method called every second
     * @param player
     */
    public void onTickEffect(@NonNull Player player) {}

    /**
     * Method called on join
     * @param player
     */
    public void onJoin(@NonNull Player player) {}

    /**
     * Method called on damage on the player
     * @param damage Damage amount
     * @param victim Instance of the victim
     * @return New health damaged
     */
    public double onDamage(double damage, @Nullable Player victim, EntityDamageEvent.@NonNull DamageCause damageCause) {
        return damage;
    }

    public static ItemStack build(String materialName, String name, List<String> lore) throws MeadowBuildItemException {
        Material material;
        try{
            material = Material.valueOf(materialName);
        } catch (IllegalArgumentException err){
            throw new MeadowBuildItemException("Material '" + materialName + "' not valid !");
        }
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemMeta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
