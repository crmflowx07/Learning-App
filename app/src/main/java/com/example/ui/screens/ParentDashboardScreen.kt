package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.CurriculumCatalog
import com.example.domain.engine.StageEngine
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun ParentDashboardScreen(
    state: UiState,
    onBack: () -> Unit,
    onUpdateDailyLimit: (Int) -> Unit,
    onOverrideStage: (Int) -> Unit
) {
    BackHandler { onBack() }
    val isUrdu = state.userProfile.languageCode == "ur"
    var screenLimit by remember(state.userProfile.dailyLimitMinutes) {
        mutableFloatStateOf(state.userProfile.dailyLimitMinutes.toFloat())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
    ) {
        // SaaS Header
        Surface(
            color = SaaSSurface,
            tonalElevation = 1.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.testTag("parent_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = SaaSTextPrimary
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = if (isUrdu) "والدین کا ایڈمن کنسول" else "Parent Administration Console",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )
                    Text(
                        text = "Safety controls, screen limits & curriculum overrides",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaaSTextSecondary
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Learner Overview Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Student Profile",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSPrimaryContainer
                        ) {
                            Text(
                                text = "Stage ${state.userProfile.assignedStage} Active",
                                color = SaaSPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = state.userProfile.name, fontWeight = FontWeight.Bold, color = SaaSTextPrimary)
                            Text(text = "Age: ${state.userProfile.age} years", color = SaaSTextSecondary, fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${state.completedLessonIds.size} Lessons Completed",
                                color = SaaSSuccess,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Streak: ${state.userProfile.currentStreak} days 🔥",
                                color = SaaSTextSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Screen-Time Rule Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Timer, contentDescription = null, tint = SaaSPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isUrdu) "یومیہ اسکرین وقت کی حد" else "Daily Screen-Time Limit",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${screenLimit.toInt()} minutes / day (Consumed: ${state.userProfile.minutesUsedToday} min)",
                        color = if (state.isScreenTimeLimitReached) SaaSError else SaaSTextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Slider(
                        value = screenLimit,
                        onValueChange = { screenLimit = it },
                        onValueChangeFinished = { onUpdateDailyLimit(screenLimit.toInt()) },
                        valueRange = 15f..180f,
                        steps = 10,
                        colors = SliderDefaults.colors(
                            thumbColor = SaaSPrimary,
                            activeTrackColor = SaaSPrimary,
                            inactiveTrackColor = SaaSBorder
                        ),
                        modifier = Modifier.testTag("screen_time_slider")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("15 min", color = SaaSTextSecondary, fontSize = 11.sp)
                        Text("180 min (3 hrs)", color = SaaSTextSecondary, fontSize = 11.sp)
                    }
                }
            }

            // Stage Override Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Tune, contentDescription = null, tint = SaaSPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isUrdu) "مرحلہ ایڈجسٹمنٹ (Parent Override)" else "Manual Stage Adjustment",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Manually promote or calibrate your learner's academic stage:",
                        style = MaterialTheme.typography.bodySmall,
                        color = SaaSTextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    CurriculumCatalog.stages.forEach { stage ->
                        val isCurrent = stage.stageId == state.userProfile.assignedStage
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isCurrent) SaaSPrimaryContainer else SaaSSurfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isCurrent) SaaSPrimary else SaaSBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Stage ${stage.stageId}: ${stage.levelLabel}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = SaaSTextPrimary
                                    )
                                    Text(
                                        text = stage.name,
                                        fontSize = 11.sp,
                                        color = SaaSTextSecondary
                                    )
                                }

                                if (isCurrent) {
                                    Text(
                                        text = "Active",
                                        color = SaaSPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                } else {
                                    TextButton(
                                        onClick = { onOverrideStage(stage.stageId) },
                                        modifier = Modifier.testTag("override_stage_${stage.stageId}")
                                    ) {
                                        Text("Set Stage", fontSize = 12.sp, color = SaaSPrimary)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Safety Policy
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = SaaSSuccess)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Safety & Compliance Policy",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    listOf(
                        "✓ 100% Ad-Free learning experience",
                        "✓ Zero third-party behavioral trackers",
                        "✓ Secure local-first on-device data persistence",
                        "✓ Parental authorization gate enforced for settings"
                    ).forEach { rule ->
                        Text(
                            text = rule,
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSTextSecondary,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
