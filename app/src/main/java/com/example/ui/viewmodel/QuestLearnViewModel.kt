package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.QuestLearnDatabase
import com.example.data.local.UserProfileEntity
import com.example.data.model.*
import com.example.data.repository.CurriculumCatalog
import com.example.data.repository.LearningRepository
import com.example.data.repository.PlacementDiagnostic
import com.example.data.repository.PlacementTestRepository
import com.example.domain.engine.StageEngine
import com.example.util.SoundManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class ScreenRoute {
    ONBOARDING,
    PLACEMENT_TEST,
    WORLD_MAP,
    COURSE_CATALOG,
    LESSON_PLAYER,
    REWARDS_SHOP,
    PARENT_DASHBOARD,
    PROFILE_BADGES,
    BOSS_BATTLE
}

data class UiState(
    val currentRoute: ScreenRoute = ScreenRoute.WORLD_MAP,
    val userProfile: UserProfileEntity = UserProfileEntity(),
    val activeStage: StageInfo = CurriculumCatalog.stages[0],
    val selectedCourse: Course? = null,
    val selectedLesson: Lesson? = null,
    val completedLessonIds: Set<String> = emptySet(),
    val favoriteLessonIds: Set<String> = emptySet(),
    val starsMap: Map<String, Int> = emptyMap(),
    val isParentGateUnlocked: Boolean = false,
    val placementResult: PlacementDiagnostic? = null,
    val isScreenTimeLimitReached: Boolean = false,
    val toastMessage: String? = null,
    val dailyChestOpened: Boolean = false,
    val dailyGoalMinutes: Int = 15,
    val speechRate: Float = 0.9f,
    val isSoundMuted: Boolean = false
)

class QuestLearnViewModel(application: Application) : AndroidViewModel(application) {

    private val db = QuestLearnDatabase.getDatabase(application)
    val repository = LearningRepository(db.learningDao())
    val soundManager = SoundManager(application)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // Collect user profile
        viewModelScope.launch {
            repository.userProfile.collect { profile ->
                val stage = repository.getStageInfo(profile.assignedStage)
                _uiState.update { current ->
                    current.copy(
                        userProfile = profile,
                        activeStage = stage,
                        isScreenTimeLimitReached = profile.minutesUsedToday >= profile.dailyLimitMinutes
                    )
                }
            }
        }

        // Collect progress
        viewModelScope.launch {
            repository.lessonProgress.collect { progressList ->
                val completedIds = progressList.map { it.lessonId }.toSet()
                val stars = progressList.associate { it.lessonId to it.starsEarned }
                _uiState.update { current ->
                    current.copy(
                        completedLessonIds = completedIds,
                        starsMap = stars
                    )
                }
            }
        }

