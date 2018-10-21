package com.westwingtask.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.westwingtask.R;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.databinding.ItemListBinding;
import com.westwingtask.interfaces.IListActivity;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Campaign> mCampaigns;
    private Context mContext;

    public ListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Campaign> campaigns) {
        this.mCampaigns = campaigns;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list,
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Campaign campaign = mCampaigns.get(position);
        holder.mBinding.setCampaign(campaign);
        holder.mBinding.itemList.setTag(position);
        holder.mBinding.itemList.setOnClickListener((View v) -> {
            int pos = (int) v.getTag();
            if (mContext instanceof IListActivity)
                ((IListActivity) mContext).showDetails(mCampaigns, pos);
        });
    }

    @Override
    public int getItemCount() {
        return mCampaigns != null ? mCampaigns.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListBinding mBinding;

        ViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
