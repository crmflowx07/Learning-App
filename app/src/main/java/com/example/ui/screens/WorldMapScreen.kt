package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.example.data.model.Landmark
import com.example.data.repository.CurriculumCatalog
import com.example.domain.engine.StageEngine
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun WorldMapScreen(
    state: UiState,
    onSelectStage: (Int) -> Unit,
    onLandmarkClick: (Landmark) -> Unit,
    onOpenPlacementTest: () -> Unit,
    onOpenCourseCatalog: () -> Unit,
    onOpenDailyChest: () -> Unit,
    onLaunchBossBattle: () -> Unit,
    onCompanionSpeak: (String) -> Unit,
    onSetDailyGoal: (Int) -> Unit = {}
) {
    val isUrdu = state.userProfile.languageCode == "ur"
    val activeStage = state.activeStage
    val stageCourses = CurriculumCatalog.allCourses.filter { it.stageId == activeStage.stageId }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. SaaS Hero / Welcome Card
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isUrdu) "خوش آمدید، ${state.userProfile.name}!" else "Welcome back, ${state.userProfile.name}!",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SaaSTextPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isUrdu) activeStage.worldDescription else "Your personalized learning curriculum from Prep to BSCS.",
                                style = MaterialTheme.typography.bodySmall,
                                color = SaaSTextSecondary
                            )
                        }

                        // Daily Bonus Pill
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (state.dailyChestOpened) SaaSSurfaceVariant else SaaSWarningContainer,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (state.dailyChestOpened) SaaSBorder else Color(0xFFFDE68A)
                            ),
                            modifier = Modifier
                                .clickable { onOpenDailyChest() }
                                .testTag("daily_chest_btn")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(if (state.dailyChestOpened) "🎁" else "⭐", fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (state.dailyChestOpened) "Claimed" else "+50 Stars",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (state.dailyChestOpened) SaaSTextSecondary else Color(0xFFB45309)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Continue learning quick action
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SaaSPrimaryContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(SaaSPrimary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = if (isUrdu) "تعلیمی سلسلہ جاری رکھیں" else "Continue Current Track",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = SaaSPrimary
                                    )
                                    Text(
                                        text = "${activeStage.name} • ${activeStage.levelLabel}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = SaaSTextSecondary
                                    )
                                }
                            }

                            Button(
                                onClick = onOpenCourseCatalog,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                modifier = Modifier.testTag("continue_learning_btn")
                            ) {
                                Text(if (isUrdu) "شروع کریں" else "Resume", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // 2. Stage Switcher Tabs (8 Stages)
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isUrdu) "تعلیمی مراحل (Prep تا BSCS)" else "Academic Stages (Prep to BSCS)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )

                    TextButton(onClick = onOpenPlacementTest) {
                        Icon(Icons.Default.Tune, contentDescription = null, modifier = Modifier.size(16.dp), tint = SaaSPrimary)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (isUrdu) "تشخیصی ٹیسٹ" else "Placement Test", fontSize = 12.sp, color = SaaSPrimary)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CurriculumCatalog.stages.forEach { stage ->
                        val isSelected = stage.stageId == activeStage.stageId
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) SaaSPrimary else SaaSSurface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) SaaSPrimary else SaaSBorder
                            ),
                            modifier = Modifier
                                .clickable { onSelectStage(stage.stageId) }
                                .testTag("stage_tab_${stage.stageId}")
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Stage ${stage.stageId}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (isSelected) Color.White else SaaSTextPrimary
                                )
                                Text(
                                    text = stage.levelLabel,
                                    fontSize = 10.sp,
                                    color = if (isSelected) Color.White.copy(alpha = 0.85f) else SaaSTextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. SaaS Metrics Grid (Key Learning Stats)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Metric 1: Completed Quests
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(if (isUrdu) "مکمل شدہ" else "Completed", style = MaterialTheme.typography.labelSmall, color = SaaSTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${state.completedLessonIds.size} Quests",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSPrimary
                        )
                    }
                }

                // Metric 2: Streak
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(if (isUrdu) "تسلسل" else "Streak", style = MaterialTheme.typography.labelSmall, color = SaaSTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${state.userProfile.currentStreak} Days 🔥",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD97706)
                        )
                    }
                }

                // Metric 3: Screen Time
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(if (isUrdu) "اسکرین وقت" else "Screen Time", style = MaterialTheme.typography.labelSmall, color = SaaSTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${state.userProfile.minutesUsedToday}m / ${state.userProfile.dailyLimitMinutes}m",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (state.isScreenTimeLimitReached) SaaSError else SaaSOnSuccessContainer
                        )
                    }
                }
            }
        }

        // 3.5 Daily Learning Goal & 7-Day Streak Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🎯", fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = if (isUrdu) "روزانہ سیکھنے کا ہدف" else "Daily Learning Target",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSTextPrimary
                                )
                                Text(
                                    text = if (isUrdu) "آج ${state.userProfile.minutesUsedToday} منٹ / ${state.dailyGoalMinutes} منٹ مکمل" else "${state.userProfile.minutesUsedToday}m of ${state.dailyGoalMinutes}m completed today",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SaaSTextSecondary
                                )
                            }
                        }

                        // Target Selector Pills
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            listOf(10, 15, 20, 30).forEach { mins ->
                                val isSelected = state.dailyGoalMinutes == mins
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSelected) SaaSPrimary else SaaSSurfaceVariant,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SaaSPrimary else SaaSBorder),
                                    modifier = Modifier
                                        .clickable { onSetDailyGoal(mins) }
                                ) {
                                    Text(
                                        text = "${mins}m",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else SaaSTextSecondary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Progress bar
                    val goalFraction = (state.userProfile.minutesUsedToday.toFloat() / state.dailyGoalMinutes.coerceAtLeast(1).toFloat()).coerceIn(0f, 1f)
                    LinearProgressIndicator(
                        progress = { goalFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (goalFraction >= 1f) SaaSSuccess else SaaSPrimary,
                        trackColor = SaaSBorder
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // 7-Day Habit Tracker
                    Text(
                        text = if (isUrdu) "ہفتہ وار تسلسل (پیر تا اتوار)" else "Weekly Consistency (Mon – Sun)",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaaSTextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                        val urduDays = listOf("پیر", "منگل", "بدھ", "جمعرات", "جمعہ", "ہفتہ", "اتوار")
                        days.forEachIndexed { idx, day ->
                            val isPastActive = idx <= ((state.userProfile.currentStreak - 1) % 7)
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(if (isPastActive) SaaSSuccessContainer else SaaSSurfaceVariant)
                                        .border(
                                            1.dp,
                                            if (isPastActive) Color(0xFFA7F3D0) else SaaSBorder,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isPastActive) "✓" else "•",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = if (isPastActive) SaaSOnSuccessContainer else SaaSTextMuted
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (isUrdu) urduDays[idx] else day,
                                    fontSize = 10.sp,
                                    color = SaaSTextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // 4. Milestone Assessment Challenge Banner
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLaunchBossBattle() }
                    .testTag("milestone_assessment_btn")
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFEF3C7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🎯", fontSize = 22.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isUrdu) "مرحلہ وار تشخیصی چیلنج" else "Stage Milestone Assessment",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = SaaSTextPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = SaaSSuccessContainer
                            ) {
                                Text(
                                    text = "+150 XP",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSOnSuccessContainer,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = if (isUrdu) "یونٹ کے اہم سوالات حل کریں اور قابلیت کا سرٹیفکیٹ حاصل کریں" else "Validate your topic mastery with timed diagnostic questions",
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSTextSecondary
                        )
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = SaaSTextMuted)
                }
            }
        }

        // 5. Active Stage Learning Modules (List of Courses)
        item {
            Text(
                text = if (isUrdu) "کورس ماڈیولز برائے ${activeStage.urduName}" else "Curriculum Modules • ${activeStage.levelLabel}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        items(stageCourses) { course ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenCourseCatalog() }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                when {
                                    course.subject.contains("Math") -> Color(0xFFFEF3C7)
                                    course.subject.contains("Computer") || course.subject.contains("CS") || course.subject.contains("DSA") -> SaaSPrimaryContainer
                                    course.subject.contains("English") -> Color(0xFFECFDF5)
                                    else -> Color(0xFFF3E8FF)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when {
                                course.subject.contains("Math") -> "📐"
                                course.subject.contains("Computer") || course.subject.contains("CS") || course.subject.contains("DSA") -> "💻"
                                course.subject.contains("English") -> "🔤"
                                course.subject.contains("Urdu") -> "📖"
                                else -> "🌟"
                            },
                            fontSize = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isUrdu) course.urduTitle else course.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = course.subject,
                                style = MaterialTheme.typography.labelSmall,
                                color = SaaSPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "•  ${course.units.sumOf { it.lessons.size }} Lessons",
                                style = MaterialTheme.typography.labelSmall,
                                color = SaaSTextSecondary
                            )
                        }
                    }

                    Icon(Icons.Default.ArrowForward, contentDescription = "Open", tint = SaaSTextSecondary, modifier = Modifier.size(20.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
