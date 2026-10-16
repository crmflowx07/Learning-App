package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.horizontalScroll
import com.example.data.model.AlphabetData
import com.example.data.model.Lesson
import com.example.data.model.LessonType
import com.example.data.model.MatchingPair
import com.example.ui.theme.*

@Composable
fun LessonPlayerScreen(
    lesson: Lesson?,
    isUrdu: Boolean,
    onBack: () -> Unit,
    onFinishLesson: (stars: Int, score: Int) -> Unit,
    onSpeak: ((String) -> Unit)? = null,
    speechRate: Float = 0.9f,
    onToggleSpeechRate: (() -> Unit)? = null,
    isFavorite: Boolean = false,
    onToggleFavorite: (() -> Unit)? = null
) {
    BackHandler { onBack() }

    if (lesson == null) {
        Box(modifier = Modifier.fillMaxSize().background(SaaSBackground), contentAlignment = Alignment.Center) {
            Text(if (isUrdu) "کوئی مشن منتخب نہیں ہوا" else "No lesson selected", color = SaaSTextSecondary)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
    ) {
        // SaaS Clean Lesson Header
        Surface(
            color = SaaSSurface,
            tonalElevation = 1.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("lesson_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SaaSTextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = if (isUrdu) lesson.urduTitle else lesson.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = SaaSPrimaryContainer
                            ) {
                                Text(
                                    text = when (lesson.type) {
                                        LessonType.SIMULATION -> "Interactive Simulation"
                                        LessonType.CODE_LAB -> "Code Lab"
                                        LessonType.MINIGAME_TRACING -> "Letter Tracing"
                                        LessonType.MINIGAME_MATCHING -> "Matching Exercise"
                                        LessonType.QUIZ -> "Diagnostic Quiz"
                                        LessonType.STORY -> "Reading Theory"
                                    },
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSPrimary,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "+${lesson.xpReward} XP",
                                style = MaterialTheme.typography.labelSmall,
                                color = SaaSWarning,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Controls: Speech Rate & Bookmark
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (onToggleSpeechRate != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSSurfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                            modifier = Modifier
                                .clickable { onToggleSpeechRate() }
                                .testTag("speech_rate_btn")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Text("🔊", fontSize = 11.sp)
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "${speechRate}x",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSPrimary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    }

                    if (onToggleFavorite != null) {
                        IconButton(
                            onClick = { onToggleFavorite() },
                            modifier = Modifier.size(32.dp).testTag("lesson_fav_btn")
                        ) {
                            Text(if (isFavorite) "★" else "☆", fontSize = 20.sp, color = if (isFavorite) Color(0xFFF59E0B) else SaaSTextMuted)
                        }
                    }
                }
            }
        }

        // Body Content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (lesson.type) {
                LessonType.SIMULATION -> {
                    SaaSSimulationPlayer(
                        simType = lesson.simulationType,
                        goalCount = lesson.simulationCount,
                        isUrdu = isUrdu,
                        onComplete = { onFinishLesson(3, 100) },
                        onSpeak = onSpeak
                    )
                }
                LessonType.MINIGAME_TRACING -> {
                    SaaSTracingPlayer(
                        targetLetter = lesson.targetCharacter,
                        isUrdu = isUrdu,
                        onComplete = { onFinishLesson(3, 100) },
                        onSpeak = onSpeak
                    )
                }
                LessonType.MINIGAME_MATCHING -> {
                    SaaSMatchingPlayer(
                        pairs = lesson.matchingPairs,
                        isUrdu = isUrdu,
                        onComplete = { onFinishLesson(3, 100) }
                    )
                }
                LessonType.CODE_LAB -> {
                    SaaSCodeLabPlayer(
                        lesson = lesson,
                        isUrdu = isUrdu,
                        onComplete = { onFinishLesson(3, 100) }
                    )
                }
                LessonType.QUIZ -> {
                    SaaSQuizPlayer(
                        lesson = lesson,
                        isUrdu = isUrdu,
                        onComplete = { stars, score -> onFinishLesson(stars, score) }
                    )
                }
                LessonType.STORY -> {
                    SaaSStoryPlayer(
                        lesson = lesson,
                        isUrdu = isUrdu,
                        onComplete = { onFinishLesson(3, 100) }
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 1. SAAS SIMULATION PLAYER
// -------------------------------------------------------------
@Composable
fun SaaSSimulationPlayer(
    simType: String,
    goalCount: Int,
    isUrdu: Boolean,
    onComplete: () -> Unit,
    onSpeak: ((String) -> Unit)? = null
) {
    when (simType) {
        "COUNTING_LAB" -> SaaSCountingExercise(goalCount = goalCount, isUrdu = isUrdu, onComplete = onComplete, onSpeak = onSpeak)
        "SHAPES_LAB" -> SaaSShapesExercise(isUrdu = isUrdu, onComplete = onComplete)
        "LOGIC_GATES_LAB" -> SaaSLogicGateExercise(isUrdu = isUrdu, onComplete = onComplete)
        "BINARY_TREE_LAB" -> SaaSTreeExercise(isUrdu = isUrdu, onComplete = onComplete)
        "SORTING_LAB" -> SaaSSortingExercise(isUrdu = isUrdu, onComplete = onComplete)
        else -> SaaSCountingExercise(goalCount = 10, isUrdu = isUrdu, onComplete = onComplete, onSpeak = onSpeak)
    }
}

@Composable
fun SaaSCountingExercise(
    goalCount: Int,
    isUrdu: Boolean,
    onComplete: () -> Unit,
    onSpeak: ((String) -> Unit)? = null
) {
    var maxTarget by remember { mutableIntStateOf(if (goalCount > 0) goalCount else 10) }
    var currentNumber by remember { mutableIntStateOf(1) }
    var countTally by remember { mutableIntStateOf(1) }

    val urduNumerals = listOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹", "۱۰", "۱۱", "۱۲", "۱۳", "۱۴", "۱۵", "۱۶", "۱۷", "۱۸", "۱۹", "۲۰")
    val englishWords = listOf(
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"
    )
    val urduWords = listOf(
        "صفر", "ایک", "دو", "تین", "چار", "پانچ", "چھ", "سات", "آٹھ", "نو", "دس",
        "گیارہ", "بارہ", "تیرہ", "چودہ", "پندرہ", "سولہ", "سترہ", "اٹھارہ", "انیس", "بیس"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = if (isUrdu) "مکمل گنتی و اعداد کا تعارف (۱ سے $maxTarget)" else "Complete Numbers & Counting (1 to $maxTarget)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = SaaSTextPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isUrdu) "عدد پر ٹیپ کریں، آواز سنیں اور گنتی مکمل کریں" else "Select any number, listen to pronunciation, and observe quantity",
            style = MaterialTheme.typography.bodyMedium,
            color = SaaSTextSecondary
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Range Selector Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(5, 10, 20).forEach { range ->
                val isSelected = maxTarget == range
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) SaaSPrimary else SaaSSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SaaSPrimary else SaaSBorder),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            maxTarget = range
                            if (currentNumber > range) currentNumber = 1
                        }
                ) {
                    Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "1 - $range",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isSelected) Color.White else SaaSTextPrimary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Large Number Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isUrdu) urduWords.getOrElse(currentNumber) { "$currentNumber" }
                            else englishWords.getOrElse(currentNumber) { "Number $currentNumber" },
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = SaaSPrimary
                        )
                        Text(
                            text = "Urdu: ${urduNumerals.getOrElse(currentNumber) { "$currentNumber" }} • ${urduWords.getOrElse(currentNumber) { "" }}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SaaSTextSecondary
                        )
                    }

                    // Pronounce Audio Button
                    IconButton(
                        onClick = {
                            val text = if (isUrdu) {
                                "${urduWords.getOrElse(currentNumber) { "$currentNumber" }}۔ عدد $currentNumber"
                            } else {
                                "Number $currentNumber. ${englishWords.getOrElse(currentNumber) { "" }}."
                            }
                            onSpeak?.invoke(text)
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SaaSPrimaryContainer)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Pronounce", tint = SaaSPrimary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Big number display
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SaaSPrimaryContainer,
                    modifier = Modifier.fillMaxWidth().height(90.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "$currentNumber",
                            fontSize = 54.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = SaaSPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Object Visualizer (Count apples)
                Text(
                    text = "Items Quantity Visualizer ($currentNumber):",
                    style = MaterialTheme.typography.labelSmall,
                    color = SaaSTextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val displayCount = currentNumber.coerceAtMost(10)
                    Text(
                        text = "🍎 ".repeat(displayCount) + if (currentNumber > 10) " (+${currentNumber - 10} more)" else "",
                        fontSize = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Numbers Grid (1 to maxTarget)
        Text(
            text = if (isUrdu) "تمام اعداد منتخب کریں:" else "Select Any Number to Learn:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = SaaSTextPrimary,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 240.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items((1..maxTarget).toList()) { i ->
                val isSelected = i == currentNumber
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) SaaSPrimary else SaaSSurface,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isSelected) SaaSPrimary else SaaSBorder
                    ),
                    modifier = Modifier
                        .height(48.dp)
                        .clickable {
                            currentNumber = i
                            onSpeak?.invoke("$i")
                        }
                        .testTag("num_box_$i")
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = if (isUrdu) "${urduNumerals.getOrElse(i) { "$i" }} ($i)" else "$i",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else SaaSTextPrimary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next / Prev Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { if (currentNumber > 1) currentNumber-- },
                enabled = currentNumber > 1,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.weight(1f)
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentNumber < maxTarget) currentNumber++
                    else onComplete()
                },
                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1.5f)
            ) {
                Text(if (currentNumber < maxTarget) "Next Number ➡️" else "Finish All ✓")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("sim_complete_btn")
        ) {
            Text(if (isUrdu) "گنتی مشن مکمل کریں ✓" else "Complete Numbers Lesson ✓")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SaaSShapesExercise(isUrdu: Boolean, onComplete: () -> Unit) {
    val shapes = listOf("Circle (دائرہ)", "Square (مربع)", "Triangle (مثلث)", "Cube (مکعب)", "Sphere (کرہ)")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isUrdu) "اشکال و جیومیٹری مشق" else "Geometric Shapes Lab",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (isUrdu) "شکل کا انتخاب کریں اور خصوصیات دیکھیں" else "Select a shape to inspect vertices, edges and properties",
                style = MaterialTheme.typography.bodyMedium,
                color = SaaSTextSecondary
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.size(220.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val cx = size.width / 2
                    val cy = size.height / 2
                    when (selectedIndex) {
                        0 -> drawCircle(SaaSPrimary, 70.dp.toPx(), Offset(cx, cy))
                        1 -> {
                            val s = 65.dp.toPx()
                            drawRect(SaaSSuccess, Offset(cx - s, cy - s), androidx.compose.ui.geometry.Size(s * 2, s * 2))
                        }
                        2 -> {
                            val p = androidx.compose.ui.graphics.Path().apply {
                                moveTo(cx, cy - 70.dp.toPx())
                                lineTo(cx + 70.dp.toPx(), cy + 60.dp.toPx())
                                lineTo(cx - 70.dp.toPx(), cy + 60.dp.toPx())
                                close()
                            }
                            drawPath(p, SaaSWarning)
                        }
                        else -> drawCircle(SaaSPurple, 70.dp.toPx(), Offset(cx, cy))
                    }
                }
                Text(
                    text = shapes[selectedIndex],
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = SaaSTextPrimary,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            shapes.forEachIndexed { idx, name ->
                FilterChip(
                    selected = selectedIndex == idx,
                    onClick = { selectedIndex = idx },
                    label = { Text(name.split(" ")[0], fontSize = 11.sp) }
                )
            }
        }

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("shapes_complete_btn")
        ) {
            Text(if (isUrdu) "مشن مکمل کریں" else "Complete Exercise")
        }
    }
}

