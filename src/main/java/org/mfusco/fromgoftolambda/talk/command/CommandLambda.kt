package org.mfusco.fromgoftolambda.talk.command

fun logger(text: String) = println("logger $text")
fun fileSave(text: String) = println("save file $text")
fun mail(text: String) = println("mail $text")

fun commandSequence(vararg commands: () -> Unit) =
        { commands.forEach { it() } }

fun main(args: Array<String>) {
    val commands = commandSequence({ logger("ciao") }, { fileSave("miofile") }, { mail("Francesco") })
    commands()
}
