package com.github.justadeni.soulGravesBanco;

import dev.faultyfunctions.soulgraves.api.SoulGravesAPI;
import dev.faultyfunctions.soulgraves.utils.Soul;
import org.bukkit.inventory.ItemStack;
import ovh.mythmc.banco.api.storage.BancoContainer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SoulContainer extends BancoContainer {

    @Override
    protected Collection<ItemStack> get(UUID uuid) {
        return SoulGravesAPI.INSTANCE.getPlayerSouls(uuid).stream()
                .map(Soul::getInventory)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    protected ItemStack addItem(UUID uuid, ItemStack itemStack) {
        SoulGravesAPI.INSTANCE.getPlayerSouls(uuid).stream().findFirst()
                .ifPresent(soul -> {
                    soul.getInventory().add(itemStack);
                });

        return null;
    }

    @Override
    protected ItemStack removeItem(UUID uuid, ItemStack toRemove) {
        int remaining = toRemove.getAmount();

        for (Soul soul : SoulGravesAPI.INSTANCE.getPlayerSouls(uuid)) {
            Iterator<ItemStack> it = soul.getInventory().iterator();
            while (it.hasNext() && remaining > 0) {
                ItemStack stack = it.next();
                if (stack == null)
                    continue;

                if (stack.isSimilar(toRemove)) { // only same type, meta, etc.
                    if (stack.getAmount() <= remaining) {
                        remaining -= stack.getAmount();
                        it.remove(); // fully remove this stack
                    } else {
                        stack.setAmount(stack.getAmount() - remaining);
                        remaining = 0;
                    }
                }
            }
            if (remaining == 0) break;
        }

        return remaining == 0 ? null : new ItemStack(toRemove.getType(), remaining);
    }

    @Override
    public String friendlyName() {
        return "SOUL_GRAVE";
    }

}