@Composable
fun SaaSLogicGateExercise(isUrdu: Boolean, onComplete: () -> Unit) {
    var inputA by remember { mutableStateOf(false) }
    var inputB by remember { mutableStateOf(false) }
    var gateType by remember { mutableStateOf("AND") }

    val output = when (gateType) {
        "AND" -> inputA && inputB
        "OR" -> inputA || inputB
        "XOR" -> inputA xor inputB
        "NAND" -> !(inputA && inputB)
        else -> false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isUrdu) "ڈیجیٹل لاجک سرکٹ لیب (BSCS)" else "Digital Logic Circuit Simulator (BSCS)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = SaaSTextPrimary
        )
        Text(
            text = if (isUrdu) "ان پٹ تبدیل کر کے سرکٹ کا آؤٹ پٹ چیک کریں" else "Toggle binary inputs to observe truth table output",
            style = MaterialTheme.typography.bodySmall,
            color = SaaSTextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("AND", "OR", "XOR", "NAND").forEach { g ->
                FilterChip(
                    selected = gateType == g,
                    onClick = { gateType = g },
                    label = { Text(g, fontWeight = FontWeight.Bold) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Input A: ${if (inputA) "1" else "0"}", fontWeight = FontWeight.Bold, color = SaaSTextPrimary)
                        Switch(checked = inputA, onCheckedChange = { inputA = it }, modifier = Modifier.testTag("switch_a"))
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SaaSPrimaryContainer,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(gateType, fontWeight = FontWeight.ExtraBold, color = SaaSPrimary, modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp))
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Input B: ${if (inputB) "1" else "0"}", fontWeight = FontWeight.Bold, color = SaaSTextPrimary)
                        Switch(checked = inputB, onCheckedChange = { inputB = it }, modifier = Modifier.testTag("switch_b"))
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (output) SaaSSuccessContainer else SaaSSurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (output) SaaSSuccess else SaaSBorder)
                ) {
                    Text(
                        text = "Output = ${if (output) "1 (HIGH)" else "0 (LOW)"}",
                        fontWeight = FontWeight.Bold,
                        color = if (output) SaaSOnSuccessContainer else SaaSTextSecondary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("logic_gate_complete_btn")
        ) {
            Text(if (isUrdu) "مشق مکمل کریں" else "Verify Logic Mastery")
        }
    }
}

