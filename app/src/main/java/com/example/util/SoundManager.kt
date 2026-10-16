package com.example.util

import android.content.Context
import android.media.AudioManager as AndroidAudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import java.util.Locale

class SoundManager(private val context: Context) : TextToSpeech.OnInitListener {

    private var toneGenerator: ToneGenerator? = null
    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    var isMuted = false

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    init {
        try {
            toneGenerator = ToneGenerator(AndroidAudioManager.STREAM_MUSIC, 85)
            tts = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.ENGLISH
            tts?.setSpeechRate(0.9f)
            isTtsReady = true
        }
    }

    fun playClickSound() {
        if (isMuted) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 50)
            vibrate(20)
        } catch (_: Exception) {}
    }

    fun playCorrectChime() {
        if (isMuted) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 150)
            vibrate(50)
        } catch (_: Exception) {}
    }

    fun playWrongBuzz() {
        if (isMuted) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 200)
            vibrate(100)
        } catch (_: Exception) {}
    }

    fun playFanfare() {
        if (isMuted) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 400)
            vibrate(150)
        } catch (_: Exception) {}
    }

    fun speak(text: String, isUrdu: Boolean = false) {
        if (isMuted || !isTtsReady || text.isBlank()) return
        try {
            if (isUrdu) {
                // Try Urdu locale if available, else English
                val urduLocale = Locale("ur")
                val supported = tts?.isLanguageAvailable(urduLocale)
                if (supported != null && supported >= TextToSpeech.LANG_AVAILABLE) {
                    tts?.language = urduLocale
                } else {
                    tts?.language = Locale.ENGLISH
                }
            } else {
                tts?.language = Locale.ENGLISH
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "quest_tts")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setSpeechRate(rate: Float) {
        tts?.setSpeechRate(rate.coerceIn(0.5f, 2.0f))
    }

    private fun vibrate(millis: Long) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(millis)
            }
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            toneGenerator?.release()
            toneGenerator = null
            tts?.stop()
            tts?.shutdown()
            tts = null
        } catch (_: Exception) {}
    }
}
