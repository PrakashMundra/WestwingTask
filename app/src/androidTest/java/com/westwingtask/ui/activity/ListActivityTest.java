package com.westwingtask.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.SystemClock;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.westwingtask.R;
import com.westwingtask.SimpleIdlingResource;
import com.westwingtask.viewmodel.ListViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {
    @Rule
    public ActivityTestRule<ListActivity> mListActivityRule = new ActivityTestRule<>(ListActivity.class);
    private SimpleIdlingResource mIdlingResource = new SimpleIdlingResource();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void complete() {
        IdlingRegistry.getInstance().unregister(mIdlingResource);
    }

    @Test
    public void loadingData() {
        mIdlingResource.setIdleState(false);
        ListViewModel viewModel = ViewModelProviders.of(mListActivityRule.getActivity()).get(ListViewModel.class);
        viewModel.getCampaigns().observeForever(campaigns -> {
            mIdlingResource.setIdleState(true);
            assertNotNull(campaigns);
            assertThat(campaigns.isEmpty(), is(false));
        });
    }

    @Test
    public void recyclerViewScroll() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.
                scrollToPosition(0));
        SystemClock.sleep(2000);
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        SystemClock.sleep(2000);
        onView(withId(R.id.recyclerView)).perform(swipeDown());
        SystemClock.sleep(2000);
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }
}
