package com.test.showcase.masterdetail_kotlintechnology.articleListActivity;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.test.showcase.masterdetail_kotlintechnology.R;
import com.test.showcase.masterdetail_kotlintechnology.ViewVisibilityIdlingResource;
import com.test.showcase.masterdetailkotlintechnology.articleList.ArticlesListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
