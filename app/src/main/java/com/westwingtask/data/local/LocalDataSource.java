package com.westwingtask.data.local;

import android.arch.lifecycle.LiveData;

import com.westwingtask.data.DataSource;
import com.westwingtask.data.model.Campaign;

import java.util.List;

public class LocalDataSource implements DataSource {
    @Override
    public LiveData<List<Campaign>> getCampaigns() {
        return null;
    }
}
