package com.gelatotest.gelatophotos.photolist

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.gelatotest.gelatophotos.GelatoPhotosApplication
import com.gelatotest.gelatophotos.MockApp
import com.gelatotest.gelatophotos.mocks.MockWebServerRobot
import com.gelatotest.gelatophotos.mocks.MockWebServerRule
import com.gelatotest.gelatophotos.mocks.UserAction
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PhotoListActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(PhotoListActivity::class.java, false, false)
    @get:Rule
    val mockWebServerRule = MockWebServerRule()
    private val mockWebServerRobot = MockWebServerRobot(mockWebServerRule)
    private val photoListActivityRobot = PhotoListActivityRobot()
    private lateinit var context: Context

    @Before
    fun setUp(){
        context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        (context as MockApp).appComponentForTest.inject(this)
    }

    @Test
    fun onLaunch_seesPhotoList(){
        activityRule.launchActivity(null)
        mockWebServerRobot
            .useDefaultDispatcher()
            .performNoSyncAction(object : UserAction {
                override fun perform() {
                    photoListActivityRobot.seesPhotoList()
                    photoListActivityRobot.seesTitle(0, "Stefan Kunze")
                    photoListActivityRobot.seesTitle(1, "Liane Metzler")
                }
            })
        }
}