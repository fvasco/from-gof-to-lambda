package org.mfusco.fromgoftolambda.talk.chainofresponsibility

fun parseAudio(file: File) = if (file.type == File.Type.AUDIO) "Audio file: ${file.content}" else null
fun parsePresentation(file: File) = if (file.type == File.Type.PRESENTATION) "Presentation file: ${file.content}" else null
fun parseText(file: File) = if (file.type == File.Type.TEXT) "Text file: ${file.content}" else null
fun parseVideo(file: File) = if (file.type == File.Type.VIDEO) "Video file: ${file.content}" else null

tailrec fun recursive(file: File, functions: List<Function1<File, String?>>): String? =
        if (functions.isEmpty()) {
            null
        } else {
            functions.first()(file) ?: recursive(file, functions.drop(1))
        }

fun main(args: Array<String>) {
    val file = File(File.Type.AUDIO, "Dream Theater  - The Astonishing")
    val functions = arrayOf(::parseText, ::parsePresentation, ::parseAudio, ::parseVideo)

    val result = functions.asIterable().map { it(file) }.filterNotNull().firstOrNull()
    println("Result $result")

    val resultRecursive = recursive(file, functions.toList())
    println("Result recursive $resultRecursive")
}
