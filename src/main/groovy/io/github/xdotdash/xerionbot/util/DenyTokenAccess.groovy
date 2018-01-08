package io.github.xdotdash.xerionbot.util

import io.github.xdotdash.xerionbot.XerionBot
import org.kohsuke.groovy.sandbox.GroovyValueFilter

class DenyTokenAccess extends GroovyValueFilter {

    @Override
    Object filterReturnValue(Object returnValue) {
        if (XerionBot.INSTANCE.jda.token.equalsIgnoreCase(returnValue.toString())) {
            throw new SecurityException("You cannot see my token!")
        }
        return returnValue
    }
}
