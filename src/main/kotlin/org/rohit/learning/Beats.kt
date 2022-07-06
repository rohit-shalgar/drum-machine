package org.rohit.learning

import java.io.File
import javax.sound.sampled.AudioSystem
import kotlinx.coroutines.*


fun playBeats(beats: String, file: String) {
    val parts = beats.split("x")
    //println(parts)
    var count = 0

    for (part in parts) {
        count += part.length + 1
        if (part == "") {
            playsound(file)
        } else {
            Thread.sleep(100 * (part.length + 1L))
            if (count < beats.length) {
                playsound(file)
            }
        }
    }

}

fun playsound(file: String) {
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
fun main() {
    runBlocking {
        launch { playBeats("x-x-x-x-x-x---------x-x-x-x-x-x-xx-x-x", "crash_cymbal.aiff") }
        playBeats("x-x---------x----------x", "floor_toms.aiff")
        playBeats("x--x--x-----x---x---x--x", "toms.aiff")
    }

    GlobalScope.launch { playBeats("x-x-x-x-x-x---------x-x-x-x-x-x-xx-x-x", "crash_cymbal.aiff") }
    playBeats("x-x---------x----------x", "floor_toms.aiff")
    playBeats("x--x--x-----x---x---x--x", "toms.aiff")
}

