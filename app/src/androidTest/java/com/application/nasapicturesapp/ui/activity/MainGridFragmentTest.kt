package com.application.nasapicturesapp.ui.activity


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.application.nasapicturesapp.R
import org.hamcrest.BaseMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.Description
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainGridFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainGridFragmentTest() {
        Thread.sleep(2000)

        val recyclerView = onView(
            allOf(
                withId(R.id.recylerviewMain),
                withParent(withParent(withId(R.id.maincontainer))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        onView(allOf(isDisplayed(),
            first(withParent(withId(R.id.recylerviewMain)))))
            .perform(click())

    }
    fun <T> first(matcher: Matcher<T>): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("first item")
            }

            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }


        }
    }
}
