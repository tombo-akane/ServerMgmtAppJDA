package com.github.tombo_akane;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(new Option("h", "help", false, "Displays this help menu."));
        options.addOption(new Option("t", "token", true, "Provide the token during startup."));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            // help引数が存在するかどうか.
            if (cmd.hasOption("help")) {
                formatter.printHelp("Help Menu", options);
                System.exit(0);
            }

            // token引数が存在するかどうか. なければnullを返す.
            String token = cmd.hasOption("token") ? cmd.getOptionValue("token") : null;
            if (token == null) {
                System.out.println("ERROR: No token provided, please provide a token using the -t or --token flag.");
                formatter.printHelp("", options);
                System.exit(0);
            }

            // すべて通過したらBot起動、tokenを2番目のclassに送信.
            ServerMgmtAppJDA.selfBot = new ServerMgmtAppJDA(token);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("", options);
            System.exit(0);
        }
    }
}