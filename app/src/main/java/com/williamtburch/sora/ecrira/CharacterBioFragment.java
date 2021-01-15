package com.williamtburch.sora.ecrira;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.williamtburch.sora.ecrira.databinding.FragmentCharacterBioBinding;

import java.util.UUID;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
//the fragment for the Biography section. just a big edittext. maybe add life details below it
public class CharacterBioFragment extends Fragment {

    private static final String ARG_CHARACTER_ID = "character_id";

    private FragmentCharacterBioBinding binding;


    public static CharacterBioFragment newInstance(UUID characterId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER_ID, characterId);
        CharacterBioFragment fragment = new CharacterBioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Character mCharacter;
    private EditText mBio;


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
       CharacterLab.get(getActivity()).updateCharacterBio(mCharacter);
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


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_bio, container, false);

        binding.setCharacter(mCharacter);


        View v = binding.getRoot();


        mBio = (EditText)v.findViewById(R.id.bio_text);
        mBio.setText(mCharacter.getBiography());
        mBio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
            }

            @Override
            public void afterTextChanged(Editable s) {

                mCharacter.setBiography(s.toString());
            }
        });








        return v;



    }

}
