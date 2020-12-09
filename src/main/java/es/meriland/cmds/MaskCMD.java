package es.meriland.cmds;

import es.meriland.MaskBuilder;
import es.meriland.Messager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaskCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        player.getInventory().addItem(MaskBuilder.mask());
        Messager.sendMessage(player, "Has recibido una mascarilla de la ETSISI. Úsala para protegerte de los demás, y recuerda mantener la distancia de seguridad.");
        return true;
    }
}
