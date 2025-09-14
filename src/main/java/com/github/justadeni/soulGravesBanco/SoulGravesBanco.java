package com.github.justadeni.soulGravesBanco;

import com.github.justadeni.soulGravesBanco.listeners.SoulCreateListener;
import com.github.justadeni.soulGravesBanco.listeners.SoulDeleteListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulGravesBanco extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SoulCreateListener(), this);
        getServer().getPluginManager().registerEvents(new SoulDeleteListener(), this);
    }

}