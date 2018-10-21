package com.westwingtask.data;

import android.arch.lifecycle.LiveData;
import android.support.test.runner.AndroidJUnit4;

import com.westwingtask.LiveDataTestUtil;
import com.westwingtask.data.model.Campaign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DataRepositoryTest {
    private DataRepository mDataRepository;

    @Before
    public void setUp() {
        mDataRepository = DataRepository.getInstance();
    }

    @After
    public void cleanUp() {
        DataRepository.clearInstance();
    }

    @Test
    public void preConditions() {
        assertNotNull(mDataRepository);
    }

    @Test
    public void getCampaigns() throws InterruptedException {
        LiveData<List<Campaign>> data = mDataRepository.getCampaigns();
        List<Campaign> campaigns = LiveDataTestUtil.getValue(data);
        assertNotNull(campaigns);
        assertThat(campaigns.isEmpty(), is(false));
    }
}
