package com.example.data.repository

import com.example.data.local.*
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LearningRepository(private val dao: LearningDao) {

    val userProfile: Flow<UserProfileEntity> = dao.getUserProfile().map { entity ->
        entity ?: UserProfileEntity()
    }

    val lessonProgress: Flow<List<LessonProgressEntity>> = dao.getAllProgress()

    val unlockedBadges: Flow<List<UnlockedBadgeEntity>> = dao.getUnlockedBadges()

    suspend fun getInitialProfile(): UserProfileEntity {
        var profile = dao.getUserProfileOnce()
        if (profile == null) {
            profile = UserProfileEntity()
            dao.insertOrUpdateProfile(profile)
        }
        return profile
    }

    suspend fun saveProfile(profile: UserProfileEntity) {
        dao.insertOrUpdateProfile(profile)
    }

    suspend fun setStage(newStage: Int) {
        val current = dao.getUserProfileOnce() ?: UserProfileEntity()
        dao.insertOrUpdateProfile(current.copy(assignedStage = newStage))
    }

    suspend fun completeLesson(
        lessonId: String,
        courseId: String,
        stageId: Int,
        stars: Int,
        score: Int,
        xpReward: Int,
        coinsReward: Int
    ) {
        // Save progress
        val progress = LessonProgressEntity(
            lessonId = lessonId,
            courseId = courseId,
            stageId = stageId,
            starsEarned = stars,
            bestScore = score,
            completedAt = System.currentTimeMillis()
        )
        dao.saveLessonProgress(progress)

        // Award XP and coins to user profile
        val profile = dao.getUserProfileOnce() ?: UserProfileEntity()
        val updated = profile.copy(
            xp = profile.xp + xpReward,
            coins = profile.coins + coinsReward,
            placementTestTaken = true
        )
        dao.insertOrUpdateProfile(updated)

        // Check first lesson badge
        dao.unlockBadge(UnlockedBadgeEntity(badgeId = "badge_first_step"))
        if (lessonId.contains("code") || lessonId.contains("py") || lessonId.contains("pf")) {
            dao.unlockBadge(UnlockedBadgeEntity(badgeId = "badge_code_init"))
        }
        if (lessonId.contains("dld") || lessonId.contains("bst")) {
            dao.unlockBadge(UnlockedBadgeEntity(badgeId = "badge_logic_guru"))
        }
    }

    suspend fun unlockBadge(badgeId: String) {
        dao.unlockBadge(UnlockedBadgeEntity(badgeId = badgeId))
    }

    suspend fun updateScreenTime(minutes: Int) {
        dao.addScreenTime(minutes)
    }

    suspend fun toggleLanguage(targetLang: String? = null) {
        val current = dao.getUserProfileOnce() ?: UserProfileEntity()
        val nextLang = targetLang ?: if (current.languageCode == "en") "ur" else "en"
        dao.insertOrUpdateProfile(current.copy(languageCode = nextLang))
    }

    suspend fun purchaseItem(itemId: String, cost: Int): Boolean {
        val profile = dao.getUserProfileOnce() ?: UserProfileEntity()
        if (profile.coins >= cost) {
            dao.insertOrUpdateProfile(profile.copy(coins = profile.coins - cost, avatarId = itemId))
            dao.saveUnlockedItem(UnlockedItemEntity(itemId = itemId, isEquipped = true))
            return true
        }
        return false
    }

    fun getStageInfo(stageId: Int): StageInfo {
        return CurriculumCatalog.stages.find { it.stageId == stageId }
            ?: CurriculumCatalog.stages[0]
    }

    fun getCoursesForStage(stageId: Int): List<Course> {
        return CurriculumCatalog.allCourses.filter { it.stageId == stageId }
    }

    fun getLessonById(lessonId: String): Lesson? {
        for (course in CurriculumCatalog.allCourses) {
            for (unit in course.units) {
                for (lesson in unit.lessons) {
                    if (lesson.id == lessonId) return lesson
                }
            }
        }
        return null
    }
}
