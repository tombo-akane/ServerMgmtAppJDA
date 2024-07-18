package com.github.tombo_akane.listener;

import com.github.tombo_akane.ServerMgmtAppJDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;

public class DiscordEventListener extends ListenerAdapter {
    public ServerMgmtAppJDA bot;
    public DiscordEventListener(ServerMgmtAppJDA bot) {
        this.bot = bot;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        registerCommands(bot.getShardManager());
    }

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Hello " + event.getUser().getAsMention() + "! How are you?")
                    .setEphemeral(false)
                    .queue();
        }
    }

    private void registerCommands(ShardManager jda) {
        Guild g = jda.getGuildById("1163461717116858520");
        if (g != null) {
            CommandListUpdateAction commands = g.updateCommands();
            commands.addCommands(Commands.slash("hello", "How are you?")).queue();
        }
    }
}
