package com.github.justadeni.soulGravesBanco.listeners;

import com.github.justadeni.soulGravesBanco.impl.SoulContainer;
import dev.faultyfunctions.soulgraves.api.event.SoulSpawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ovh.mythmc.banco.api.Banco;

public class SoulCreateListener implements Listener {

    @EventHandler
    public void onSoulCreated(SoulSpawnEvent event) {
        Banco.get().getStorageRegistry().registerStorage(new SoulContainer(event.getSoul()));
    }

}
