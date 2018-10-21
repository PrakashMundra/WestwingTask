package com.westwingtask.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.westwingtask.api.Service;
import com.westwingtask.data.DataSource;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.data.model.CampaignResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {
    @Override
    public LiveData<List<Campaign>> getCampaigns() {
        MediatorLiveData<List<Campaign>> data = new MediatorLiveData<>();
        Service.getInstance().getApi().getCampaigns().enqueue(new Callback<CampaignResponse>() {
            @Override
            public void onResponse(@NonNull Call<CampaignResponse> call, @NonNull Response<CampaignResponse> response) {
                CampaignResponse campaignResponse = response.body();
                if (campaignResponse != null && campaignResponse.metadata != null)
                    data.setValue(campaignResponse.metadata.data);
            }

            @Override
            public void onFailure(@NonNull Call<CampaignResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
