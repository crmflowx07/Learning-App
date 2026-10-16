package com.example.data.model

enum class LessonType {
    SIMULATION,
    STORY,
    MINIGAME_TRACING,
    MINIGAME_MATCHING,
    QUIZ,
    CODE_LAB
}

data class Landmark(
    val id: String,
    val name: String,
    val urduName: String,
    val subject: String,
    val icon: String,
    val xRatio: Float, // 0.0 to 1.0 on world map
    val yRatio: Float,
    val courseId: String,
    val isLocked: Boolean = false
)

data class StageInfo(
    val stageId: Int, // 1 to 8
    val name: String,
    val urduName: String,
    val ageMin: Int,
    val ageMax: Int,
    val levelLabel: String,
    val worldTheme: String,
    val worldDescription: String,
    val companionName: String,
    val companionType: String,
    val landmarks: List<Landmark>
)

data class QuizQuestion(
    val id: String,
    val question: String,
    val urduQuestion: String,
    val options: List<String>,
    val urduOptions: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val urduExplanation: String
)

data class CodeLabChallenge(
    val id: String,
    val title: String,
    val urduTitle: String,
    val language: String, // "python", "cpp", "javascript"
    val instructions: String,
    val initialCode: String,
    val expectedOutput: String,
    val solutionCode: String,
    val hint: String
)

data class MatchingPair(
    val id: String,
    val itemA: String,
    val itemB: String
)

data class Lesson(
    val id: String,
    val unitId: String,
    val title: String,
    val urduTitle: String,
    val type: LessonType,
    val xpReward: Int,
    val coinsReward: Int,
    val order: Int,
    val questions: List<QuizQuestion> = emptyList(),
    val codeChallenge: CodeLabChallenge? = null,
    val storyPages: List<StoryPage> = emptyList(),
    val simulationType: String = "", // "COUNTING_LAB", "LOGIC_GATES_LAB", "BINARY_TREE_LAB", "SHAPES_LAB", "SORTING_LAB"
    val targetCharacter: String = "A",
    val simulationCount: Int = 5,
    val matchingPairs: List<MatchingPair> = emptyList()
)

data class StoryPage(
    val pageNumber: Int,
    val englishText: String,
    val urduText: String,
    val visualPrompt: String,
    val interactivePrompt: String? = null
)

data class CourseUnit(
    val id: String,
    val courseId: String,
    val title: String,
    val urduTitle: String,
    val order: Int,
    val lessons: List<Lesson>
)

data class Course(
    val id: String,
    val stageId: Int,
    val title: String,
    val urduTitle: String,
    val subject: String,
    val iconKey: String,
    val units: List<CourseUnit>
)

data class Avatar(
    val id: String,
    val name: String,
    val stageBracket: String, // "prep", "primary", "middle", "matric", "college", "bscs"
    val description: String,
    val isUnlocked: Boolean = true,
    val costCoins: Int = 0
)

data class AchievementBadge(
    val id: String,
    val title: String,
    val urduTitle: String,
    val description: String,
    val iconEmoji: String,
    val isUnlocked: Boolean = false,
    val progress: Float = 0f
)