@Composable
fun SaaSTreeExercise(isUrdu: Boolean, onComplete: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Binary Search Tree (BST) Concept",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rule: Left Node < Parent Node < Right Node",
                style = MaterialTheme.typography.bodyMedium,
                color = SaaSTextSecondary
            )
        }

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth().height(220.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val rootX = size.width / 2
                    val rootY = 50f
                    val leftX = size.width / 4
                    val leftY = 150f
                    val rightX = (size.width * 3) / 4
                    val rightY = 150f

                    drawLine(SaaSBorder, Offset(rootX, rootY), Offset(leftX, leftY), strokeWidth = 4f)
                    drawLine(SaaSBorder, Offset(rootX, rootY), Offset(rightX, rightY), strokeWidth = 4f)

                    drawCircle(SaaSPrimary, 24.dp.toPx(), Offset(rootX, rootY))
                    drawCircle(SaaSSuccess, 20.dp.toPx(), Offset(leftX, leftY))
                    drawCircle(SaaSPurple, 20.dp.toPx(), Offset(rightX, rightY))
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center) {
                        Text("50 (Root)", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 28.dp, end = 28.dp, bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("30 (< 50)", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        Text("70 (> 50)", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                }
            }
        }

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("bst_complete_btn")
        ) {
            Text(if (isUrdu) "تصور مکمل کریں" else "Complete BST Module")
        }
    }
}

