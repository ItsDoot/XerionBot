package io.github.xdotdash.xerionbot

import io.github.xdotdash.xerionbot.command.EvalCommand
import io.github.xdotdash.xerionbot.command.ScriptCommand
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.AnnotatedEventManager

enum XerionBot {

    INSTANCE

    private JDA jda

    JDA getJda() {
        return jda
    }

    static void main(String[] args) {
        if (args.length != 1) {
            println("Must include only 1 arg, which is the bot token.")
            return
        }

        INSTANCE.jda = new JDABuilder(AccountType.BOT)
                .setToken(args[0])
                .setAudioEnabled(false)
                .setAutoReconnect(true)
                .setBulkDeleteSplittingEnabled(false)
                .setEnableShutdownHook(true)
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(new EvalCommand())
                .addEventListener(new ScriptCommand())
                .buildBlocking()
    }
}
