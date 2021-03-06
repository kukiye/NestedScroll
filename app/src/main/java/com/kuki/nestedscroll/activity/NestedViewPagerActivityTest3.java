package com.kuki.nestedscroll.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kuki.nestedscroll.R;
import com.kuki.nestedscroll.databinding.ActivityNestedViewPagerTestBinding;
import com.kuki.nestedscroll.fragment.RecyclerViewFragment;
import com.kuki.nestedscroll.viewpager.ViewPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class NestedViewPagerActivityTest3 extends AppCompatActivity {
    ActivityNestedViewPagerTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nested_view_pager_test);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this, getPageFragments());
        binding.viewpagerView.setAdapter(pagerAdapter);
        final String[] labels = new String[]{"linear", "scroll", "recycler"};
        new TabLayoutMediator(binding.tablayout, binding.viewpagerView, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(labels[position]);
            }
        }).attach();
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("NestedViewPagerActivity", "Refresh started...");
                binding.getRoot().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private List<Fragment> getPageFragments() {
        List<Fragment> data = new ArrayList<>();
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        return data;
    }
}
