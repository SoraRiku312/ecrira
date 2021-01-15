package com.williamtburch.sora.ecrira;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.williamtburch.sora.ecrira.databinding.FragmentCharacterAppearBinding;

import java.util.UUID;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class CharacterAppearFragment extends Fragment {

    private static final String ARG_CHARACTER_ID = "character_id";

    private FragmentCharacterAppearBinding binding;


    public static CharacterAppearFragment newInstance(UUID characterId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER_ID, characterId);
        CharacterAppearFragment fragment = new CharacterAppearFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Character mCharacter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID characterId = (UUID)getArguments().getSerializable(ARG_CHARACTER_ID);
        mCharacter = CharacterLab.get(getActivity()).getCharacter(characterId);
        setHasOptionsMenu(true);
        getActivity().setTitle(mCharacter.getNickName());
    }

    @Override
    public void onPause(){
        super.onPause();
//        CharacterLab.get(getActivity()).updateCharacterBasics(mCharacter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_character, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_character:
                CharacterLab.get(getActivity()).deleteCharacter(mCharacter);
                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_appear, container, false);

        binding.setCharacter(mCharacter);


        View v = binding.getRoot();











        return v;



    }

}

