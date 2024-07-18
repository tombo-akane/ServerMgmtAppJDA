package com.github.tombo_akane;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class ServerMgmtAppJDA {
    protected static ServerMgmtAppJDA selfBot;
    private ShardManager shardManager = null;
    public ServerMgmtAppJDA(String token) {
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

    private ShardManager buildShardManager(String token) throws LoginException {
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token);

        return builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}
