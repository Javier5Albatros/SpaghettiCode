package es.meriland;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NearbyPlayerTracker {
    private HashMap<Player, Integer> map;
    private int maxAttempts;
    private Set<Player> set;

    public NearbyPlayerTracker(int maxAttempts) {
        setMaxAttempts(maxAttempts);
        map = new HashMap<>();
        set = new HashSet<>();
    }

    public void setMaxAttempts(int maxAttempts) { this.maxAttempts = maxAttempts; }

    public int getMaxAttempts() { return this.maxAttempts; }

    public boolean isInSet(Player player) {
        return set.contains(player);
    }

    public void addRemoveSet(Player player) {
        set.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                set.remove(player);
            }
        }.runTaskLater(SpaghettiCode.getInstance(), 10L);
    }

    public void increasePlayerAttempts(Player player) {
        if(!map.containsKey(player)) {
            setPlayerAttempts(player, 1);
        } else if(getPlayerAttempts(player) < getMaxAttempts()) {
            setPlayerAttempts(player, getPlayerAttempts(player)+1);
        } else {
            setPlayerAttempts(player, 1);
        }
        handleNearbyPlayer(player);
    }

    public int getPlayerAttempts(Player player) {
        if(map.get(player) != null) {
            return map.get(player);
        } else {
            return -1;
        }
    }

    public void setPlayerAttempts(Player player, int maxAttempts) {
        map.put(player, maxAttempts);
    }

    private void handleNearbyPlayer(Player player) {
        switch (getPlayerAttempts(player)) {
            case 1:
                Messager.sendMessage(player, "No deberías acercarte a los demás...");
                break;
            case 2:
                Messager.sendMessage(player, "Te estamos avisando, a la siguiente te cae un evalúa");
                break;
            case 3:
                Messager.sendMessage(player, "Parece que eres más duro que Algorítmica... Y mira que vas por la tercera matrícula");
                break;
            case 4:
                Messager.sendMessage(player, "*ETSISI Enfurecida intensifies*");
                break;
            case 5:
                Messager.sendMessage(player, "Tú te lo has ganado. Segunda Matrícula para ti, tontito");
                player.setHealth(0);
                Messager.broadcastMessage(player.getDisplayName() + " &ces un terrorista. Ha sido atado y vacunado por Fernando Simón");
                break;
            default:
                Messager.sendMessage(player, "Tú eres un poco pesado no?");
                break;
        }
    }
}
