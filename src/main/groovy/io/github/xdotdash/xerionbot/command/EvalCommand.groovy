package io.github.xdotdash.xerionbot.command

import io.github.xdotdash.xerionbot.util.ScriptingUtil
import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.SubscribeEvent

import java.util.regex.Matcher

class EvalCommand extends Command {

    EvalCommand() {
        super("eval", "eval `{3}([^`]+)`{3}", "!eval ```<expression>```")
    }

    @Override
    void execute(GuildMessageReceivedEvent event, Matcher matcher) {
        GroovyShell shell = new GroovyShell(ScriptingUtil.generateBinding(event), ScriptingUtil.compilerConfig)

        try {
            Object eval = shell.evaluate(matcher.group(1))

            if (eval == null) {
                event.channel.sendMessage("\uD83D\uDC4D").queue()
            } else {
                event.channel.sendMessage(new MessageBuilder().append("Result:\n").appendCodeBlock(eval.toString(), null).build()).queue()
            }
        } catch (Exception e) {
            event.channel.sendMessage("Error: `" + e.message + "`").queue()
        }
    }

    @SubscribeEvent
    void onCommand(GuildMessageReceivedEvent event) {
        if (!event.member.hasPermission(Permission.ADMINISTRATOR)) {
            return
        }

        String content = event.message.content

        if (!content.startsWith(PREFIX + name)) {
            return
        }

        Matcher matcher = spec.matcher(content)

        if (!matcher.find()) {
            event.channel.sendMessage("❌ | Correct Usage: " + usage).queue()
            return
        }

        execute(event, matcher)
    }
}
