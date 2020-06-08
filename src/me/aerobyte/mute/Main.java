package me.aerobyte.mute;

import java.util.ArrayList;

import me.aerobyte.mute.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public ArrayList<Player> mute = new ArrayList<Player>();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMute(AsyncPlayerChatEvent e) {
        if (mute.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Utils.chat("&cYou are currently muted."));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("mute")) {
            if (args.length == 1) {
                player.sendMessage(Utils.chat("&cUsage: /mute <player>"));
                return true;
            }else {
                Player mutePlayer = Bukkit.getPlayer(args[0]);
                if (mutePlayer == null) {
                    player.sendMessage(Utils.chat("&f" + mutePlayer.getName() + "&cis not online."));
                    return true;
                }
                mute.add(mutePlayer);
                player.sendMessage(Utils.chat("&cYou have muted &f" + mutePlayer.getName()));
                mutePlayer.sendMessage(Utils.chat("&cYou have been muted by &f" + player.getName()));
                return true;
            }
        }
        return false;
    }
}