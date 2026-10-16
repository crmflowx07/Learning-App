package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningDao {

    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    @Update
    suspend fun updateProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE courseId = :courseId")
    fun getCourseProgress(courseId: String): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId")
    suspend fun getLessonProgress(lessonId: String): LessonProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLessonProgress(progress: LessonProgressEntity)

    @Query("SELECT * FROM unlocked_badges")
    fun getUnlockedBadges(): Flow<List<UnlockedBadgeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun unlockBadge(badge: UnlockedBadgeEntity)

    @Query("SELECT * FROM unlocked_items")
    fun getUnlockedItems(): Flow<List<UnlockedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUnlockedItem(item: UnlockedItemEntity)

    @Query("UPDATE user_profile SET minutesUsedToday = minutesUsedToday + :minutes WHERE id = 1")
    suspend fun addScreenTime(minutes: Int)

    @Query("UPDATE user_profile SET assignedStage = :newStage WHERE id = 1")
    suspend fun updateAssignedStage(newStage: Int)
}
