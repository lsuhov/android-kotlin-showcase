package com.test.showcase.masterdetailkotlintechnology.articleList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.showcase.masterdetail_kotlintechnology.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoadingProgressBarIsDiplayedInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticlesListActivity> mActivityRule = new ActivityTestRule<>(ArticlesListActivity.class);

    @Test
    public void checkThatProgressBarIsDisplayedAtBeginning() {

        onView(withId(R.id.articlesProgress)).check(matches(isDisplayed()));
    }
}
