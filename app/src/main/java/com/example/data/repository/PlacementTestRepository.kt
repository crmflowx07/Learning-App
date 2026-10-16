package com.example.data.repository

import com.example.data.model.QuizQuestion
import com.example.domain.engine.StageEngine

data class PlacementDiagnostic(
    val recommendedStage: Int,
    val scorePercentage: Int,
    val subjectBreakdown: Map<String, Int>, // "English" -> 80, "Math" -> 90, "Logic" -> 85
    val feedbackEnglish: String,
    val feedbackUrdu: String
)

object PlacementTestRepository {

    fun getPlacementQuestionsForAge(age: Int): List<QuizQuestion> {
        val initialStage = StageEngine.getStageForAge(age)
        return when {
            initialStage <= 2 -> listOf(
                QuizQuestion(
                    id = "pq_k1",
                    question = "Count the apples: 🍎 🍎 🍎 + 🍎 🍎 = ?",
                    urduQuestion = "سیب گنیں: 🍎 🍎 🍎 + 🍎 🍎 = کتنے ہوئے؟",
                    options = listOf("4", "5", "6", "7"),
                    urduOptions = listOf("۴", "۵", "۶", "۷"),
                    correctIndex = 1,
                    explanation = "3 + 2 = 5 apples!",
                    urduExplanation = "۳ + ۲ = ۵ سیب!"
                ),
                QuizQuestion(
                    id = "pq_k2",
                    question = "Which letter is at the start of 'Cat'?",
                    urduQuestion = "لفظ 'Cat' کا پہلا حرف کون سا ہے؟",
                    options = listOf("B", "C", "D", "A"),
                    urduOptions = listOf("B", "C", "D", "A"),
                    correctIndex = 1,
                    explanation = "C is for Cat!",
                    urduExplanation = "C سے Cat بنتا ہے!"
                ),
                QuizQuestion(
                    id = "pq_k3",
                    question = "Which shape has 3 sides?",
                    urduQuestion = "کس شکل کے ۳ کونے ہوتے ہیں؟",
                    options = listOf("Square (مربع)", "Triangle (مثلث)", "Circle (دائرہ)"),
                    urduOptions = listOf("مربع", "مثلث", "دائرہ"),
                    correctIndex = 1,
                    explanation = "A triangle has three corners and three sides.",
                    urduExplanation = "مثلث کے ۳ کونے ہوتے ہیں۔"
                )
            )
            initialStage in 3..5 -> listOf(
                QuizQuestion(
                    id = "pq_m1",
                    question = "What is 6 × 7?",
                    urduQuestion = "۶ × ۷ کا حاصل ضرب کیا ہے؟",
                    options = listOf("36", "42", "48", "49"),
                    urduOptions = listOf("۳۶", "۴۲", "۴۸", "۴۹"),
                    correctIndex = 1,
                    explanation = "6 multiplied by 7 is 42.",
                    urduExplanation = "۶ کو ۷ سے ضرب دیں تو ۴۲ آتا ہے۔"
                ),
                QuizQuestion(
                    id = "pq_m2",
                    question = "Which device is primarily used to process computer instructions?",
                    urduQuestion = "کمپیوٹر کی ہدایات پروسیس کرنے والا مرکزی آلہ کون سا ہے؟",
                    options = listOf("CPU", "Monitor", "Keyboard", "Speaker"),
                    urduOptions = listOf("سی پی یو (CPU)", "مانیٹر", "کی بورڈ", "اسپیکر"),
                    correctIndex = 0,
                    explanation = "The CPU (Central Processing Unit) executes computer instructions.",
                    urduExplanation = "سی پی یو کمپیوٹر کا دماغ ہے جو ہدایات چلاتا ہے۔"
                ),
                QuizQuestion(
                    id = "pq_m3",
                    question = "In code, what does a variable do?",
                    urduQuestion = "کوڈنگ میں ویری ایبل (Variable) کیا کام کرتا ہے؟",
                    options = listOf("Stores data / value", "Plays sound", "Deletes files"),
                    urduOptions = listOf("ڈیٹا یا قیمت محفوظ کرتا ہے", "آواز چلاتا ہے", "فائلیں حذف کرتا ہے"),
                    correctIndex = 0,
                    explanation = "A variable stores values in memory for later use.",
                    urduExplanation = "ویری ایبل میموری میں ڈیٹا محفوظ رکھنے کا ڈبہ ہے۔"
                )
            )
            else -> listOf(
                QuizQuestion(
                    id = "pq_h1",
                    question = "What is the output of print(2 ** 3) in Python?",
                    urduQuestion = "پائیتھن میں print(2 ** 3) کا نتیجہ کیا آئے گا؟",
                    options = listOf("6", "8", "5", "9"),
                    urduOptions = listOf("۶", "۸", "۵", "۹"),
                    correctIndex = 1,
                    explanation = "2 ** 3 means 2 raised to the power of 3, which is 8.",
                    urduExplanation = "۲ کی طاقت ۳، یعنی ۲ × ۲ × ۲ = ۸۔"
                ),
                QuizQuestion(
                    id = "pq_h2",
                    question = "Which data structure operates on a Last-In First-Out (LIFO) basis?",
                    urduQuestion = "کون سا ڈیٹا اسٹرکچر LIFO (سب سے آخری پہلے نکلے) اصول پر کام کرتا ہے؟",
                    options = listOf("Queue", "Stack", "Linked List", "Tree"),
                    urduOptions = listOf("قطار (Queue)", "اسٹیک (Stack)", "لنکڈ لسٹ", "ٹری"),
                    correctIndex = 1,
                    explanation = "A Stack follows Last-In First-Out (LIFO).",
                    urduExplanation = "اسٹیک (Stack) LIFO اصول پر کام کرتا ہے۔"
                ),
                QuizQuestion(
                    id = "pq_h3",
                    question = "If x + 5 = 12, what is the value of 2x?",
                    urduQuestion = "اگر x + 5 = 12 ہو، تو 2x کی کیا قیمت ہوگی؟",
                    options = listOf("7", "14", "10", "24"),
                    urduOptions = listOf("۷", "۱۴", "۱۰", "۲۴"),
                    correctIndex = 1,
                    explanation = "x = 12 - 5 = 7. Therefore 2x = 2 * 7 = 14.",
                    urduExplanation = "x = ۱۲ - ۵ = ۷۔ پس ۲x = ۱۴۔"
                )
            )
        }
    }

