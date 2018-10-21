package com.westwingtask.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.westwingtask.data.DataRepository;
import com.westwingtask.data.model.Campaign;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    public ObservableBoolean loading = new ObservableBoolean(true);
    public ObservableBoolean isEmpty = new ObservableBoolean(false);
    public ObservableBoolean error = new ObservableBoolean(false);
    private DataRepository mDataRepository;

    public ListViewModel(@NonNull Application application) {
        super(application);
        this.mDataRepository = DataRepository.getInstance();
    }

    public LiveData<List<Campaign>> getCampaigns() {
        MediatorLiveData<List<Campaign>> observable = new MediatorLiveData<>();
        LiveData<List<Campaign>> campaigns = mDataRepository.getCampaigns();
        observable.addSource(campaigns, observable::setValue);
        return observable;
    }
}
