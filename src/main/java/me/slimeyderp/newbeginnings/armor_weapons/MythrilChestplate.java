package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MythrilChestplate extends NonDisenchantableSlimefunItem {
    public MythrilChestplate(Category category, SlimefunItemStack item, RecipeType recipeType,
                             ItemStack[] recipe, boolean hidden) {
        super(category, item, recipeType, recipe);
        this.hidden = hidden;
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();
        if (player.isSneaking()) {
            Bukkit.getScheduler().runTaskLater(NewBeginnings.getInstance(),
                () -> handleShiftClick(player,item), 2);
        }
    }

    private void handleShiftClick(Player p, ItemStack i) {
        PlayerInventory pi = p.getInventory();

        // If the player equipped the chestplate:

        if (p.getItemInHand().getType().equals(Material.AIR)) { pi.setChestplate(null); }

        if (hidden) {
            p.sendMessage("Chestplate Mode Activated");
            p.setItemInHand(ExtraItemStack.MYTHRIL_CHESTPLATE_STACK);
        } else {
            p.sendMessage("Elytra Mode Activated");
            p.setItemInHand(ExtraItemStack.MYTHRIL_CHESTPLATE_ELYTRA_STACK);
        }
    }
}
