package com.example.domain.engine

object StageEngine {

    /**
     * Maps an age to default recommended stage (1 to 8)
     */
    fun getStageForAge(age: Int): Int {
        return when {
            age <= 5 -> 1  // Prep / KG (4-5)
            age in 6..7 -> 2  // Class 1-2
            age in 8..9 -> 3  // Class 3-4
            age in 10..11 -> 4 // Class 5-6
            age in 12..13 -> 5 // Class 7-8
            age in 14..15 -> 6 // Class 9-10 (Matric)
            age in 16..17 -> 7 // Intermediate (ICS / Pre-Eng / Pre-Med)
            else -> 8         // BSCS (18-22+)
        }
    }

    /**
     * Evaluates placement test result (score out of 100%) and adjusts initial stage
     */
    fun evaluatePlacementTest(initialStage: Int, scorePercentage: Int): Int {
        return when {
            scorePercentage >= 85 && initialStage < 8 -> initialStage + 1
            scorePercentage < 40 && initialStage > 1 -> initialStage - 1
            else -> initialStage
        }
    }

    /**
     * Checks if a parent override is permissible (allows any stage 1 to 8 with parent confirmation)
     */
    fun canParentOverrideStage(requestedStage: Int): Boolean {
        return requestedStage in 1..8
    }

    /**
     * Get age bracket label for UI
     */
    fun getStageBadge(stageId: Int, isUrdu: Boolean): String {
        return when (stageId) {
            1 -> if (isUrdu) "مرحلہ ۱: پریپ / کے جی (۴-۵ سال)" else "Stage 1: Prep / KG (Ages 4-5)"
            2 -> if (isUrdu) "مرحلہ ۲: جماعت ۱-۲ (۶-۷ سال)" else "Stage 2: Class 1-2 (Ages 6-7)"
            3 -> if (isUrdu) "مرحلہ ۳: جماعت ۳-۴ (۸-۹ سال)" else "Stage 3: Class 3-4 (Ages 8-9)"
            4 -> if (isUrdu) "مرحلہ ۴: جماعت ۵-۶ (۱۰-۱۱ سال)" else "Stage 4: Class 5-6 (Ages 10-11)"
            5 -> if (isUrdu) "مرحلہ ۵: جماعت ۷-۸ (۱۲-۱۳ سال)" else "Stage 5: Class 7-8 (Ages 12-13)"
            6 -> if (isUrdu) "مرحلہ ۶: میٹرک جماعت ۹-۱۰ (۱۴-۱۵ سال)" else "Stage 6: Matric (Ages 14-15)"
            7 -> if (isUrdu) "مرحلہ ۷: انٹرمیڈیٹ (۱۶-۱۷ سال)" else "Stage 7: Intermediate ICS/FSc"
            8 -> if (isUrdu) "مرحلہ ۸: کمپیوٹر سائنس BSCS (۱۸-۲۲ سال)" else "Stage 8: BSCS University Lab"
            else -> "Stage $stageId"
        }
    }
}
