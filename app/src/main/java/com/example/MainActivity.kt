package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SaaSBackground
import com.example.ui.viewmodel.QuestLearnViewModel
import com.example.ui.viewmodel.ScreenRoute

class MainActivity : ComponentActivity() {

    private val viewModel: QuestLearnViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme(darkTheme = false) {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                var showParentGate by remember { mutableStateOf(false) }
                val snackbarHostState = remember { SnackbarHostState() }

                // Handle Toast / Feedback Message
                LaunchedEffect(state.toastMessage) {
                    state.toastMessage?.let { msg ->
                        snackbarHostState.showSnackbar(msg)
                        viewModel.clearToast()
                    }
                }

                val isUrdu = state.userProfile.languageCode == "ur"
                val showBars = state.currentRoute in listOf(
                    ScreenRoute.WORLD_MAP,
                    ScreenRoute.COURSE_CATALOG,
                    ScreenRoute.REWARDS_SHOP,
                    ScreenRoute.PROFILE_BADGES
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        if (showBars) {
                            SaaSTopBar(
                                state = state,
                                onToggleLanguage = { viewModel.toggleLanguage() },
                                onOpenParentGate = { showParentGate = true },
                                onOpenProfile = { viewModel.navigateTo(ScreenRoute.PROFILE_BADGES) }
                            )
                        }
                    },
                    bottomBar = {
                        if (showBars) {
                            SaaSBottomNav(
                                currentRoute = state.currentRoute,
                                onNavigate = { viewModel.navigateTo(it) },
                                isUrdu = isUrdu
                            )
                        }
                    },
                    containerColor = SaaSBackground
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (state.currentRoute) {
                            ScreenRoute.ONBOARDING -> {
                                OnboardingScreen(
                                    state = state,
                                    onComplete = { name, age, takePlacement ->
                                        viewModel.completeOnboarding(name, age, takePlacement)
                                    }
                                )
                            }
                            ScreenRoute.PLACEMENT_TEST -> {
                                PlacementTestScreen(
                                    state = state,
                                    onBack = { viewModel.navigateTo(ScreenRoute.WORLD_MAP) },
                                    onSubmitDiagnostic = { correct, total ->
                                        viewModel.submitPlacementAnswers(correct, total)
                                    },
                                    onEnterStage = {
                                        viewModel.navigateTo(ScreenRoute.WORLD_MAP)
                                    }
                                )
                            }
                            ScreenRoute.WORLD_MAP -> {
                                WorldMapScreen(
                                    state = state,
                                    onSelectStage = { stageId ->
                                        viewModel.selectStage(stageId)
                                    },
                                    onLandmarkClick = { landmark ->
                                        val course = viewModel.repository.getCoursesForStage(state.activeStage.stageId)
                                            .find { it.id == landmark.courseId }
                                        if (course != null) {
                                            viewModel.selectCourse(course)
                                        } else {
                                            viewModel.navigateTo(ScreenRoute.COURSE_CATALOG)
                                        }
                                    },
                                    onOpenPlacementTest = {
                                        viewModel.navigateTo(ScreenRoute.PLACEMENT_TEST)
                                    },
                                    onOpenCourseCatalog = {
                                        viewModel.navigateTo(ScreenRoute.COURSE_CATALOG)
                                    },
                                    onOpenDailyChest = {
                                        viewModel.openDailyMysteryChest()
                                    },
                                    onLaunchBossBattle = {
                                        viewModel.launchBossBattle()
                                    },
                                    onCompanionSpeak = { text ->
                                        viewModel.speak(text)
                                    },
                                    onSetDailyGoal = { mins ->
                                        viewModel.setDailyGoal(mins)
                                    }
                                )
                            }
                            ScreenRoute.COURSE_CATALOG -> {
                                CourseCatalogScreen(
                                    state = state,
                                    onBack = { viewModel.navigateTo(ScreenRoute.WORLD_MAP) },
                                    onSelectLesson = { lesson ->
                                        viewModel.openLesson(lesson)
                                    },
                                    onLaunchBossBattle = {
                                        viewModel.launchBossBattle()
                                    },
                                    onToggleFavorite = { lessonId ->
                                        viewModel.toggleFavorite(lessonId)
                                    }
                                )
                            }
                            ScreenRoute.LESSON_PLAYER -> {
                                LessonPlayerScreen(
                                    lesson = state.selectedLesson,
                                    isUrdu = isUrdu,
                                    onBack = { viewModel.navigateTo(ScreenRoute.COURSE_CATALOG) },
                                    onFinishLesson = { stars, score ->
                                        viewModel.finishLesson(stars, score)
                                    },
                                    onSpeak = { text -> viewModel.speak(text) },
                                    speechRate = state.speechRate,
                                    onToggleSpeechRate = { viewModel.toggleSpeechRate() },
                                    isFavorite = state.selectedLesson?.let { state.favoriteLessonIds.contains(it.id) } == true,
                                    onToggleFavorite = { state.selectedLesson?.let { viewModel.toggleFavorite(it.id) } }
                                )
                            }
                            ScreenRoute.REWARDS_SHOP -> {
                                RewardsShopScreen(
                                    state = state,
                                    onBack = { viewModel.navigateTo(ScreenRoute.WORLD_MAP) },
                                    onBuyItem = { itemId, cost ->
                                        viewModel.buyItem(itemId, cost)
                                    }
                                )
                            }
                            ScreenRoute.PARENT_DASHBOARD -> {
                                ParentDashboardScreen(
                                    state = state,
                                    onBack = { viewModel.lockParentGate() },
                                    onUpdateDailyLimit = { limit ->
                                        viewModel.updateScreenTimeLimits(limit)
                                    },
                                    onOverrideStage = { stageId ->
                                        viewModel.parentOverrideStage(stageId)
                                    }
                                )
                            }
                            ScreenRoute.PROFILE_BADGES -> {
                                ProfileBadgesScreen(
                                    state = state,
                                    onBack = { viewModel.navigateTo(ScreenRoute.WORLD_MAP) }
                                )
                            }
                            ScreenRoute.BOSS_BATTLE -> {
                                BossBattleScreen(
                                    stageId = state.activeStage.stageId,
                                    isUrdu = isUrdu,
                                    onBack = { viewModel.navigateTo(ScreenRoute.WORLD_MAP) },
                                    onBossDefeated = { xp, coins ->
                                        viewModel.onBossDefeated(xp, coins)
                                    }
                                )
                            }
                        }

                        // Screen Time Limit Reminder Dialog
                        if (state.isScreenTimeLimitReached && state.currentRoute != ScreenRoute.PARENT_DASHBOARD) {
                            AlertDialog(
                                onDismissRequest = { /* Require parent to dismiss */ },
                                title = { Text(if (isUrdu) "وقت ختم ہو گیا!" else "Time for a Break! ⏰") },
                                text = {
                                    Text(
                                        if (isUrdu)
                                            "آج کے لیے مطالعے کا وقت مکمل ہو گیا ہے۔ آنکھوں کو آرام دیں!"
                                        else
                                            "You've reached your daily screen-time limit (${state.userProfile.dailyLimitMinutes} min). Great job learning today!"
                                    )
                                },
                                confirmButton = {
                                    Button(onClick = { showParentGate = true }) {
                                        Text(if (isUrdu) "والدین کی اجازت" else "Parent Unlock")
                                    }
                                }
                            )
                        }

                        // Parental Gate Security Dialog
                        if (showParentGate) {
                            ParentalGateDialog(
                                onDismiss = { showParentGate = false },
                                onSuccess = {
                                    showParentGate = false
                                    viewModel.navigateTo(ScreenRoute.PARENT_DASHBOARD)
                                },
                                isUrdu = isUrdu
                            )
                        }
                    }
                }
            }
        }
    }
}
