package com.westwingtask.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.westwingtask.R;
import com.westwingtask.data.model.Campaign;
import com.westwingtask.databinding.FragmentDetailsBinding;
import com.westwingtask.utils.IntentUtils;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class DetailsFragment extends Fragment {
    public static final String EXTRAS_CAMPAIGN = "extras_campaign";
    private static final int REQ_CODE_CALL = 0X100;
    private FragmentDetailsBinding mBinding;

    public static DetailsFragment newInstance(Campaign campaign) {
        DetailsFragment f = new DetailsFragment();
        Bundle b = new Bundle();
        b.putParcelable(EXTRAS_CAMPAIGN, campaign);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            Campaign campaign = b.getParcelable(EXTRAS_CAMPAIGN);
            mBinding.setCampaign(campaign);
        }
        mBinding.callNow.setOnClickListener((View v) -> {
            if (isPermissionGranted()) {
                if (getContext() != null)
                    IntentUtils.call(getContext(), getString(R.string.customer_support));
            }
        });
    }

    public boolean isPermissionGranted() {
        if (getActivity() != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQ_CODE_CALL);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (getContext() != null)
                        IntentUtils.call(getContext(), getString(R.string.customer_support));
                }
            }
        }
    }
}
