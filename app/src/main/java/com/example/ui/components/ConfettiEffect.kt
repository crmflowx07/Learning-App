package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class ConfettiParticle(
    val startX: Float,
    val startY: Float,
    val velocityX: Float,
    val velocityY: Float,
    val color: Color,
    val size: Float,
    val rotationSpeed: Float
)

@Composable
fun ConfettiOverlay(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    if (!isActive) return

    val particles = remember {
        val random = Random(42)
        val colors = listOf(
            Color(0xFFF59E0B), // Gold
            Color(0xFF06B6D4), // Cyan
            Color(0xFF6366F1), // Indigo
            Color(0xFF10B981), // Emerald
            Color(0xFFEC4899), // Pink
            Color(0xFFF97316)  // Orange
        )
        List(70) {
            ConfettiParticle(
                startX = random.nextFloat() * 1000f,
                startY = -50f,
                velocityX = (random.nextFloat() - 0.5f) * 300f,
                velocityY = 200f + random.nextFloat() * 500f,
                color = colors[random.nextInt(colors.size)],
                size = 8f + random.nextFloat() * 14f,
                rotationSpeed = (random.nextFloat() - 0.5f) * 10f
            )
        }
    }

    val transition = rememberInfiniteTransition(label = "confetti")
    val animProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "fall"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        particles.forEach { p ->
            val curX = (p.startX + p.velocityX * animProgress).mod(w)
            val curY = (p.startY + p.velocityY * animProgress).mod(h + 100f)

            drawCircle(
                color = p.color.copy(alpha = (1f - (animProgress * 0.4f)).coerceIn(0f, 1f)),
                radius = p.size,
                center = Offset(curX, curY)
            )
        }
    }
}