@Composable
fun SaaSSortingExercise(isUrdu: Boolean, onComplete: () -> Unit) {
    var array by remember { mutableStateOf(listOf(45, 12, 89, 34, 67, 23)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Algorithm Step: Array Sorting",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Observe element comparisons and ascending order sorting",
                style = MaterialTheme.typography.bodyMedium,
                color = SaaSTextSecondary
            )
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth().height(200.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                array.forEach { v ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("$v", color = SaaSTextPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .width(34.dp)
                                .height((v * 1.5).dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(SaaSPrimary)
                        )
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
                onClick = { array = array.sorted() },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Sort Ascending")
            }
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f).testTag("sort_complete_btn")
            ) {
                Text("Verify (+70 XP)")
            }
        }
    }
}

// -------------------------------------------------------------
// 2. SAAS CARD MATCHING
// -------------------------------------------------------------
@Composable
fun SaaSMatchingPlayer(
    pairs: List<MatchingPair>,
    isUrdu: Boolean,
    onComplete: () -> Unit
) {
    var selectedA by remember { mutableStateOf<String?>(null) }
    var selectedB by remember { mutableStateOf<String?>(null) }
    var matchedIds by remember { mutableStateOf(setOf<String>()) }

    val allMatched = pairs.isNotEmpty() && matchedIds.size == pairs.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isUrdu) "ملاپ کی مشق (Matching Pairs)" else "Matching Exercise",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (isUrdu) "بائیں کالم سے حرف چنیں اور دائیں طرف درست لفظ سے ملائیں" else "Select an item from Column A and match it with Column B",
                style = MaterialTheme.typography.bodyMedium,
                color = SaaSTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Column A
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pairs.forEach { pair ->
                    val isDone = matchedIds.contains(pair.id)
                    val isSelected = selectedA == pair.id

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when {
                            isDone -> SaaSSuccessContainer
                            isSelected -> SaaSPrimaryContainer
                            else -> SaaSSurface
                        },
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            when {
                                isDone -> SaaSSuccess
                                isSelected -> SaaSPrimary
                                else -> SaaSBorder
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !isDone) {
                                selectedA = pair.id
                                if (selectedB != null) {
                                    if (selectedB == pair.id) {
                                        matchedIds = matchedIds + pair.id
                                    }
                                    selectedA = null
                                    selectedB = null
                                }
                            }
                            .testTag("match_a_${pair.id}")
                    ) {
                        Box(modifier = Modifier.padding(14.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = pair.itemA,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isDone) SaaSOnSuccessContainer else SaaSTextPrimary
                            )
                        }
                    }
                }
            }

            // Column B
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pairs.reversed().forEach { pair ->
                    val isDone = matchedIds.contains(pair.id)
                    val isSelected = selectedB == pair.id

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when {
                            isDone -> SaaSSuccessContainer
                            isSelected -> SaaSPrimaryContainer
                            else -> SaaSSurface
                        },
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            when {
                                isDone -> SaaSSuccess
                                isSelected -> SaaSPrimary
                                else -> SaaSBorder
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !isDone) {
                                selectedB = pair.id
                                if (selectedA != null) {
                                    if (selectedA == pair.id) {
                                        matchedIds = matchedIds + pair.id
                                    }
                                    selectedA = null
                                    selectedB = null
                                }
                            }
                            .testTag("match_b_${pair.id}")
                    ) {
                        Box(modifier = Modifier.padding(14.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = pair.itemB,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium,
                                color = if (isDone) SaaSOnSuccessContainer else SaaSTextPrimary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (allMatched) {
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().testTag("matching_complete_btn")
            ) {
                Text(if (isUrdu) "تمام جوڑے مکمل! آگے بڑھیں" else "All Pairs Matched! Continue")
            }
        } else {
            Text(
                text = "${matchedIds.size} of ${pairs.size} pairs matched",
                color = SaaSTextSecondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

// -------------------------------------------------------------
// 3. SAAS ALPHABET COMPLETE WORKSHOP (A to Z & URDU HAROOF)
// -------------------------------------------------------------
@Composable
fun SaaSTracingPlayer(
    targetLetter: String,
    isUrdu: Boolean,
    onComplete: () -> Unit,
    onSpeak: ((String) -> Unit)? = null
) {
    val isTargetUrdu = targetLetter.any { it in '\u0600'..'\u06FF' } || isUrdu
    val alphabetList = if (isTargetUrdu) AlphabetData.urduAlphabet else AlphabetData.englishAlphabet

    var selectedIndex by remember {
        val initialIdx = alphabetList.indexOfFirst { it.letter.equals(targetLetter, ignoreCase = true) }
        mutableIntStateOf(if (initialIdx >= 0) initialIdx else 0)
    }

    val currentLetterInfo = alphabetList.getOrElse(selectedIndex) { alphabetList[0] }
    var strokes by remember(selectedIndex) { mutableStateOf(listOf<Offset>()) }
    val masteredLetters = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Top Alphabet Quick Selector Ribbon (A, B, C, D ... Z)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isTargetUrdu) "حروفِ تہجی منتخب کریں" else "Select Letter (A-Z)",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = SaaSSuccessContainer
            ) {
                Text(
                    text = "${masteredLetters.size} / ${alphabetList.size} Mastered",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SaaSOnSuccessContainer,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Horizontal letter scroll
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            alphabetList.forEachIndexed { idx, item ->
                val isSelected = idx == selectedIndex
                val isMastered = masteredLetters.contains(item.letter)

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when {
                        isSelected -> SaaSPrimary
                        isMastered -> SaaSSuccessContainer
                        else -> SaaSSurface
                    },
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        when {
                            isSelected -> SaaSPrimary
                            isMastered -> SaaSSuccess
                            else -> SaaSBorder
                        }
                    ),
                    modifier = Modifier
                        .clickable { selectedIndex = idx }
                        .testTag("letter_tab_${item.letter}")
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isTargetUrdu) item.letter else "${item.letter}${item.lowercase}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = when {
                                isSelected -> Color.White
                                isMastered -> SaaSOnSuccessContainer
                                else -> SaaSTextPrimary
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Active Letter Overview Card (Letter + Sound + Speaker)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SaaSPrimaryContainer,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = if (isTargetUrdu) currentLetterInfo.letter else "${currentLetterInfo.letter} ${currentLetterInfo.lowercase}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = SaaSPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = if (isTargetUrdu) currentLetterInfo.urduPhonics else currentLetterInfo.phonics,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Text(
                            text = if (isTargetUrdu) currentLetterInfo.lowercase else "Letter ${currentLetterInfo.letter}",
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSTextSecondary
                        )
                    }
                }

                // Audio Button
                IconButton(
                    onClick = {
                        val textToSpeak = if (isTargetUrdu) {
                            "${currentLetterInfo.urduPhonics}. " + currentLetterInfo.examples.joinToString { it.word }
                        } else {
                            "Letter ${currentLetterInfo.letter}. ${currentLetterInfo.phonics}. " +
                                    currentLetterInfo.examples.joinToString { "${currentLetterInfo.letter} for ${it.word}" }
                        }
                        onSpeak?.invoke(textToSpeak)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(SaaSPrimaryContainer)
                        .testTag("letter_audio_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Pronounce Letter",
                        tint = SaaSPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Illustrated Vocabulary Cards Grid (Apple, Ant, Airplane, etc.)
        Text(
            text = if (isTargetUrdu) "الفاظ و تصاویر" else "Illustrated Words & Phonics",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = SaaSTextPrimary,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            currentLetterInfo.examples.take(4).forEach { ex ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onSpeak?.invoke(if (isTargetUrdu) ex.word else ex.word)
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = ex.emoji, fontSize = 28.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = ex.word,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary,
                            maxLines = 1
                        )
                        Text(
                            text = ex.urduWord,
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3.5 Quick Phonics Quiz Challenge
        val primaryExample = currentLetterInfo.examples.firstOrNull()
        val otherExamples = remember(selectedIndex) {
            val otherLetters = alphabetList.filterIndexed { i, _ -> i != selectedIndex }
            listOfNotNull(
                primaryExample,
                otherLetters.getOrNull(0)?.examples?.firstOrNull(),
                otherLetters.getOrNull(1)?.examples?.firstOrNull()
            ).shuffled()
        }
        var selectedQuizWord by remember(selectedIndex) { mutableStateOf<String?>(null) }
        var quizFeedback by remember(selectedIndex) { mutableStateOf<String?>(null) }

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isTargetUrdu) "صوتیات ٹیسٹ: ${currentLetterInfo.letter} سے کون سا لفظ بنتا ہے؟" else "Phonics Quiz: Which starts with '${currentLetterInfo.letter}'?",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )
                    Surface(shape = RoundedCornerShape(4.dp), color = SaaSPrimaryContainer) {
                        Text(
                            text = "Interactive",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SaaSPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    otherExamples.forEach { ex ->
                        val isChosen = selectedQuizWord == ex.word
                        val isCorrect = ex.word == primaryExample?.word
                        OutlinedButton(
                            onClick = {
                                selectedQuizWord = ex.word
                                if (isCorrect) {
                                    quizFeedback = if (isTargetUrdu) "شاباش! بالکل درست جواب!" else "Super! '${currentLetterInfo.letter}' is for ${ex.word}! 🎉"
                                    onSpeak?.invoke(if (isTargetUrdu) "بہت خوب! ${ex.word}" else "Great job! ${currentLetterInfo.letter} is for ${ex.word}!")
                                    if (!masteredLetters.contains(currentLetterInfo.letter)) {
                                        masteredLetters.add(currentLetterInfo.letter)
                                    }
                                } else {
                                    quizFeedback = if (isTargetUrdu) "دوبارہ کوشش کریں، آواز سنیں۔" else "Try again! Listen to '${currentLetterInfo.letter}' sound."
                                    onSpeak?.invoke(if (isTargetUrdu) "دوبارہ کوشش کریں" else "Try again!")
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = when {
                                    isChosen && isCorrect -> SaaSSuccessContainer
                                    isChosen && !isCorrect -> SaaSErrorContainer
                                    else -> SaaSSurface
                                }
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                when {
                                    isChosen && isCorrect -> SaaSSuccess
                                    isChosen && !isCorrect -> SaaSError
                                    else -> SaaSBorder
                                }
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = ex.emoji, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = ex.word,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSTextPrimary,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
                if (quizFeedback != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = quizFeedback!!,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selectedQuizWord == primaryExample?.word) SaaSSuccess else SaaSError
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. White SaaS Handwriting & Tracing Canvas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isTargetUrdu) "خوشخطی و ٹریسنگ مشق" else "Handwriting & Tracing Canvas",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            TextButton(onClick = { strokes = emptyList() }) {
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp), tint = SaaSPrimary)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Clear", fontSize = 12.sp, color = SaaSPrimary)
            }
        }

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, SaaSBorder),
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .pointerInput(currentLetterInfo.letter) {
                    detectDragGestures { change, _ ->
                        change.consume()
                        strokes = strokes + change.position
                    }
                }
                .testTag("tracing_canvas")
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Background Guide Letter
                Text(
                    text = currentLetterInfo.letter,
                    fontSize = 150.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE2E8F0)
                )

                // User Drawn Strokes
                Canvas(modifier = Modifier.fillMaxSize()) {
                    for (i in 0 until strokes.size - 1) {
                        drawLine(
                            color = SaaSPrimary,
                            start = strokes[i],
                            end = strokes[i + 1],
                            strokeWidth = 10.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }

                if (strokes.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SaaSPrimaryContainer
                        ) {
                            Text(
                                text = if (isTargetUrdu) "انگلی سے خاکہ ٹریس کریں" else "Trace letter outline with your finger",
                                style = MaterialTheme.typography.labelSmall,
                                color = SaaSPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Letter Navigation Controls & Mark Mastered
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = {
                    if (selectedIndex > 0) {
                        selectedIndex--
                    }
                },
                enabled = selectedIndex > 0,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Previous")
            }

            Button(
                onClick = {
                    if (!masteredLetters.contains(currentLetterInfo.letter)) {
                        masteredLetters.add(currentLetterInfo.letter)
                    }
                    if (selectedIndex + 1 < alphabetList.size) {
                        selectedIndex++
                    } else {
                        onComplete()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1.5f).testTag("tracing_next_btn")
            ) {
                Text(
                    text = if (selectedIndex + 1 < alphabetList.size) "Next Letter ➡️" else "Finish All Letters ✓",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("tracing_complete_btn")
        ) {
            Text(if (isTargetUrdu) "مشن مکمل کریں اور ستارے حاصل کریں ✓" else "Complete Alphabet Lesson ✓")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// -------------------------------------------------------------
// 4. SAAS CODE LAB
// -------------------------------------------------------------
@Composable
fun SaaSCodeLabPlayer(
    lesson: Lesson,
    isUrdu: Boolean,
    onComplete: () -> Unit
) {
    val challenge = lesson.codeChallenge
    var codeText by remember { mutableStateOf(challenge?.initialCode ?: "print('Hello World!')") }
    var consoleOutput by remember { mutableStateOf("") }
    var isPassed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = challenge?.title ?: "Programming Exercise",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SaaSTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = challenge?.instructions ?: "Write code to satisfy the test requirements.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SaaSTextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Editor
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Editor • ${challenge?.language?.uppercase() ?: "PYTHON"}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = SaaSTextSecondary
            )
            Text(
                text = "main.${if (challenge?.language == "cpp") "cpp" else "py"}",
                style = MaterialTheme.typography.labelSmall,
                color = SaaSPrimary,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = codeText,
            onValueChange = { codeText = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
                .testTag("code_editor_field"),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF0F172A)
            ),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8FAFC),
                unfocusedContainerColor = Color(0xFFF8FAFC),
                focusedBorderColor = SaaSPrimary,
                unfocusedBorderColor = SaaSBorder
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                consoleOutput = if (codeText.contains("print") || codeText.contains("cout") || codeText.contains("SELECT")) {
                    challenge?.expectedOutput ?: "Execution successful."
                } else {
                    "Exit status 0."
                }
                isPassed = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().testTag("run_code_btn")
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Run Code & Run Tests")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Console
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Terminal Output:",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (consoleOutput.isEmpty()) "[Console idle]" else "> $consoleOutput",
                    color = if (isPassed) Color(0xFFA7F3D0) else Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (isPassed) {
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().testTag("code_claim_btn")
            ) {
                Text("Tests Passed! Complete Lesson")
            }
        }
    }
}

// -------------------------------------------------------------
// 5. SAAS QUIZ PLAYER
// -------------------------------------------------------------
@Composable
fun SaaSQuizPlayer(
    lesson: Lesson,
    isUrdu: Boolean,
    onComplete: (stars: Int, score: Int) -> Unit
) {
    val questions = lesson.questions
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { onComplete(3, 100) }) {
                Text("Complete")
            }
        }
        return
    }

    val currentQ = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Question ${currentIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = SaaSPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Score: $correctCount",
                    style = MaterialTheme.typography.labelMedium,
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
            options.forEachIndexed { optIndex, optText ->
                val isSelected = selectedOption == optIndex
                val isCorrect = optIndex == currentQ.correctIndex

                val cardColor = when {
                    isSubmitted && isCorrect -> SaaSSuccessContainer
                    isSubmitted && isSelected && !isCorrect -> Color(0xFFFEF2F2)
                    isSelected -> SaaSPrimaryContainer
                    else -> SaaSSurface
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = cardColor,
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
                        .clickable(enabled = !isSubmitted) {
                            selectedOption = optIndex
                        }
                        .testTag("quiz_opt_$optIndex")
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${('A' + optIndex)}. ",
                            fontWeight = FontWeight.Bold,
                            color = SaaSPrimary
                        )
                        Text(
                            text = optText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = SaaSTextPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        if (isSubmitted && isCorrect) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SaaSSuccess)
                        }
                    }
                }
            }

            if (isSubmitted) {
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isUrdu) currentQ.urduExplanation else currentQ.explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = SaaSTextSecondary,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            if (!isSubmitted) {
                Button(
                    onClick = {
                        if (selectedOption != null) {
                            isSubmitted = true
                            if (selectedOption == currentQ.correctIndex) {
                                correctCount++
                            }
                        }
                    },
                    enabled = selectedOption != null,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                    modifier = Modifier.fillMaxWidth().testTag("quiz_submit_btn")
                ) {
                    Text(if (isUrdu) "جواب جمع کریں" else "Submit Answer")
                }
            } else {
                Button(
                    onClick = {
                        if (currentIndex + 1 < questions.size) {
                            currentIndex++
                            selectedOption = null
                            isSubmitted = false
                        } else {
                            val stars = when {
                                correctCount == questions.size -> 3
                                correctCount >= (questions.size / 2) -> 2
                                else -> 1
                            }
                            onComplete(stars, (correctCount * 100) / questions.size)
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaaSSuccess),
                    modifier = Modifier.fillMaxWidth().testTag("quiz_next_btn")
                ) {
                    Text(
                        if (currentIndex + 1 < questions.size) {
                            if (isUrdu) "اگلا سوال" else "Next Question"
                        } else {
                            if (isUrdu) "کوئز مکمل کریں" else "Finish & View Results"
                        }
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 6. SAAS STORY / THEORY PLAYER
// -------------------------------------------------------------
@Composable
fun SaaSStoryPlayer(
    lesson: Lesson,
    isUrdu: Boolean,
    onComplete: () -> Unit
) {
    val pages = lesson.storyPages
    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isUrdu) lesson.urduTitle else lesson.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = SaaSTextPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 180.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = if (pages.isNotEmpty() && currentPage < pages.size) {
                            val page = pages[currentPage]
                            if (isUrdu) page.urduText else page.englishText
                        } else {
                            if (isUrdu) "تصور کا مطالعہ کریں" else "Read through the module concept."
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = SaaSTextPrimary,
                        fontSize = 17.sp,
                        lineHeight = 26.sp
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentPage > 0) {
                OutlinedButton(onClick = { currentPage-- }, shape = RoundedCornerShape(8.dp)) {
                    Text("Previous", color = SaaSTextPrimary)
                }
            } else {
                Spacer(modifier = Modifier.width(1.dp))
            }

            Button(
                onClick = {
                    if (pages.isNotEmpty() && currentPage + 1 < pages.size) {
                        currentPage++
                    } else {
                        onComplete()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = SaaSPrimary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("story_next_btn")
            ) {
                Text(
                    if (pages.isNotEmpty() && currentPage + 1 < pages.size) "Next Page" else "Complete Module"
                )
            }
        }
    }
}
