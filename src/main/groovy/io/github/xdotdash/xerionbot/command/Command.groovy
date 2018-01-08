package io.github.xdotdash.xerionbot.command

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class Command {

    static final String PREFIX = "!"

    protected final String name
    protected final Pattern spec
    protected final String usage

    Command(String name, String spec, String usage) {
        this.name = name
        this.spec = Pattern.compile(PREFIX + spec)
        this.usage = usage
    }

    abstract void execute(GuildMessageReceivedEvent event, Matcher matcher)
}