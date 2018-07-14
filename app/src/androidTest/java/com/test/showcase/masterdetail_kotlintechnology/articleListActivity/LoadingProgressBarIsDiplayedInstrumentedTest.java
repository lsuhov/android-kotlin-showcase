package com.test.showcase.masterdetail_kotlintechnology.articleListActivity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.test.showcase.masterdetail_kotlintechnology.R;
import com.test.showcase.masterdetailkotlintechnology.articleList.ArticlesListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoadingProgressBarIsDiplayedInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticlesListActivity> mActivityRule = new ActivityTestRule<>(ArticlesListActivity.class);

    @Test
    public void checkThatProgressBarIsDisplayedAtBeginning() {

        onView(withId(R.id.articlesProgress)).check(matches(isDisplayed()));
    }
}
