package com.williamtburch.sora.ecrira;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private ImageButton mCharacterButton;
    private ImageButton mWorldButton;
    private static String USAGE_TYPE = "home";

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    //    setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_grid, container, false);

        mCharacterButton = (ImageButton)view.findViewById(R.id.characterButton);
        mCharacterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = CharacterListActivity.newIntent(getActivity(), USAGE_TYPE);
                startActivity(intent);
            }
        });

        mWorldButton = (ImageButton)view.findViewById(R.id.worldButton);
        mWorldButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), WorldPagerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
