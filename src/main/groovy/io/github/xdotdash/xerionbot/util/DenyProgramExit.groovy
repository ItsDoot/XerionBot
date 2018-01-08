package io.github.xdotdash.xerionbot.util

import org.kohsuke.groovy.sandbox.GroovyValueFilter

class DenyProgramExit extends GroovyValueFilter {

    @Override
    Object onMethodCall(Invoker invoker, Object receiver, String method, Object... args) throws Throwable {
        print(receiver.class.name + "." + method)
        if (receiver == Runtime.runtime && method == "exit") {
            throw new SecurityException("You are not allowed to exit the process.")
        }

        return super.onMethodCall(invoker, receiver, method, args)
    }

    @Override
    Object onStaticCall(Invoker invoker, Class receiver, String method, Object... args) throws Throwable {
        print(receiver.name + "." + method)
        if (receiver == System.class && method == "exit") {
            throw new SecurityException("You are not allowed to exit the process.")
        }

        return super.onStaticCall(invoker, receiver, method, args)
    }
}