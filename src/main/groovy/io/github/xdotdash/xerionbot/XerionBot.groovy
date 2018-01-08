package io.github.xdotdash.xerionbot

import com.google.common.reflect.TypeToken
import io.github.xdotdash.xerionbot.command.EvalCommand
import io.github.xdotdash.xerionbot.command.ScriptCommand
import io.github.xdotdash.xerionbot.util.FileUtil
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.hooks.AnnotatedEventManager
import ninja.leaping.configurate.commented.CommentedConfigurationNode
import ninja.leaping.configurate.hocon.HoconConfigurationLoader

enum XerionBot {

    INSTANCE

    private final HoconConfigurationLoader loader =
            HoconConfigurationLoader.builder().setPath(FileUtil.CONFIG_FILE).build()
    private CommentedConfigurationNode node = loader.load()

    private Config config = node.getValue(TypeToken.of(Config.class))
    private JDA jda

    Config getConfig() {
        return config
    }

    JDA getJda() {
        return jda
    }

    static void main(String[] args) {
        INSTANCE.jda = new JDABuilder(AccountType.BOT)
                .setToken(INSTANCE.config.token)
                .setAudioEnabled(false)
                .setAutoReconnect(true)
                .setBulkDeleteSplittingEnabled(false)
                .setEnableShutdownHook(true)
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(new EvalCommand())
                .addEventListener(new ScriptCommand())
                .buildBlocking()

        Runtime.addShutdownHook {
            loader.save(node)
        }
    }
}