package es.meriland.listeners;

import es.meriland.MaskBuilder;
import es.meriland.Messager;
import es.meriland.NearbyPlayerTracker;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PlayerMoveListener implements Listener {

    final private static int distance = 3;
    NearbyPlayerTracker nbp = new NearbyPlayerTracker(13);


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        List<Entity> nearbyPlayers = player.getNearbyEntities(distance, distance, distance);
        checkPlayerGroups(player, nearbyPlayers, 6);
        if(!nbp.isInSet(player)) {
            if (MaskBuilder.isMask(player.getInventory().getHelmet())) {
                pushPlayer(player, nearbyPlayers, 0.5, 1.3);
            } else {
                pushPlayer(player, nearbyPlayers, 1.3, 2.5);
            }
            nbp.addRemoveSet(player);
        }
    }

    private void pushPlayer(Player player, List<Entity> nearbyPlayers, double minPush, double maxPush) {
        nearbyPlayers.stream().filter(entity -> entity instanceof Player).forEach(entity -> {
            Player otherPlayer = (Player)entity;
            Vector push = player.getLocation().toVector().subtract(otherPlayer.getLocation().toVector());
            if(MaskBuilder.isMask(otherPlayer.getInventory().getHelmet())) {
                push = push.normalize().multiply(minPush);
            } else {
                push = push.normalize().multiply(maxPush);
            }
            player.setVelocity(push);
            otherPlayer.setVelocity(push.multiply(-1));
            nbp.increasePlayerAttempts(player);
        });
    }

    private void checkPlayerGroups(Player player, List<Entity> nearbyPlayers, int maxGroup) {
        List<Player> players = new ArrayList<>();
        int playersCount = (int) nearbyPlayers.stream().filter(entity -> entity instanceof Player).map(entity -> (Player) entity).count();
        playersCount++;
        if(playersCount > maxGroup) {
            nearbyPlayers.stream().filter(entity -> entity instanceof Player).forEach(entity -> {
                Player otherPlayer = (Player)entity;
                players.add(otherPlayer);
            });
            players.add(player);
            players.forEach(target -> {
                target.setHealth(0);
                Messager.sendMessage(target, "&cHas muerto por estar en grupo, subnormal");
                nbp.setPlayerAttempts(target, 1);
            });
            Messager.broadcastMessage("&c¡Se ha detectado un grupo de más de &4"+maxGroup+"&c personas!");
        }
    }

    private void thunderPlayer(Player player) {
        double health = player.getHealth();
        while(health != 0) {
            player.setInvulnerable(false);
            player.setGameMode(GameMode.SURVIVAL);
            player.getWorld().spawnEntity(player.getLocation(), EntityType.LIGHTNING);
            health = player.getHealth();
        }
    }

}
