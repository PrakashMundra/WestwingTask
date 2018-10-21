package com.westwingtask.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.VisibleForTesting;

import com.westwingtask.data.local.LocalDataSource;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.data.remote.RemoteDataSource;

import java.util.List;

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE;
    private RemoteDataSource mRemoteDataSource;
    private LocalDataSource mLocalDataSource;

    private DataRepository() {
        this.mRemoteDataSource = new RemoteDataSource();
        this.mLocalDataSource = new LocalDataSource();
    }

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                INSTANCE = new DataRepository();
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

    @Override
    public LiveData<List<Campaign>> getCampaigns() {
        return mRemoteDataSource.getCampaigns();
    }
}
