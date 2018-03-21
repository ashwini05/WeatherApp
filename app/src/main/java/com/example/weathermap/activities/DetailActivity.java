package com.example.weathermap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.weathermap.R;
import com.example.weathermap.fragments.TempFragment;
import com.example.weathermap.utils.Constants;

public class DetailActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void init() {
        super.init(true);
        tabLayout = (TabLayout) findViewById(R.id.activity_detail_tab);
        viewPager = (ViewPager) findViewById(R.id.activity_detail_viewpager);

        Intent intent = getIntent();
        String regionName = intent.getStringExtra(Constants.REGION_NAME);
        setToolbarTitle(regionName + " " + getString(R.string.region));
        TempPagerAdapter adapter = new TempPagerAdapter(getSupportFragmentManager(), regionName);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class TempPagerAdapter extends FragmentStatePagerAdapter {
        private String regionName;

        public TempPagerAdapter(FragmentManager fm, String regionName) {
            super(fm);
            this.regionName = regionName;
        }

        @Override
        public Fragment getItem(int position) {
            TempFragment tempFragment = TempFragment.newInstance(position, regionName);
            return tempFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.max);
                case 1:
                    return getString(R.string.min);
                case 2:
                    return getString(R.string.sunshine);
                case 3:
                    return getString(R.string.rainfall);
                default:
                    return null;
            }
        }
    }

}
