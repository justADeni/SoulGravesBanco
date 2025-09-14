package com.github.justadeni.soulGravesBanco.listeners;

import com.github.justadeni.soulGravesBanco.impl.SoulContainer;
import dev.faultyfunctions.soulgraves.api.event.SoulDeleteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ovh.mythmc.banco.api.Banco;

public class SoulDeleteListener implements Listener {

    @EventHandler
    public void onSoulDeleted(SoulDeleteEvent event) {
        Banco.get().getStorageRegistry().get().stream()
                .filter(bs -> bs instanceof SoulContainer)
                .map(bs -> (SoulContainer)bs)
                .filter(bs -> bs.getUUID().equals(event.getSoul().getMarkerUUID()))
                .findFirst()
                .ifPresent(sc -> Banco.get().getStorageRegistry().unregisterStorage(sc));
    }

}
