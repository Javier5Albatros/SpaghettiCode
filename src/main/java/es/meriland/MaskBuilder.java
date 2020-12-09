package es.meriland;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MaskBuilder {


    public static ItemStack mask() {
        ItemStack mask = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) mask.getItemMeta();
        List<String> lore = new ArrayList<>();
        skullMeta.setDisplayName("Mascarilla ETSISI");
        skullMeta.setOwner("elasdelpollo");
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        lore.add("Mascarilla Homologada");
        skullMeta.setUnbreakable(true);
        skullMeta.setLore(lore);
        skullMeta.getPersistentDataContainer().set(new NamespacedKey(SpaghettiCode.getInstance(), "mask"), PersistentDataType.INTEGER, 1);
        mask.setItemMeta(skullMeta);
        return mask;
    }

    public static boolean isMask(ItemStack item) {
        return item != null && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SpaghettiCode.getInstance(), "mask"), PersistentDataType.INTEGER);
    }
}