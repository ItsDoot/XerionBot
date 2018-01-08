package io.github.xdotdash.xerionbot.util

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import org.codehaus.groovy.control.CompilerConfiguration
import org.kohsuke.groovy.sandbox.SandboxTransformer

class ScriptingUtil {

    static Binding generateBinding(GuildMessageReceivedEvent event) {
        Binding binding = new Binding()

        binding.setVariable("event", event)
        binding.setVariable("author", event.author)
        binding.setVariable("channel", event.channel)
        binding.setVariable("guild", event.guild)
        binding.setVariable("jda", event.getJDA())
        binding.setVariable("member", event.member)
        binding.setVariable("message", event.message)

        return binding
    }

    static final CompilerConfiguration compilerConfig =
            new CompilerConfiguration().addCompilationCustomizers(new SandboxTransformer())

    static {
        new DenyTokenAccess().register()
    }
}