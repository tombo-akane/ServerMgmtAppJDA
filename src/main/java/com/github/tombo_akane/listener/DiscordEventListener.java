package com.github.tombo_akane.listener;

import com.github.tombo_akane.ServerMgmtAppJDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordEventListener extends ListenerAdapter {
    public ServerMgmtAppJDA bot;
    public DiscordEventListener(ServerMgmtAppJDA bot) {
        this.bot = bot;
    }
}
