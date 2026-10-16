package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.PlacementDiagnostic
import com.example.data.repository.PlacementTestRepository
import com.example.domain.engine.StageEngine
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun PlacementTestScreen(
    state: UiState,
    onBack: () -> Unit,
    onSubmitDiagnostic: (correctCount: Int, total: Int) -> Unit,
    onEnterStage: () -> Unit
) {
    BackHandler { onBack() }
    val isUrdu = state.userProfile.languageCode == "ur"
    val age = state.userProfile.age
    val questions = remember(age) { PlacementTestRepository.getPlacementQuestionsForAge(age) }

    var currentIdx by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var correctCount by remember { mutableIntStateOf(0) }
    var testFinished by remember { mutableStateOf(false) }

    val diagResult = state.placementResult

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
                IconButton(onClick = onBack, modifier = Modifier.testTag("placement_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = SaaSTextPrimary
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = if (isUrdu) "تشخیصی تعینِ سطح ٹیسٹ" else "Adaptive Placement Diagnostic",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )
                    Text(
                        text = "Calibrated assessment for learner age $age",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaaSTextSecondary
                    )
                }
            }
        }

        if (diagResult != null || testFinished) {
            // Diagnostic Summary
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(SaaSPrimaryContainer, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Psychology,
                            contentDescription = null,
                            tint = SaaSPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = if (isUrdu) "تعینِ سطح کا نتیجہ" else "Diagnostic Recommendation",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    val recommendedStage = diagResult?.recommendedStage ?: state.userProfile.assignedStage
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SaaSPrimaryContainer
                    ) {
                        Text(
                            text = StageEngine.getStageBadge(recommendedStage, isUrdu),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSPrimary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (isUrdu) diagResult?.feedbackUrdu ?: "مرحلہ طے ہو گیا!" else diagResult?.feedbackEnglish ?: "Curriculum calibrated successfully.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = SaaSTextPrimary,
                                lineHeight = 22.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            diagResult?.subjectBreakdown?.forEach { (subj, score) ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(subj, color = SaaSTextSecondary, style = MaterialTheme.typography.bodyMedium)
                                    Text("$score%", color = SaaSPrimary, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onEnterStage,
                    colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().testTag("enter_world_btn")
                ) {
                    Text(
                        text = if (isUrdu) "ڈیش بورڈ پر جائیں" else "Enter Recommended Curriculum",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            // Question in progress
            val currentQ = questions[currentIdx]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Question ${currentIdx + 1} of ${questions.size}",
                        color = SaaSPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (isUrdu) currentQ.urduQuestion else currentQ.question,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    val options = if (isUrdu) currentQ.urduOptions else currentQ.options
                    options.forEachIndexed { optIndex, optText ->
                        val isSelected = selectedOption == optIndex
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) SaaSPrimaryContainer else SaaSSurface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) SaaSPrimary else SaaSBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { selectedOption = optIndex }
                                .testTag("placement_opt_$optIndex")
                        ) {
                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = SaaSTextPrimary,
                                modifier = Modifier.padding(14.dp)
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        if (selectedOption == currentQ.correctIndex) {
                            correctCount++
                        }
                        if (currentIdx + 1 < questions.size) {
                            currentIdx++
                            selectedOption = null
                        } else {
                            testFinished = true
                            onSubmitDiagnostic(correctCount, questions.size)
                        }
                    },
                    enabled = selectedOption != null,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                    modifier = Modifier.fillMaxWidth().testTag("placement_next_btn")
                ) {
                    Text(if (currentIdx + 1 < questions.size) "Next Question" else "Compute Recommendation")
                }
            }
        }
    }
}
