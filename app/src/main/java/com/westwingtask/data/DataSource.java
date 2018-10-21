package com.westwingtask.data;

import android.arch.lifecycle.LiveData;

import com.westwingtask.data.model.Campaign;

import java.util.List;

public interface DataSource {
    LiveData<List<Campaign>> getCampaigns();
}
