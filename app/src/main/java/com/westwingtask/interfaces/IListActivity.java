package com.westwingtask.interfaces;

import com.westwingtask.data.model.Campaign;

import java.util.List;

public interface IListActivity {
    void showDetails(List<Campaign> campaigns, int position);
}
