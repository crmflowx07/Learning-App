package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WorkspacePremium
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
import com.example.data.repository.CurriculumCatalog
import com.example.domain.engine.StageEngine
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun ProfileBadgesScreen(
    state: UiState,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val isUrdu = state.userProfile.languageCode == "ur"
    val badges = CurriculumCatalog.sampleBadges

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
                IconButton(onClick = onBack, modifier = Modifier.testTag("badges_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = SaaSTextPrimary
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = if (isUrdu) "تعلیمی اسناد و بیجز" else "Learner Credentials & Achievements",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )
                    Text(
                        text = "${state.userProfile.name} • Level ${1 + state.userProfile.xp / 100} Scholar",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaaSTextSecondary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(SaaSPrimaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (state.userProfile.assignedStage <= 2) "🧒" else if (state.userProfile.assignedStage <= 6) "🧑‍🎓" else "💻",
                                fontSize = 32.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = state.userProfile.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )

                        Text(
                            text = StageEngine.getStageBadge(state.userProfile.assignedStage, isUrdu),
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSPrimary,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("🔥 ${state.userProfile.currentStreak}", fontWeight = FontWeight.Bold, color = SaaSWarning, fontSize = 16.sp)
                                Text("Day Streak", color = SaaSTextSecondary, fontSize = 11.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("⭐ ${state.userProfile.coins}", fontWeight = FontWeight.Bold, color = SaaSOnSuccessContainer, fontSize = 16.sp)
                                Text("Stars Earned", color = SaaSTextSecondary, fontSize = 11.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("⚡ ${state.userProfile.xp}", fontWeight = FontWeight.Bold, color = SaaSPrimary, fontSize = 16.sp)
                                Text("Academic XP", color = SaaSTextSecondary, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }

            // Milestone Certificate
            item {
                var showCertificateDialog by remember { mutableStateOf(false) }

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSPrimaryContainer),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC7D2FE)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCertificateDialog = true }
                        .testTag("certificate_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = SaaSPrimary, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = if (isUrdu) "سرکاری تعلیمی سند" else "Certificate of Curriculum Progress",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = SaaSPrimary
                                    )
                                    Text(
                                        text = "Stage ${state.userProfile.assignedStage}: ${state.activeStage.levelLabel}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SaaSTextSecondary
                                    )
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SaaSSurface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder)
                            ) {
                                Text(
                                    text = if (isUrdu) "دیکھیں 📄" else "View 📄",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isUrdu) "طالب علم ${state.userProfile.name} کے شاندار تعلیمی نتائج اور مسلسل محنت کی تصدیق کی جاتی ہے۔" else "Presented to ${state.userProfile.name} in recognition of consistent learning and academic mastery across foundational and advanced subjects.",
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSTextPrimary,
                            lineHeight = 18.sp
                        )
                    }
                }

                // Official Certificate Dialog
                if (showCertificateDialog) {
                    AlertDialog(
                        onDismissRequest = { showCertificateDialog = false },
                        confirmButton = {
                            Button(
                                onClick = { showCertificateDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(if (isUrdu) "بند کریں" else "Close Certificate")
                            }
                        },
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🏆", fontSize = 22.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isUrdu) "سرکاری اعزازی سند" else "Official Academic Certificate",
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSTextPrimary
                                )
                            }
                        },
                        text = {
                            Surface(
                                color = SaaSSurface,
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFFDE68A)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("⭐ QUESTLEARN ACADEMY ⭐", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD97706))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = state.userProfile.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = SaaSPrimary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "has demonstrated verified mastery in",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = SaaSTextSecondary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Stage ${state.activeStage.stageId}: ${state.activeStage.name}",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = SaaSTextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = SaaSSuccessContainer
                                    ) {
                                        Text(
                                            text = "✓ VERIFIED • ${state.completedLessonIds.size} Quests Completed • ${state.userProfile.xp} XP",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SaaSOnSuccessContainer,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }

            // Badges Section Header
            item {
                Text(
                    text = "Achievement Badges",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = SaaSTextPrimary
                )
            }

            items(badges) { badge ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(if (badge.isUnlocked) SaaSSuccessContainer else SaaSSurfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (badge.isUnlocked) badge.iconEmoji else "🔒",
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isUrdu) badge.urduTitle else badge.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (badge.isUnlocked) SaaSTextPrimary else SaaSTextSecondary
                            )
                            Text(
                                text = badge.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = SaaSTextSecondary
                            )
                        }

                        if (badge.isUnlocked) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SaaSSuccessContainer
                            ) {
                                Text(
                                    text = "Unlocked",
                                    color = SaaSOnSuccessContainer,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
