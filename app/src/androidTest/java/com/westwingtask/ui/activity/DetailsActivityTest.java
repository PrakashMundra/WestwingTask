package com.westwingtask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.westwingtask.R;
import com.westwingtask.SimpleIdlingResource;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.data.model.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {
    @Rule
    public ActivityTestRule<DetailsActivity> mDetailsActivityRule = new ActivityTestRule<>(DetailsActivity.class);
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
    public void launchActivity() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putParcelableArrayListExtra(DetailsActivity.EXTRAS_CAMPAIGNS, (ArrayList<? extends Parcelable>) getCampaigns());
        intent.putExtra(DetailsActivity.EXTRAS_POSITION, 1);
        mDetailsActivityRule.launchActivity(intent);
        SystemClock.sleep(1000);
        onView(withId(R.id.details_pager)).perform(swipeLeft());
        SystemClock.sleep(1000);
        onView(withId(R.id.details_pager)).perform(swipeLeft());
        SystemClock.sleep(1000);
        onView(withId(R.id.details_pager)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(withId(R.id.details_pager)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.call_now), isDisplayed())).perform(click());
    }

    private List<Campaign> getCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Kreative Wandideen", "Testing description",
                new Image("https://d2ewgkjyvye2k.cloudfront.net/image/upload/v1504017209/club/de/campaign/DEIDEA3/original")));
        campaigns.add(new Campaign("Blaupause f\u00fcrs Interior", "Testing description",
                new Image("https://d2ewgkjyvye2k.cloudfront.net/image/upload/v1504017209/club/de/campaign/DEAQUA4/original")));
        campaigns.add(new Campaign("Der LG HomBot Square", "Testing description",
                new Image("https://d2ewgkjyvye2k.cloudfront.net/image/upload/v1504017209/club/de/campaign/DELGSA1/original")));
        campaigns.add(new Campaign("Wir \u2661 Lagom", "Testing description",
                new Image("https://d2ewgkjyvye2k.cloudfront.net/image/upload/v1504017209/club/de/campaign/DELAGO3/original")));
        return campaigns;
    }
}
