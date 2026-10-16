package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Young Scholar",
    val age: Int = 6,
    val assignedStage: Int = 2,
    val xp: Int = 120,
    val coins: Int = 50,
    val currentStreak: Int = 3,
    val lastActiveDate: String = "",
    val avatarId: String = "avatar_kid_explorer",
    val languageCode: String = "en", // "en" or "ur"
    val dailyLimitMinutes: Int = 60,
    val minutesUsedToday: Int = 12,
    val parentPin: String = "1234",
    val placementTestTaken: Boolean = false
)

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val courseId: String,
    val stageId: Int,
    val starsEarned: Int,
    val bestScore: Int,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_badges")
data class UnlockedBadgeEntity(
    @PrimaryKey val badgeId: String,
    val unlockedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_items")
data class UnlockedItemEntity(
    @PrimaryKey val itemId: String,
    val isEquipped: Boolean = false,
    val purchasedAt: Long = System.currentTimeMillis()
)
