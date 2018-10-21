package com.westwingtask.api;

import com.westwingtask.data.model.CampaignResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("cms/test/data.json")
    Call<CampaignResponse> getCampaigns();
}
