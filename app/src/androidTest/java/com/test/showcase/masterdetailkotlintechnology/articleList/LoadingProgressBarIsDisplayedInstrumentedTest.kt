package com.test.showcase.masterdetailkotlintechnology.articleList

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import com.test.showcase.masterdetail_kotlintechnology.R

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4::class)
class LoadingProgressBarIsDisplayedInstrumentedTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(ArticlesListActivity::class.java)

    @Test
    fun checkThatProgressBarIsDisplayedAtBeginning() {

        onView(withId(R.id.articlesProgress)).check(matches(isDisplayed()))
    }
}
