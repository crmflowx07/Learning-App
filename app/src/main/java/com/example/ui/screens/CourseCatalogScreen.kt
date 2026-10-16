package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
import com.example.data.model.Course
import com.example.data.model.Lesson
import com.example.data.model.LessonType
import com.example.data.repository.CurriculumCatalog
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun CourseCatalogScreen(
    state: UiState,
    onBack: () -> Unit,
    onSelectLesson: (Lesson) -> Unit,
    onLaunchBossBattle: () -> Unit = {},
    onToggleFavorite: (String) -> Unit = {}
) {
    BackHandler { onBack() }
    val isUrdu = state.userProfile.languageCode == "ur"
    val stageCourses = CurriculumCatalog.allCourses.filter { it.stageId == state.activeStage.stageId }

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val filterOptions = remember(stageCourses) {
        listOf("All", "⭐ Bookmarks", "✓ Completed") + stageCourses.map { it.subject }.distinct()
    }

    // All lessons in current stage
    val allLessons = remember(stageCourses) {
        stageCourses.flatMap { c -> c.units.flatMap { u -> u.lessons } }
    }

    // Filtered courses / lessons
    val matchingLessons = remember(searchQuery, selectedFilter, state.completedLessonIds, state.favoriteLessonIds, stageCourses) {
        if (searchQuery.isNotBlank()) {
            val q = searchQuery.trim().lowercase()
            allLessons.filter { l ->
                l.title.lowercase().contains(q) ||
                l.urduTitle.lowercase().contains(q) ||
                l.targetCharacter.lowercase().contains(q) ||
                l.simulationType.lowercase().contains(q)
            }
        } else {
            emptyList()
        }
    }

    val filteredCourses = remember(selectedFilter, stageCourses, state.completedLessonIds, state.favoriteLessonIds) {
        when (selectedFilter) {
            "All" -> stageCourses
            "⭐ Bookmarks" -> {
                stageCourses.mapNotNull { course ->
                    val filteredUnits = course.units.mapNotNull { unit ->
                        val favLessons = unit.lessons.filter { state.favoriteLessonIds.contains(it.id) }
                        if (favLessons.isNotEmpty()) unit.copy(lessons = favLessons) else null
                    }
                    if (filteredUnits.isNotEmpty()) course.copy(units = filteredUnits) else null
                }
            }
            "✓ Completed" -> {
                stageCourses.mapNotNull { course ->
                    val filteredUnits = course.units.mapNotNull { unit ->
                        val compLessons = unit.lessons.filter { state.completedLessonIds.contains(it.id) }
                        if (compLessons.isNotEmpty()) unit.copy(lessons = compLessons) else null
                    }
                    if (filteredUnits.isNotEmpty()) course.copy(units = filteredUnits) else null
                }
            }
            else -> stageCourses.filter { it.subject == selectedFilter }
        }
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
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("catalog_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SaaSTextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isUrdu) "نصاب و اسباق - مرحلہ ${state.activeStage.stageId}" else "Curriculum Modules • Stage ${state.activeStage.stageId}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Text(
                            text = "${state.activeStage.name} • ${state.activeStage.levelLabel}",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary
                        )
                    }

                    // Total lessons badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SaaSPrimaryContainer,
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC7D2FE))
                    ) {
                        Text(
                            text = "${state.completedLessonIds.size}/${allLessons.size} Done",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SaaSPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            if (isUrdu) "سبق، حرف یا ٹاپک تلاش کریں..." else "Search letters, topics, algorithms...",
                            fontSize = 13.sp,
                            color = SaaSTextMuted
                        )
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = SaaSTextSecondary, modifier = Modifier.size(18.dp))
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear", tint = SaaSTextSecondary, modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SaaSPrimary,
                        unfocusedBorderColor = SaaSBorder,
                        focusedContainerColor = SaaSSurface,
                        unfocusedContainerColor = SaaSSurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("catalog_search_bar")
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subject / Category Filter Chips
                val scrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    filterOptions.forEach { opt ->
                        val isSelected = selectedFilter == opt
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) SaaSPrimary else SaaSSurfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SaaSPrimary else SaaSBorder),
                            modifier = Modifier
                                .clickable { selectedFilter = opt }
                                .testTag("filter_chip_$opt")
                        ) {
                            Text(
                                text = opt,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else SaaSTextSecondary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // List of courses & units
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (searchQuery.isNotBlank()) {
                item {
                    Text(
                        text = if (isUrdu) "تلاش کے نتائج (${matchingLessons.size})" else "Search Results (${matchingLessons.size})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaaSTextPrimary
                    )
                }

                if (matchingLessons.isEmpty()) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("🔍", fontSize = 32.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (isUrdu) "کوئی مماثل سبق نہیں ملا" else "No matching lessons found for \"$searchQuery\"",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSTextPrimary
                                )
                            }
                        }
                    }
                } else {
                    items(matchingLessons) { lesson ->
                        val isCompleted = state.completedLessonIds.contains(lesson.id)
                        val isFav = state.favoriteLessonIds.contains(lesson.id)
                        val stars = state.starsMap[lesson.id] ?: 0

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isCompleted) SaaSSuccessContainer else SaaSSurface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isCompleted) Color(0xFFA7F3D0) else SaaSBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectLesson(lesson) }
                                .testTag("search_lesson_${lesson.id}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(if (isCompleted) SaaSSuccess else SaaSPrimaryContainer),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = if (isCompleted) "✓" else when (lesson.type) {
                                                LessonType.SIMULATION -> "🔬"
                                                LessonType.CODE_LAB -> "💻"
                                                LessonType.MINIGAME_TRACING -> "✏️"
                                                LessonType.MINIGAME_MATCHING -> "🧩"
                                                LessonType.QUIZ -> "❓"
                                                LessonType.STORY -> "📖"
                                            },
                                            fontSize = 14.sp,
                                            color = if (isCompleted) Color.White else SaaSPrimary
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = if (isUrdu) lesson.urduTitle else lesson.title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = SaaSTextPrimary
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = "+${lesson.xpReward} XP",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = SaaSPrimary,
                                                fontWeight = FontWeight.Bold
                                            )
                                            if (isCompleted && stars > 0) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text("⭐".repeat(stars), fontSize = 11.sp)
                                            }
                                        }
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { onToggleFavorite(lesson.id) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Text(if (isFav) "★" else "☆", fontSize = 18.sp, color = if (isFav) Color(0xFFF59E0B) else SaaSTextMuted)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Start",
                                        tint = SaaSPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            } else if (filteredCourses.isEmpty()) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("📚", fontSize = 36.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isUrdu) "اس مضمون کے لیے ماڈیول موجود نہیں" else "No courses found for this filter",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = SaaSTextPrimary
                            )
                        }
                    }
                }
            } else {
                items(filteredCourses) { course ->
                    SaaSCourseCard(
                        course = course,
                        isUrdu = isUrdu,
                        completedIds = state.completedLessonIds,
                        favoriteIds = state.favoriteLessonIds,
                        starsMap = state.starsMap,
                        onSelectLesson = onSelectLesson,
                        onToggleFavorite = onToggleFavorite
                    )
                }

                // Milestone Challenge Card
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFFDE68A)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLaunchBossBattle() }
                            .testTag("catalog_boss_btn")
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SaaSWarningContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🎯", fontSize = 24.sp)
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isUrdu) "یونٹ کا فائنل ٹیسٹ چیلنج" else "Stage Finale Assessment",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SaaSTextPrimary
                                )
                                Text(
                                    text = if (isUrdu) "اپنی مہارت کی تصدیق کریں اور تمغہ جیتیں" else "Verify comprehensive topic mastery and claim +150 XP",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SaaSTextSecondary
                                )
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = SaaSPrimary)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun SaaSCourseCard(
    course: Course,
    isUrdu: Boolean,
    completedIds: Set<String>,
    favoriteIds: Set<String> = emptySet(),
    starsMap: Map<String, Int>,
    onSelectLesson: (Lesson) -> Unit,
    onToggleFavorite: (String) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SaaSSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SaaSPrimaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when {
                                course.subject.contains("Math") -> "📐"
                                course.subject.contains("Computer") || course.subject.contains("CS") || course.subject.contains("DSA") -> "💻"
                                course.subject.contains("English") -> "🔤"
                                course.subject.contains("Urdu") -> "📖"
                                else -> "🔬"
                            },
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = if (isUrdu) course.urduTitle else course.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Text(
                            text = course.subject,
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Units and Lessons
            course.units.forEach { unit ->
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(
                        text = if (isUrdu) unit.urduTitle else unit.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = SaaSTextSecondary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    unit.lessons.forEach { lesson ->
                        val isCompleted = completedIds.contains(lesson.id)
                        val stars = starsMap[lesson.id] ?: 0

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isCompleted) SaaSSuccessContainer else SaaSSurfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isCompleted) Color(0xFFA7F3D0) else SaaSBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { onSelectLesson(lesson) }
                                .testTag("lesson_item_${lesson.id}")
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
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (isCompleted) SaaSSuccess else SaaSSurface
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (isCompleted) {
                                            Icon(
                                                Icons.Default.Check,
                                                contentDescription = "Completed",
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        } else {
                                            Text(
                                                text = when (lesson.type) {
                                                    LessonType.SIMULATION -> "🔬"
                                                    LessonType.CODE_LAB -> "💻"
                                                    LessonType.MINIGAME_TRACING -> "✏️"
                                                    LessonType.MINIGAME_MATCHING -> "🧩"
                                                    LessonType.QUIZ -> "❓"
                                                    LessonType.STORY -> "📖"
                                                },
                                                fontSize = 13.sp
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(
                                            text = if (isUrdu) lesson.urduTitle else lesson.title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Medium,
                                            color = SaaSTextPrimary
                                        )
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = when (lesson.type) {
                                                    LessonType.SIMULATION -> "Simulation"
                                                    LessonType.CODE_LAB -> "Code Lab"
                                                    LessonType.MINIGAME_TRACING -> "Tracing"
                                                    LessonType.MINIGAME_MATCHING -> "Matching"
                                                    LessonType.QUIZ -> "Quiz"
                                                    LessonType.STORY -> "Reading"
                                                },
                                                style = MaterialTheme.typography.labelSmall,
                                                color = SaaSTextSecondary
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "+${lesson.xpReward} XP",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = SaaSPrimary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val isFav = favoriteIds.contains(lesson.id)
                                    IconButton(
                                        onClick = { onToggleFavorite(lesson.id) },
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Text(if (isFav) "★" else "☆", fontSize = 17.sp, color = if (isFav) Color(0xFFF59E0B) else SaaSTextMuted)
                                    }
                                    Spacer(modifier = Modifier.width(2.dp))
                                    if (isCompleted && stars > 0) {
                                        Text(text = "⭐".repeat(stars), fontSize = 11.sp)
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = "Start",
                                            tint = SaaSPrimary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
