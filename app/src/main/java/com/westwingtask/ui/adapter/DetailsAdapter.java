package com.westwingtask.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.westwingtask.data.model.Campaign;
import com.westwingtask.ui.fragment.DetailsFragment;

import java.util.List;

public class DetailsAdapter extends FragmentStatePagerAdapter {
    private List<Campaign> mCampaigns;

    public DetailsAdapter(FragmentManager fm, List<Campaign> campaigns) {
        super(fm);
        this.mCampaigns = campaigns;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(mCampaigns.get(position));
    }

    @Override
    public int getCount() {
        return (mCampaigns != null ? mCampaigns.size() : 0);
    }
}
