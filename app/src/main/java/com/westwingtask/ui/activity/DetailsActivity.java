package com.westwingtask.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.westwingtask.R;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.ui.adapter.DetailsAdapter;

import java.util.List;

public class DetailsActivity extends FragmentActivity {
    public static final String EXTRAS_CAMPAIGNS = "extras_campaigns";
    public static final String EXTRAS_POSITION = "extras_position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setUpPager();
        findViewById(R.id.close).setOnClickListener((View v) -> {
            finish();
        });
    }

    private void setUpPager() {
        if (getIntent() != null) {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                List<Campaign> campaigns = b.getParcelableArrayList(EXTRAS_CAMPAIGNS);
                int position = b.getInt(EXTRAS_POSITION);
                ViewPager pager = findViewById(R.id.details_pager);
                pager.setAdapter(new DetailsAdapter(getSupportFragmentManager(), campaigns));
                pager.setCurrentItem(position);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_exist, R.anim.zoom_out);
    }
}
