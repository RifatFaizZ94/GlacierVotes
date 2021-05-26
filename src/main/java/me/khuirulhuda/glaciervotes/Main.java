package me.khuirulhuda.glaciervotes;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import me.khuirulhuda.glaciervotes.events.*;
import me.khuirulhuda.glaciervotes.commands.*;
import org.bukkit.ChatColor;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;


public class Main extends JavaPlugin implements Listener {
  
  private File customConfigFile;
  private FileConfiguration customConfig;
  private static Main INSTANCE;
  
    @Override
    public void onEnable() {
        this.getCommand("reload").setExecutor(new Reload());
        Bukkit.getPluginManager().registerEvents(new Vote(), this);
        INSTANCE = this;
        this.getLogger().info(ChatColor.GREEN+"GlacierVotes Successfully Enabled");
        //createCustomConfig();
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        this.getLogger().info("GlacierVotes RenderyCrafty Successfully Disabled");
    }
    
    public static Main getInstance() {
      return INSTANCE;
    }
    
    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }
    
   
    
    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
         }
        
        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}