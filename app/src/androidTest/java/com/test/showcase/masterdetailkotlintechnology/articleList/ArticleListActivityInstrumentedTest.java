package com.test.showcase.masterdetailkotlintechnology.articleList;

import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.showcase.masterdetail_kotlintechnology.R;
import com.test.showcase.masterdetailkotlintechnology.ViewVisibilityIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class ArticleListActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticlesListActivity> mActivityRule = new ActivityTestRule<>(ArticlesListActivity.class);

    private IdlingResource idlingResource = null;

    @Before
    public void setup() {
        idlingResource = new ViewVisibilityIdlingResource(mActivityRule.getActivity(),
                R.id.recyclerView, View.VISIBLE);

        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void checkThatRecyclerViewIsLoaded() {
        onView((withId(R.id.recyclerView))).check(matches(isDisplayed()));

    }

    @After
    public void teardown() {
        boolean result = IdlingRegistry.getInstance().unregister(idlingResource);

        assertTrue(result);
    }
}
