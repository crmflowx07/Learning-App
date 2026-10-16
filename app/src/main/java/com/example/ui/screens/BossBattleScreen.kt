package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.data.model.QuizQuestion
import com.example.ui.theme.*

@Composable
fun BossBattleScreen(
    stageId: Int,
    isUrdu: Boolean,
    onBack: () -> Unit,
    onBossDefeated: (xpBonus: Int, coinBonus: Int) -> Unit
) {
    BackHandler { onBack() }

    val assessmentTitle = when (stageId) {
        1 -> if (isUrdu) "پریپ / کے جی جامع جائزہ" else "Prep / KG Milestone Assessment"
        2 -> if (isUrdu) "جماعت ۱-۲ جامع جائزہ" else "Class 1-2 Milestone Assessment"
        5 -> if (isUrdu) "جماعت ۷-۸ پائیتھن جائزہ" else "Class 7-8 Python Logic Assessment"
        8 -> if (isUrdu) "BSCS سمسٹر جامع امتحان" else "BSCS Comprehensive Exam"
        else -> if (isUrdu) "مرحلہ وار تشخیصی جائزہ" else "Stage Milestone Assessment"
    }

    val assessmentQuestions = remember(stageId) {
        listOf(
            QuizQuestion(
                id = "boss_q1",
                question = if (stageId <= 2) "Which number comes directly after 9?" else "Which logic condition is True when either A OR B is true?",
                urduQuestion = if (stageId <= 2) "۹ کے فوراً بعد کون سا عدد آتا ہے؟" else "OR گیٹ کا آؤٹ پٹ کب 1 ہوتا ہے؟",
                options = if (stageId <= 2) listOf("8", "10", "11", "12") else listOf("Only when both are false", "When at least one input is True", "Never True"),
                urduOptions = if (stageId <= 2) listOf("۸", "۱۰", "۱۱", "۱۲") else listOf("جب دونوں غلط ہوں", "جب کم از کم ایک درست ہو", "کبھی نہیں"),
                correctIndex = 1,
                explanation = "Correct answer verified.",
                urduExplanation = "درست جواب تصدیق شدہ!"
            ),
            QuizQuestion(
                id = "boss_q2",
                question = if (stageId <= 2) "What is 5 + 5?" else "In computing, what is the size of 1 Byte in bits?",
                urduQuestion = if (stageId <= 2) "۵ + ۵ کتنے ہوتے ہیں؟" else "۱ بائٹ میں کتنے بٹس ہوتے ہیں؟",
                options = if (stageId <= 2) listOf("8", "9", "10", "12") else listOf("4 bits", "8 bits", "16 bits", "32 bits"),
                urduOptions = if (stageId <= 2) listOf("۸", "۹", "۱۰", "۱۲") else listOf("۴ بٹس", "۸ بٹس", "۱۶ بٹس", "۳۲ بٹس"),
                correctIndex = if (stageId <= 2) 2 else 1,
                explanation = "Correct answer verified.",
                urduExplanation = "درست جواب تصدیق شدہ!"
            ),
            QuizQuestion(
                id = "boss_q3",
                question = if (stageId <= 2) "Which shape is round like a ball?" else "What is the result of 15 // 4 (integer division) in Python?",
                urduQuestion = if (stageId <= 2) "گیند کی طرح گول شکل کون سی ہوتی ہے؟" else "پائیتھن میں 15 // 4 کا نتیجہ کیا ہوگا؟",
                options = if (stageId <= 2) listOf("Square (مربع)", "Circle (دائرہ)", "Triangle (مثلث)") else listOf("3", "3.75", "4", "0"),
                urduOptions = if (stageId <= 2) listOf("مربع", "دائرہ", "مثلث") else listOf("۳", "۳.۷۵", "۴", "۰"),
                correctIndex = if (stageId <= 2) 1 else 0,
                explanation = "Assessment verified.",
                urduExplanation = "جائزہ مکمل ہوا!"
            )
        )
    }

    var qIndex by remember { mutableIntStateOf(0) }
    var scoreCount by remember { mutableIntStateOf(0) }
    var isVictory by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
    ) {
        // SaaS Assessment Header
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SaaSTextPrimary)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = assessmentTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Text(
                            text = "Stage $stageId Diagnostic Evaluation",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = SaaSPrimaryContainer
                ) {
                    Text(
                        text = "+150 XP Bonus",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SaaSPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Assessment Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (isVictory) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(SaaSSuccessContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SaaSSuccess, modifier = Modifier.size(36.dp))
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = if (isUrdu) "مرحلہ وار جائزہ پاس ہو گیا!" else "Milestone Assessment Passed!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Score: ${scoreCount} / ${assessmentQuestions.size} Questions Answered Correctly (100% Mastery)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SaaSTextSecondary
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = SaaSPrimaryContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "Rewards Granted:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = SaaSPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "• +150 Academic XP  • +50 Currency Stars  • Stage Champion Badge",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SaaSTextPrimary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { onBossDefeated(150, 50) },
                            colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().testTag("boss_claim_btn")
                        ) {
                            Text(if (isUrdu) "انعام وصول کریں اور ڈیش بورڈ پر جائیں" else "Claim Rewards & Return to Dashboard")
                        }
                    }
                }
            } else {
                val currentQ = assessmentQuestions[qIndex]

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question ${qIndex + 1} of ${assessmentQuestions.size}",
                            style = MaterialTheme.typography.labelMedium,
                            color = SaaSPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Passing Benchmark: 70%",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary
                        )
                    }

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
                    options.forEachIndexed { idx, opt ->
                        val isSelected = selectedOption == idx
                        val isCorrect = idx == currentQ.correctIndex

                        val cardBg = when {
                            isSubmitted && isCorrect -> SaaSSuccessContainer
                            isSubmitted && isSelected && !isCorrect -> Color(0xFFFEF2F2)
                            isSelected -> SaaSPrimaryContainer
                            else -> SaaSSurface
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = cardBg,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                when {
                                    isSubmitted && isCorrect -> SaaSSuccess
                                    isSubmitted && isSelected && !isCorrect -> SaaSError
                                    isSelected -> SaaSPrimary
                                    else -> SaaSBorder
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable(enabled = !isSubmitted) { selectedOption = idx }
                                .testTag("boss_opt_$idx")
                        ) {
                            Text(
                                text = opt,
                                color = SaaSTextPrimary,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(14.dp)
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        if (!isSubmitted) {
                            isSubmitted = true
                            if (selectedOption == currentQ.correctIndex) {
                                scoreCount++
                            }
                        } else {
                            if (qIndex + 1 < assessmentQuestions.size) {
                                qIndex++
                                selectedOption = null
                                isSubmitted = false
                            } else {
                                isVictory = true
                            }
                        }
                    },
                    enabled = selectedOption != null,
                    colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().testTag("cast_spell_btn")
                ) {
                    Text(
                        if (!isSubmitted) {
                            if (isUrdu) "جواب جمع کریں" else "Submit Answer"
                        } else {
                            if (qIndex + 1 < assessmentQuestions.size) "Next Question" else "Complete Assessment"
                        }
                    )
                }
            }
        }
    }
}
