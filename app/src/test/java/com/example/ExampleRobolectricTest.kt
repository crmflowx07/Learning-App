package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.domain.engine.StageEngine
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("QuestLearn", appName)
    }

    @Test
    fun `stage engine maps ages accurately`() {
        // Prep / KG
        assertEquals(1, StageEngine.getStageForAge(4))
        assertEquals(1, StageEngine.getStageForAge(5))

        // Class 1-2
        assertEquals(2, StageEngine.getStageForAge(6))
        assertEquals(2, StageEngine.getStageForAge(7))

        // Class 3-4
        assertEquals(3, StageEngine.getStageForAge(8))

        // Matric
        assertEquals(6, StageEngine.getStageForAge(14))

        // Intermediate
        assertEquals(7, StageEngine.getStageForAge(17))

        // BSCS
        assertEquals(8, StageEngine.getStageForAge(20))
    }

    @Test
    fun `placement test moves advanced learner up a stage`() {
        val initialStage = 2
        val adjusted = StageEngine.evaluatePlacementTest(initialStage, 90)
        assertEquals(3, adjusted)
    }

    @Test
    fun `alphabet data contains complete A to Z and Urdu Haroof`() {
        val eng = com.example.data.model.AlphabetData.englishAlphabet
        assertEquals(26, eng.size)
        assertEquals("A", eng.first().letter)
        assertEquals("Z", eng.last().letter)

        val urdu = com.example.data.model.AlphabetData.urduAlphabet
        assertEquals(37, urdu.size)
        assertEquals("ا", urdu.first().letter)
        assertEquals("ے", urdu.last().letter)
    }
}
