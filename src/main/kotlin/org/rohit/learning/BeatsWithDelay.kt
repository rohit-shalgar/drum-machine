package org.rohit.learning

import java.io.File
import javax.sound.sampled.AudioSystem
import kotlinx.coroutines.*

/*
    Thread.sleep stops the thread
    but delay , delays a coroutine
    methods with suspend keyword can call the delay function.
    runblocking - one thread runs all the couroutines.
 */
suspend fun playBeatsWithDelay(beats: String, file: String) {
    val parts = beats.split("x")
    println(parts)
    var count = 0

    for (part in parts) {
        count += part.length + 1
        if (part == "") {
            playsoundWithDelay(file)
        } else {
            delay(500)
            if (count < beats.length) {
                playsoundWithDelay(file)
            }
        }
    }

}

fun playsoundWithDelay(file: String) {
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(
        File(
            file
        )
    )
    clip.open(audioInputStream)
    clip.start()
}

@OptIn(DelicateCoroutinesApi::class)
suspend fun main() {
    runBlocking {
        launch { playBeatsWithDelay("x-x-x-x-x-x---------x-x-x-x-x-x-xx-x-x", "crash_cymbal.aiff") }
        playBeatsWithDelay("x-x---------x----------x", "floor_toms.aiff")
        playBeatsWithDelay("x--x--x-----x---x---x--x", "toms.aiff")
    }

}

