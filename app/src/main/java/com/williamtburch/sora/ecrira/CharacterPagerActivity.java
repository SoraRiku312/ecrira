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

public class CharacterPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private UUID characterId;

    private static final String EXTRA_CHARACTER_ID =
            "com.williamtburch.sora.ecrira.character_id";


    public static Intent newIntent(Context packageContext, UUID characterId){
        Intent intent = new Intent(packageContext, CharacterPagerActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        return intent;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);

        mViewPager = (ViewPager)findViewById(R.id.tab_viewpager);

        mViewPager.setOffscreenPageLimit(4);


        characterId = (UUID)getIntent().getSerializableExtra(EXTRA_CHARACTER_ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return CharacterFragment.newInstance(characterId);
                }
                if(position == 1){
                    return CharacterBioFragment.newInstance(characterId);
                }
                if(position == 2){
                    return CharacterInterviewFragment.newInstance(characterId);
                }
                else{
                    return HomeFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public String getPageTitle(int position){

                switch(position){
                    case 0:
                        return "Basic Info";
                    case 1:
                        return "Bio";
                    case 2:
                        return "Interview";
                    case 3:
                        return "Under Construction";
                    case 4:
                        return "Interview";
                    default:
                        return "Untitled";
                }



            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);


    }
}
