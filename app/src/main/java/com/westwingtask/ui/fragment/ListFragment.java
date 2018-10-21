package com.westwingtask.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.westwingtask.R;
import com.westwingtask.databinding.FragmentListBinding;
import com.westwingtask.ui.adapter.ListAdapter;
import com.westwingtask.viewmodel.ListViewModel;
import com.westwingtask.widget.GridItemDecoration;

public class ListFragment extends Fragment {
    public static final String TAG = ListFragment.class.getSimpleName();
    private FragmentListBinding mBinding;
    private ListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
        final ListViewModel model = ViewModelProviders.of(this).get(ListViewModel.class);
        mBinding.setViewModel(model);
        mBinding.retry.setOnClickListener((View v) -> {
            model.loading.set(true);
            model.isEmpty.set(false);
            model.error.set(false);
            loadCampaigns(model);
        });
        loadCampaigns(model);
    }

    private void setUpRecyclerView() {
        if (getContext() != null) {
            int colSpan = (getContext().getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT) ? 1 : 2;
            mBinding.recyclerView.setHasFixedSize(true);
            mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), colSpan));
            int space = getResources().getDimensionPixelSize(R.dimen.item_margin);
            mBinding.recyclerView.addItemDecoration(new GridItemDecoration(colSpan, space));
            mAdapter = new ListAdapter(getContext());
            mBinding.recyclerView.setAdapter(mAdapter);
        }
    }

    private void loadCampaigns(ListViewModel model) {
        model.getCampaigns().observe(this, campaigns -> {
                    if (campaigns != null) {
                        if (campaigns.size() > 0) {
                            mAdapter.setData(campaigns);
                            model.isEmpty.set(false);
                        } else
                            model.isEmpty.set(true);
                    } else
                        model.error.set(true);
                    model.loading.set(false);
                }
        );
    }
}
