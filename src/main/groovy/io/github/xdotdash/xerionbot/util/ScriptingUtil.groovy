package io.github.xdotdash.xerionbot.util

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import org.codehaus.groovy.control.CompilerConfiguration
import org.kohsuke.groovy.sandbox.SandboxTransformer

class ScriptingUtil {

    static Binding generateBinding(GuildMessageReceivedEvent event) {
        Binding binding = new Binding()

        binding.event = event
        binding.author = event.author
        binding.channel = event.channel
        binding.guild = event.guild
        binding.jda = event.getJDA()
        binding.member = event.member
        binding.message = event.message

        return binding
    }

    static final CompilerConfiguration compilerConfig =
            new CompilerConfiguration().addCompilationCustomizers(new SandboxTransformer())

    static {
        new DenyTokenAccess().register()
        new DenyProgramExit().register()
    }
}