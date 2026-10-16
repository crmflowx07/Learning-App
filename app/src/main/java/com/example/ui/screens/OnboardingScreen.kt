package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
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
fun OnboardingScreen(
    state: UiState,
    onComplete: (name: String, age: Int, takePlacement: Boolean) -> Unit
) {
    val isUrdu = state.userProfile.languageCode == "ur"
    var name by remember { mutableStateOf(state.userProfile.name) }
    var age by remember { mutableIntStateOf(state.userProfile.age) }

    val currentCalculatedStage = remember(age) { StageEngine.getStageForAge(age) }
    val stageInfo = remember(currentCalculatedStage) {
        CurriculumCatalog.stages.find { it.stageId == currentCalculatedStage }
            ?: CurriculumCatalog.stages[0]
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // App SaaS Crest
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SaaSPrimaryContainer)
                    .border(1.dp, SaaSBorder, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = SaaSPrimary,
                    modifier = Modifier.size(38.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "QuestLearn Academy",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = SaaSTextPrimary
            )

            Text(
                text = if (isUrdu) "حروف سے لے کر الگورتھم تک: مکمل تعلیمی نصاب" else "Complete Curriculum from ABCs to Algorithms & BSCS",
                style = MaterialTheme.typography.bodyMedium,
                color = SaaSTextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Learner Name Field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(if (isUrdu) "طالب علم کا نام" else "Student / Learner Name") },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("onboarding_name_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = SaaSTextPrimary,
                    unfocusedTextColor = SaaSTextPrimary,
                    focusedBorderColor = SaaSPrimary,
                    unfocusedBorderColor = SaaSBorder,
                    focusedContainerColor = SaaSSurface,
                    unfocusedContainerColor = SaaSSurface
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Age Selector Slider Card
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
                            text = if (isUrdu) "طالب علم کی عمر" else "Select Student Age",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSPrimaryContainer
                        ) {
                            Text(
                                text = "$age Years Old",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SaaSPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Slider(
                        value = age.toFloat(),
                        onValueChange = { age = it.toInt() },
                        valueRange = 4f..22f,
                        steps = 17,
                        colors = SliderDefaults.colors(
                            thumbColor = SaaSPrimary,
                            activeTrackColor = SaaSPrimary,
                            inactiveTrackColor = SaaSBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("onboarding_age_slider")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Age 4 (Prep)", color = SaaSTextMuted, fontSize = 11.sp)
                        Text("Age 16 (Inter)", color = SaaSTextMuted, fontSize = 11.sp)
                        Text("Age 22 (BSCS)", color = SaaSTextMuted, fontSize = 11.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dynamic Stage Assignment Preview Card
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
                            text = if (isUrdu) "تجویز کردہ تعلیمی درجہ" else "Recommended Academic Track",
                            style = MaterialTheme.typography.labelMedium,
                            color = SaaSTextSecondary
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSSuccessContainer
                        ) {
                            Text(
                                text = "Stage $currentCalculatedStage",
                                color = SaaSOnSuccessContainer,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (isUrdu) stageInfo.urduName else stageInfo.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )

                    Text(
                        text = "Level: ${stageInfo.levelLabel}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SaaSPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = stageInfo.worldDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = SaaSTextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SaaSWarning,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Learning Assistant: ${stageInfo.companionName}",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { onComplete(name, age, true) },
                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("onboarding_take_placement_btn")
            ) {
                Icon(Icons.Default.Psychology, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isUrdu) "تعینِ سطح کا تشخیصی ٹیسٹ لیں (تجویز کردہ)" else "Take Placement Test (Recommended)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { onComplete(name, age, false) },
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("onboarding_enter_direct_btn")
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp), tint = SaaSPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isUrdu) "براہِ راست تعلیمی پورٹل میں داخل ہوں" else "Enter Learning Portal Directly",
                    color = SaaSTextPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}
