package com.test.showcase.masterdetailkotlintechnology.articleList

import android.app.Instrumentation
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.UriMatchers.hasHost
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.showcase.masterdetail_kotlintechnology.R
import com.test.showcase.masterdetailkotlintechnology.ViewVisibilityIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigateToArticleInstrumentedTest {

    @get:Rule
    var mActivityRule = IntentsTestRule(ArticlesListActivity::class.java)

    private var idlingResource: IdlingResource? = null

    @Before
    fun setup() {
        idlingResource = ViewVisibilityIdlingResource(mActivityRule.activity,
                R.id.recyclerView, View.VISIBLE)

        IdlingRegistry.getInstance().register(idlingResource!!)
    }

    @Test
    fun navigateToArticle() {
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(hasHost("www.nytimes.com")))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))


        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        intended(expectedIntent)
    }

    @After
    fun teardown() {
        val result = IdlingRegistry.getInstance().unregister(idlingResource!!)

        assertTrue(result)
    }
}