    fun calculateResult(age: Int, correctAnswersCount: Int, totalQuestions: Int): PlacementDiagnostic {
        val pct = if (totalQuestions > 0) (correctAnswersCount * 100) / totalQuestions else 50
        val baseStage = StageEngine.getStageForAge(age)
        val adjustedStage = StageEngine.evaluatePlacementTest(baseStage, pct)

        val breakdown = mapOf(
            "Math" to (pct + (if (pct > 70) 5 else -5)).coerceIn(0, 100),
            "Language" to pct,
            "Logic & CS" to (pct + (if (pct > 50) 10 else 0)).coerceIn(0, 100)
        )

        val feedbackEn = when {
            adjustedStage > baseStage -> "Superb! You performed ahead of your age level. Unlocking Stage $adjustedStage!"
            adjustedStage < baseStage -> "Good start! We recommend building foundational mastery in Stage $adjustedStage."
            else -> "Perfect placement! Stage $adjustedStage matches your skill level ideally."
        }

        val feedbackUr = when {
            adjustedStage > baseStage -> "شاباش! آپ کی کارکردگی عمر سے آگے ہے۔ مرحلہ $adjustedStage کھول دیا گیا ہے!"
            adjustedStage < baseStage -> "بہت اچھا آغاز! بنیاد مضبوط کرنے کے لیے مرحلہ $adjustedStage تجویز کیا جاتا ہے۔"
            else -> "شاندار! مرحلہ $adjustedStage آپ کی مہارت کے لیے بالکل موزوں ہے۔"
        }

        return PlacementDiagnostic(
            recommendedStage = adjustedStage,
            scorePercentage = pct,
            subjectBreakdown = breakdown,
            feedbackEnglish = feedbackEn,
            feedbackUrdu = feedbackUr
        )
    }
}
