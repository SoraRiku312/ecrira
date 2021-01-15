package com.williamtburch.sora.ecrira;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class WorldPagerActivity extends AppCompatActivity {

    private static final String EXTRA_WORLD_ID =
            "com.williamtburch.sora.ecrira.world_id";

    private ViewPager mViewPager;

    public static Intent newIntent(Context packageContext, UUID worldId){
        Intent intent = new Intent(packageContext, WorldPagerActivity.class);
        intent.putExtra(EXTRA_WORLD_ID, worldId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);

        UUID worldId = (UUID) getIntent().getSerializableExtra(EXTRA_WORLD_ID);

        mViewPager = (ViewPager)findViewById(R.id.tab_viewpager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return WorldListFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public String getPageTitle(int i){

                switch (i){
                    case 0:
                        return "World";
                    case 1:
                        return "Country";
                    case 2:
                        return "City";
                    default:
                        return "Untitled";
                }
            }
        });

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
