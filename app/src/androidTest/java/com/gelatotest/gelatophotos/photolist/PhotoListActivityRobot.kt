package com.gelatotest.gelatophotos.photolist

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import com.gelatotest.gelatophotos.R
import org.hamcrest.Description
import org.hamcrest.Matcher


class PhotoListActivityRobot {
    fun seesPhotoList(): PhotoListActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))
        return this
    }

    fun seesTitle(position: Int, author: String): PhotoListActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(matches(atPositionOnView(position, withText(author), R.id.txt_news_name)))
        return this
    }

    private fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>):Matcher<View>{
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java){

            override fun describeTo(description: Description?) {
                description?.appendText("has item at position $position: ")
            }

            override fun matchesSafely(item: RecyclerView): Boolean {
                val viewHolder = item.findViewHolderForAdapterPosition(position)
                return itemMatcher.matches(viewHolder?.itemView)
            }
        }
    }

    fun atPositionOnView(
        position: Int,
        itemMatcher: Matcher<View>,
        targetViewId: Int
    ): Matcher<View> {

        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder!!.itemView.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }
}