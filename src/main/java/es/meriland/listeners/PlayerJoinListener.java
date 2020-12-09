package es.meriland.listeners;

import es.meriland.MaskBuilder;
import es.meriland.Messager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().addItem(MaskBuilder.mask());
        Messager.sendMessage(player, "Has recibido una mascarilla de la ETSISI. Úsala para protegerte de los demás, y recuerda mantener la distancia de seguridad.");
    }
}
