package com.github.justadeni.soulGravesBanco.impl;

import dev.faultyfunctions.soulgraves.utils.Soul;
import org.bukkit.inventory.ItemStack;
import ovh.mythmc.banco.api.storage.BancoContainer;

import java.util.Collection;
import java.util.UUID;

public class SoulContainer extends BancoContainer {

    private final Soul soul;

    public SoulContainer(Soul soul) {
        this.soul = soul;
    }

    public UUID getUUID() {
        return soul.getMarkerUUID();
    }

    @Override
    protected Collection<ItemStack> get(UUID uuid) {
        return soul.getInventory();
    }

    // Refusal to store any new items
    @Override
    protected ItemStack addItem(UUID uuid, ItemStack itemStack) {
        return itemStack;
    }

    @Override
    protected ItemStack removeItem(UUID uuid, ItemStack toRemove) {
        if (toRemove == null) return null;
        int amountToRemove = toRemove.getAmount();

        for (int i = 0; i < soul.getInventory().size(); i++) {
            ItemStack slot = soul.getInventory().get(i);
            if (slot != null && slot.equals(toRemove)) {
                int currentAmt = slot.getAmount();

                if (currentAmt <= amountToRemove) {
                    // remove whole stack
                    slot.setAmount(0);
                    amountToRemove -= currentAmt;
                } else {
                    // partially remove
                    slot.setAmount(currentAmt - amountToRemove);
                    amountToRemove = 0;
                    break;
                }
            }
        }

        // Removed everything
        if (amountToRemove <= 0) {
            return null;
        }

        // Couldn't remove everything, return leftover
        ItemStack leftover = toRemove.clone();
        leftover.setAmount(amountToRemove);
        return leftover;
    }

    @Override
    public String friendlyName() {
        return "SOUL_GRAVE";
    }

}
