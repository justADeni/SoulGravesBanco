package com.github.justadeni.soulGravesBanco;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulGravesBanco extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SoulCreateListener(), this);
        getServer().getPluginManager().registerEvents(new SoulDeleteListener(), this);
    }

}