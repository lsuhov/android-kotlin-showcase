package com.test.showcase.masterdetailkotlintechnology.articleList

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.test.showcase.masterdetail_kotlintechnology.R
import com.test.showcase.masterdetailkotlintechnology.ViewVisibilityIdlingResource
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleListActivityTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(ArticlesListActivity::class.java)

    private var idlingResource: IdlingResource? = null

    @Before
    fun setup() {
        idlingResource = ViewVisibilityIdlingResource(mActivityRule.activity,
                R.id.recyclerView, View.VISIBLE)

        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun teardown() {
        val result = IdlingRegistry.getInstance().unregister(idlingResource)

        assertTrue(result)
    }

    @Test
    fun checkThatRecyclerViewIsLoaded() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkThatFilterAreaIsShown() {
        onView(withId(R.id.filterMenuItem)).perform(click())
        onView(withId(R.id.filterLayout)).check(matches(isDisplayed()))
    }
}