package net.earthmc.quarters.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.earthmc.quarters.api.QuartersAPI;
import net.earthmc.quarters.api.QuartersMessaging;
import net.earthmc.quarters.object.Quarter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

@CommandAlias("quarters|q")
public class HereCommand extends BaseCommand {
    @Subcommand("here")
    @Description("Get info about the quarter you are standing in")
    @CommandPermission("quarters.command.here")
    public void onHere(Player player) {
        if (!QuartersAPI.getInstance().isPlayerInQuarter(player)) {
            QuartersMessaging.sendErrorMessage(player, "You are not standing within a quarter");
            return;
        }

        Quarter quarter = QuartersAPI.getInstance().getQuarterAtLocation(player.getLocation());

        TextComponent component = Component.text()
                .append(Component.text("Owner: ").color(NamedTextColor.DARK_GRAY).decorate(TextDecoration.ITALIC))
                .append(Component.text(quarter.getOwner() == null ? "null" : quarter.getOwner().getName() + "\n")).color(NamedTextColor.GRAY)
                .build();

        QuartersMessaging.sendInfoWall(player, component);
    }
}
