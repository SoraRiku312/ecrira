package com.williamtburch.sora.ecrira;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class CharacterListActivity extends AppCompatActivity {

    private static String USAGE_TYPE = "usage_type";

    public static Intent newIntent(Context packageContext, String type){
        Intent intent = new Intent(packageContext, CharacterListActivity.class);
        intent.putExtra(USAGE_TYPE, type);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = CharacterListFragment.newInstance(getIntent().getStringExtra(USAGE_TYPE));
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();

        }
    }
}
