package com.github.justadeni.soulGravesBanco;

import org.bukkit.plugin.java.JavaPlugin;
import ovh.mythmc.banco.api.Banco;

public class SoulGravesBanco extends JavaPlugin {

    @Override
    public void onEnable() {
        Banco.get().getStorageRegistry().registerStorage(new SoulContainer());
    }

}