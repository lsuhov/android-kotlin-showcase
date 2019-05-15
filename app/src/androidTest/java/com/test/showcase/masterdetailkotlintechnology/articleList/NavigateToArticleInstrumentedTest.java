package com.test.showcase.masterdetailkotlintechnology.articleList;

import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.test.showcase.masterdetail_kotlintechnology.R;
import com.test.showcase.masterdetailkotlintechnology.ViewVisibilityIdlingResource;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.UriMatchers.hasHost;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class NavigateToArticleInstrumentedTest {

    @Rule
    public IntentsTestRule<ArticlesListActivity> mActivityRule = new IntentsTestRule<>(ArticlesListActivity.class);

    private IdlingResource idlingResource = null;

    @Before
    public void setup() {
        idlingResource = new ViewVisibilityIdlingResource(mActivityRule.getActivity(),
                R.id.recyclerView, View.VISIBLE);

        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void navigateToArticle() {
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData(hasHost("www.nytimes.com")));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));


        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        intended(expectedIntent);
    }

    @After
    public void teardown() {
        boolean result = IdlingRegistry.getInstance().unregister(idlingResource);

        assertTrue(result);
    }
}