        // Start screen-time background counter (increments used minutes every 60s)
        viewModelScope.launch {
            while (true) {
                delay(60_000)
                repository.updateScreenTime(1)
            }
        }
    }

    fun navigateTo(route: ScreenRoute) {
        soundManager.playClickSound()
        _uiState.update { it.copy(currentRoute = route) }
    }

    fun selectStage(stageId: Int) {
        soundManager.playClickSound()
        viewModelScope.launch {
            repository.setStage(stageId)
        }
    }

    fun selectCourse(course: Course) {
        soundManager.playClickSound()
        _uiState.update { it.copy(selectedCourse = course, currentRoute = ScreenRoute.COURSE_CATALOG) }
    }

    fun openLesson(lesson: Lesson) {
        soundManager.playClickSound()
        _uiState.update { it.copy(selectedLesson = lesson, currentRoute = ScreenRoute.LESSON_PLAYER) }
    }

    fun finishLesson(stars: Int, score: Int) {
        val lesson = _uiState.value.selectedLesson ?: return
        val course = _uiState.value.selectedCourse
        val courseId = course?.id ?: "course_generic"
        val stageId = _uiState.value.activeStage.stageId

        soundManager.playFanfare()

        viewModelScope.launch {
            repository.completeLesson(
                lessonId = lesson.id,
                courseId = courseId,
                stageId = stageId,
                stars = stars,
                score = score,
                xpReward = lesson.xpReward,
                coinsReward = lesson.coinsReward
            )
            val lang = _uiState.value.userProfile.languageCode
            val msg = if (lang == "ur") {
                "شاندار! +${lesson.xpReward} XP اور +${lesson.coinsReward} ستارے حاصل ہوئے!"
            } else {
                "Quest Complete! +${lesson.xpReward} XP & +${lesson.coinsReward} Stars!"
            }
            _uiState.update { it.copy(toastMessage = msg, currentRoute = ScreenRoute.COURSE_CATALOG) }
        }
    }

    fun openDailyMysteryChest() {
        if (_uiState.value.dailyChestOpened) return
        soundManager.playFanfare()
        viewModelScope.launch {
            val curr = _uiState.value.userProfile
            val updated = curr.copy(
                coins = curr.coins + 50,
                xp = curr.xp + 50
            )
            repository.saveProfile(updated)
            val lang = curr.languageCode
            val msg = if (lang == "ur") {
                "🎉 روزانہ کا پراسرار باکس کھل گیا! +50 ستارے اور +50 XP حاصل ہوئے!"
            } else {
                "🎉 Daily Mystery Chest opened! +50 Stars & +50 XP claimed!"
            }
            _uiState.update { it.copy(dailyChestOpened = true, toastMessage = msg) }
        }
    }

    fun speak(text: String) {
        val isUrdu = _uiState.value.userProfile.languageCode == "ur"
        soundManager.speak(text, isUrdu)
    }

    fun launchBossBattle() {
        soundManager.playClickSound()
        _uiState.update { it.copy(currentRoute = ScreenRoute.BOSS_BATTLE) }
    }

    fun onBossDefeated(xpBonus: Int, coinBonus: Int) {
        soundManager.playFanfare()
        viewModelScope.launch {
            val curr = _uiState.value.userProfile
            val updated = curr.copy(
                coins = curr.coins + coinBonus,
                xp = curr.xp + xpBonus
            )
            repository.saveProfile(updated)
            repository.unlockBadge("badge_boss_slayer")
            repository.unlockBadge("badge_stage_complete")
            val lang = curr.languageCode
            val msg = if (lang == "ur") {
                "🏆 باس کو شکست دے دی گئی! عظیم الشان فتح!"
            } else {
                "🏆 Boss Overcome! Glory and +$xpBonus XP awarded!"
            }
            _uiState.update { it.copy(toastMessage = msg, currentRoute = ScreenRoute.WORLD_MAP) }
        }
    }

    fun toggleLanguage() {
        soundManager.playClickSound()
        viewModelScope.launch {
            repository.toggleLanguage()
        }
    }

    fun completeOnboarding(name: String, age: Int, takePlacement: Boolean) {
        soundManager.playClickSound()
        val calculatedStage = StageEngine.getStageForAge(age)
        viewModelScope.launch {
            val updated = _uiState.value.userProfile.copy(
                name = name.ifBlank { "Young Scholar" },
                age = age,
                assignedStage = calculatedStage
            )
            repository.saveProfile(updated)
            if (takePlacement) {
                _uiState.update { it.copy(currentRoute = ScreenRoute.PLACEMENT_TEST) }
            } else {
                _uiState.update { it.copy(currentRoute = ScreenRoute.WORLD_MAP) }
            }
        }
    }

    fun submitPlacementAnswers(correctCount: Int, total: Int) {
        soundManager.playFanfare()
        val age = _uiState.value.userProfile.age
        val diag = PlacementTestRepository.calculateResult(age, correctCount, total)
        viewModelScope.launch {
            repository.setStage(diag.recommendedStage)
            _uiState.update {
                it.copy(placementResult = diag)
            }
        }
    }

    fun unlockParentGate(enteredAnswer: String, expectedAnswer: String): Boolean {
        return if (enteredAnswer.trim() == expectedAnswer.trim()) {
            soundManager.playCorrectChime()
            _uiState.update { it.copy(isParentGateUnlocked = true, currentRoute = ScreenRoute.PARENT_DASHBOARD) }
            true
        } else {
            soundManager.playWrongBuzz()
            false
        }
    }

    fun lockParentGate() {
        soundManager.playClickSound()
        _uiState.update { it.copy(isParentGateUnlocked = false, currentRoute = ScreenRoute.WORLD_MAP) }
    }

    fun updateScreenTimeLimits(newLimitMinutes: Int) {
        soundManager.playClickSound()
        viewModelScope.launch {
            val curr = _uiState.value.userProfile
            repository.saveProfile(curr.copy(dailyLimitMinutes = newLimitMinutes))
        }
    }

    fun parentOverrideStage(newStage: Int) {
        if (StageEngine.canParentOverrideStage(newStage)) {
            soundManager.playClickSound()
            viewModelScope.launch {
                repository.setStage(newStage)
                val lang = _uiState.value.userProfile.languageCode
                val msg = if (lang == "ur") "مرحلہ کامیابی سے تبدیل ہو گیا!" else "Stage successfully updated!"
                _uiState.update { it.copy(toastMessage = msg) }
            }
        }
    }

    fun buyItem(itemId: String, cost: Int) {
        viewModelScope.launch {
            val success = repository.purchaseItem(itemId, cost)
            val lang = _uiState.value.userProfile.languageCode
            if (success) {
                soundManager.playFanfare()
            } else {
                soundManager.playWrongBuzz()
            }
            val msg = if (success) {
                if (lang == "ur") "آئٹم خرید کر فعال کر دیا گیا!" else "Item equipped successfully!"
            } else {
                if (lang == "ur") "ستارے ناکافی ہیں!" else "Not enough stars!"
            }
            _uiState.update { it.copy(toastMessage = msg) }
        }
    }

    fun toggleFavorite(lessonId: String) {
        soundManager.playClickSound()
        _uiState.update { current ->
            val set = current.favoriteLessonIds.toMutableSet()
            val added = if (set.contains(lessonId)) {
                set.remove(lessonId)
                false
            } else {
                set.add(lessonId)
                true
            }
            val lang = current.userProfile.languageCode
            val msg = if (added) {
                if (lang == "ur") "پسندیدہ میں شامل کر دیا گیا!" else "Added to Bookmarks! ⭐"
            } else {
                if (lang == "ur") "پسندیدہ سے نکال دیا گیا" else "Removed from Bookmarks"
            }
            current.copy(favoriteLessonIds = set, toastMessage = msg)
        }
    }

    fun setDailyGoal(minutes: Int) {
        soundManager.playClickSound()
        _uiState.update { current ->
            val lang = current.userProfile.languageCode
            val msg = if (lang == "ur") "روزانہ ہدف: $minutes منٹ مقرر ہو گیا!" else "Daily goal set to $minutes minutes! 🎯"
            current.copy(dailyGoalMinutes = minutes, toastMessage = msg)
        }
    }

    fun toggleSpeechRate() {
        val nextRate = when (_uiState.value.speechRate) {
            0.75f -> 1.0f
            1.0f -> 1.25f
            else -> 0.75f
        }
        soundManager.setSpeechRate(nextRate)
        soundManager.playClickSound()
        _uiState.update { current ->
            val label = when (nextRate) {
                0.75f -> "0.75x (Slow/آہستہ)"
                1.0f -> "1.0x (Normal/عام)"
                else -> "1.25x (Fast/تیز)"
            }
            current.copy(speechRate = nextRate, toastMessage = "Audio Speed: $label")
        }
    }

    fun toggleSoundMute() {
        val nextMute = !_uiState.value.isSoundMuted
        soundManager.isMuted = nextMute
        if (!nextMute) soundManager.playClickSound()
        _uiState.update { current ->
            val lang = current.userProfile.languageCode
            val msg = if (nextMute) {
                if (lang == "ur") "آواز بند کر دی گئی (Mute)" else "Sound Muted 🔇"
            } else {
                if (lang == "ur") "آواز فعال ہے (Unmute)" else "Sound Enabled 🔊"
            }
            current.copy(isSoundMuted = nextMute, toastMessage = msg)
        }
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }

    override fun onCleared() {
        super.onCleared()
        soundManager.release()
    }
}
