package me.DawnBudgie.PandoraSlots;

/*
 *
 * @Author DawnBudgie
 * 
 * Info: This plugin's main purpose is to provide a server que for users to enter a busy server.
 * The plugin utilizes a permission node to 'whitelist' a user and if the server is full, kick a person that does not
 * have said permission node. 
 * 
 */

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PandoraSlots
  extends JavaPlugin
  implements Listener
{
  private String kickMessage;
  
  public void onEnable()
  {
    saveDefaultConfig();
    this.kickMessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("kickmsg", "&cServer full! Please try again in a few minutes! &f:(")).replace("&&", "&");
    getServer().getPluginManager().registerEvents(this, this);
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onLogin(PlayerLoginEvent event)
  {
    if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
      if (event.getPlayer().hasPermission("PandoraSlots.bypass")) {
        event.allow();
      } else {
        event.setKickMessage(this.kickMessage);
      }
    }
  }
}
