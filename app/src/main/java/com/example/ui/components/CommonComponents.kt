package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.ScreenRoute
import com.example.ui.viewmodel.UiState

@Composable
fun SaaSTopBar(
    state: UiState,
    onToggleLanguage: () -> Unit,
    onOpenParentGate: () -> Unit,
    onOpenProfile: () -> Unit
) {
    val isUrdu = state.userProfile.languageCode == "ur"

    Surface(
        color = SaaSSurface,
        tonalElevation = 1.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Brand & User Info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onOpenProfile() }
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .testTag("profile_chip")
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SaaSPrimaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (state.userProfile.assignedStage <= 2) "🧒" else if (state.userProfile.assignedStage <= 6) "🧑‍🎓" else "💻",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = state.userProfile.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSPrimaryContainer
                        ) {
                            Text(
                                text = "Lvl ${1 + state.userProfile.xp / 100}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SaaSPrimary,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Text(
                        text = "Stage ${state.activeStage.stageId} • ${state.activeStage.levelLabel}",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaaSTextSecondary
                    )
                }
            }

            // Stats & Actions Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak Badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SaaSWarningContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("🔥", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${state.userProfile.currentStreak}d",
                            color = Color(0xFFB45309),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                // Currency Badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SaaSSuccessContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFA7F3D0))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("⭐", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${state.userProfile.coins}",
                            color = SaaSOnSuccessContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                // Language toggle
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SaaSSurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.clickable { onToggleLanguage() }.testTag("lang_toggle_btn")
                ) {
                    Text(
                        text = if (isUrdu) "EN" else "اردو",
                        color = SaaSPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                    )
                }

                // Parent Controls Button
                IconButton(
                    onClick = onOpenParentGate,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SaaSPrimaryContainer)
                        .testTag("parent_gate_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = "Parent Settings",
                        tint = SaaSPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SaaSBottomNav(
    currentRoute: ScreenRoute,
    onNavigate: (ScreenRoute) -> Unit,
    isUrdu: Boolean
) {
    Surface(
        color = SaaSSurface,
        tonalElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        NavigationBar(
            containerColor = SaaSSurface,
            tonalElevation = 0.dp,
            modifier = Modifier.height(60.dp)
        ) {
            NavigationBarItem(
                selected = currentRoute == ScreenRoute.WORLD_MAP,
                onClick = { onNavigate(ScreenRoute.WORLD_MAP) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == ScreenRoute.WORLD_MAP) Icons.Filled.Dashboard else Icons.Outlined.Dashboard,
                        contentDescription = "Dashboard"
                    )
                },
                label = { Text(if (isUrdu) "ڈیش بورڈ" else "Dashboard", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaaSPrimary,
                    selectedTextColor = SaaSPrimary,
                    indicatorColor = SaaSPrimaryContainer,
                    unselectedIconColor = SaaSTextSecondary,
                    unselectedTextColor = SaaSTextSecondary
                ),
                modifier = Modifier.testTag("nav_dashboard")
            )

            NavigationBarItem(
                selected = currentRoute == ScreenRoute.COURSE_CATALOG,
                onClick = { onNavigate(ScreenRoute.COURSE_CATALOG) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == ScreenRoute.COURSE_CATALOG) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                        contentDescription = "Curriculum"
                    )
                },
                label = { Text(if (isUrdu) "نصاب" else "Curriculum", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaaSPrimary,
                    selectedTextColor = SaaSPrimary,
                    indicatorColor = SaaSPrimaryContainer,
                    unselectedIconColor = SaaSTextSecondary,
                    unselectedTextColor = SaaSTextSecondary
                ),
                modifier = Modifier.testTag("nav_curriculum")
            )

            NavigationBarItem(
                selected = currentRoute == ScreenRoute.REWARDS_SHOP,
                onClick = { onNavigate(ScreenRoute.REWARDS_SHOP) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == ScreenRoute.REWARDS_SHOP) Icons.Filled.Storefront else Icons.Outlined.Storefront,
                        contentDescription = "Rewards"
                    )
                },
                label = { Text(if (isUrdu) "انعامات" else "Rewards", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaaSPrimary,
                    selectedTextColor = SaaSPrimary,
                    indicatorColor = SaaSPrimaryContainer,
                    unselectedIconColor = SaaSTextSecondary,
                    unselectedTextColor = SaaSTextSecondary
                ),
                modifier = Modifier.testTag("nav_rewards")
            )

            NavigationBarItem(
                selected = currentRoute == ScreenRoute.PROFILE_BADGES,
                onClick = { onNavigate(ScreenRoute.PROFILE_BADGES) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == ScreenRoute.PROFILE_BADGES) Icons.Filled.WorkspacePremium else Icons.Outlined.WorkspacePremium,
                        contentDescription = "Credentials"
                    )
                },
                label = { Text(if (isUrdu) "اسناد" else "Credentials", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaaSPrimary,
                    selectedTextColor = SaaSPrimary,
                    indicatorColor = SaaSPrimaryContainer,
                    unselectedIconColor = SaaSTextSecondary,
                    unselectedTextColor = SaaSTextSecondary
                ),
                modifier = Modifier.testTag("nav_badges")
            )
        }
    }
}

@Composable
fun ParentalGateDialog(
    onDismiss: () -> Unit,
    onSuccess: () -> Unit,
    isUrdu: Boolean
) {
    val num1 = remember { 7 }
    val num2 = remember { 8 }
    val expected = (num1 * num2).toString()
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Security, contentDescription = null, tint = SaaSPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isUrdu) "والدین کی توثیق" else "Parental Access Verification",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SaaSTextPrimary
                )
            }
        },
        text = {
            Column {
                Text(
                    text = if (isUrdu)
                        "بچوں کے محفوظ تجربے کے لیے تصدیقی سوال حل کریں:"
                    else
                        "Please verify your authorization to access parent controls and metrics:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SaaSTextSecondary
                )
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = SaaSPrimaryContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(14.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "$num1 × $num2 = ?",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = SaaSPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        error = false
                    },
                    label = { Text(if (isUrdu) "جواب درج کریں" else "Enter answer") },
                    isError = error,
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("parent_gate_input")
                )
                if (error) {
                    Text(
                        text = if (isUrdu) "غلط جواب! دوبارہ کوشش کریں۔" else "Incorrect answer. Try again.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (input.trim() == expected) {
                        onSuccess()
                    } else {
                        error = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("parent_gate_submit")
            ) {
                Text(if (isUrdu) "تصدیق کریں" else "Verify & Unlock")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(if (isUrdu) "منسوخ" else "Cancel", color = SaaSTextSecondary)
            }
        },
        containerColor = SaaSSurface,
        shape = RoundedCornerShape(14.dp)
    )
}
