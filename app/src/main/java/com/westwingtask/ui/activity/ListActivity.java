package com.westwingtask.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.westwingtask.R;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.interfaces.IListActivity;
import com.westwingtask.ui.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements IListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle(R.string.campaigns);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new ListFragment(), ListFragment.TAG)
                    .commit();
    }

    @Override
    public void showDetails(List<Campaign> campaigns, int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putParcelableArrayListExtra(DetailsActivity.EXTRAS_CAMPAIGNS, (ArrayList<? extends Parcelable>) campaigns);
        intent.putExtra(DetailsActivity.EXTRAS_POSITION, position);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_exist);
    }
}
