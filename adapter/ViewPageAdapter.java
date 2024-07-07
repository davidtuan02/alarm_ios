package com.example.myalarm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myalarm.fragment.BamgioFragment;
import com.example.myalarm.fragment.BaoThucFragment;
import com.example.myalarm.fragment.GioquocteFragment;
import com.example.myalarm.fragment.HengioFragment;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new GioquocteFragment();
            case 1 :
                return new BaoThucFragment();
            case 2 :
                return new BamgioFragment();
            case 3 :
                return new HengioFragment();
            default:
                return new BaoThucFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
