package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SaaSLightColorScheme = lightColorScheme(
    primary = SaaSPrimary,
    onPrimary = Color.White,
    primaryContainer = SaaSPrimaryContainer,
    onPrimaryContainer = SaaSPrimaryVariant,
    secondary = SaaSSuccess,
    onSecondary = Color.White,
    secondaryContainer = SaaSSuccessContainer,
    onSecondaryContainer = SaaSOnSuccessContainer,
    tertiary = SaaSWarning,
    onTertiary = Color.White,
    tertiaryContainer = SaaSWarningContainer,
    background = SaaSBackground,
    onBackground = SaaSTextPrimary,
    surface = SaaSSurface,
    onSurface = SaaSTextPrimary,
    surfaceVariant = SaaSSurfaceVariant,
    onSurfaceVariant = SaaSTextSecondary,
    outline = SaaSBorder,
    outlineVariant = SaaSBorderHover
)

private val SaaSDarkColorScheme = darkColorScheme(
    primary = Color(0xFF818CF8),
    onPrimary = Color(0xFF0F172A),
    primaryContainer = Color(0xFF1E1B4B),
    onPrimaryContainer = Color(0xFFE0E7FF),
    secondary = Color(0xFF34D399),
    onSecondary = Color(0xFF064E3B),
    secondaryContainer = Color(0xFF064E3B),
    onSecondaryContainer = Color(0xFFA7F3D0),
    tertiary = Color(0xFFFBBF24),
    background = Color(0xFF0B0F19),
    onBackground = Color(0xFFF8FAFC),
    surface = Color(0xFF111827),
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFF9CA3AF),
    outline = Color(0xFF374151)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = false, // Clean SaaS Light Theme by default
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) SaaSDarkColorScheme else SaaSLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
