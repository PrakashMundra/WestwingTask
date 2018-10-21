package com.westwingtask.data.model;

import java.util.List;

public class CampaignResponse {
    public MetaData metadata;

    public class MetaData {
        public List<Campaign> data;
    }
}
