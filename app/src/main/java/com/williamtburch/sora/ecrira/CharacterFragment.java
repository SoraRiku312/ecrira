package com.williamtburch.sora.ecrira;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.williamtburch.sora.ecrira.databinding.FragmentCharacterBinding;

import java.io.File;
import java.util.UUID;

import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import static android.app.Activity.RESULT_OK;

//the fragment for the main section, the basic info stuff
public class CharacterFragment extends Fragment {
    //the id of the character
    private static final String ARG_CHARACTER_ID = "character_id";
    //resultcode for loading an image
    static final int RESULT_LOAD_IMG = 1;

    private FragmentCharacterBinding binding;

    private GridLayoutManager layoutManager;



    public static CharacterFragment newInstance(UUID characterId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER_ID, characterId);

        CharacterFragment fragment = new CharacterFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private Character mCharacter;
    private EditText mNameField;
    private EditText mAgeField;
    private Button mAddImageButton;
    private ImageView mImageView;
    private EditText mNickNameField;
    private EditText mDescriptionField;
    private RadioGroup mGenderGroup;
    private File mPhotoFile;

    private TextView mTestText;
    private Button mTestButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID characterId = (UUID)getArguments().getSerializable(ARG_CHARACTER_ID);
        mCharacter = CharacterLab.get(getActivity()).getCharacter(characterId);
        mPhotoFile = CharacterLab.get(getActivity()).getPhotoFile(mCharacter);
        setHasOptionsMenu(true);
        getActivity().setTitle(mCharacter.getNickName()); //make this show name if nickname empty
    }

    @Override
    public void onPause(){
        super.onPause();
        CharacterLab.get(getActivity()).updateCharacterBasics(mCharacter);
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
//        View v = inflater.inflate(R.layout.fragment_character, container, false);


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false);

        binding.setCharacter(mCharacter);


        mNameField = binding.characterName;
        mAgeField = binding.characterAge;


        View v = binding.getRoot();


        mNameField = (EditText) v.findViewById(R.id.character_name);
        mNameField.setText(mCharacter.getFirstName()); ////make a get full name thing
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCharacter.setFirstName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mAgeField = (EditText) v.findViewById(R.id.character_age);
        mAgeField.setText(mCharacter.getAgeAsString());
        mAgeField.addTextChangedListener(new TextWatcher() {
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
                if (s.length() != 0) {
                    mCharacter.setAgeAsString(s.toString());
                }
            }
        });

        mAddImageButton = (Button) v.findViewById(R.id.add_image_button);
        mAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.williamtburch.sora.ecrira.fileprovider",
                        mPhotoFile);
                photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });

        mImageView = (ImageView) v.findViewById(R.id.character_image_view);
        if(mCharacter.isHasImage()) {
            mImageView.setImageURI(null);
            try {
//                mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), Uri.parse(mCharacter.getImageURI())));
                Picasso.get().load(Uri.parse(mCharacter.getImageURI())).fit().centerCrop().into(mImageView);
            }catch(Exception e){
                e.getMessage();
            }
//            Picasso.get().load(mPhotoFile).fit().centerCrop().into(mImageView);
        }

        mDescriptionField = (EditText) v.findViewById(R.id.description_edit_text);
        mDescriptionField.setText(mCharacter.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCharacter.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mNickNameField = (EditText) v.findViewById(R.id.character_nickname);
        mNickNameField.setText(mCharacter.getNickName());
        mNickNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCharacter.setNickName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mGenderGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
        if(mCharacter.getGender()!=null) {
            if (mCharacter.getGender().equals("Female")) {
                mGenderGroup.check(R.id.female_radio_button);
            } else if (mCharacter.getGender().equals("Male")) {
                mGenderGroup.check(R.id.male_radio_button);
            } else if (mCharacter.getGender().equals("Other")) {
                mGenderGroup.check(R.id.other_radio_button);
            }
        }

        mGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedButton = (RadioButton)group.findViewById(checkedId);

                mCharacter.setGender(checkedButton.getText().toString());
            }
        });

        //tester for relationship tab. click the button, sends intent of going to the list just for the name
        mTestButton = (Button)v.findViewById(R.id.testbutton);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CharacterListActivity.newIntent(getActivity(), "chooser");
                startActivityForResult(intent, 2);
            }
        });

        mTestText = (TextView)v.findViewById(R.id.testext);


        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG){
                final Uri imageUri = data.getData();
                Picasso.get().load(imageUri).fit().centerCrop().into(mImageView);
                mCharacter.setImageURI(imageUri.toString());
                mCharacter.setHasImage(true);

        }
    //tester for relationship tab results. todo string constants for codes
        if(resultCode == 1 && requestCode == 2){
            final String name = data.getExtras().getString("result");
            mTestText.setText(name);

        }

    }



    //    private class CharacterAdapter extends GroupAdapter<CharacterHolder>{
//
//        private List<Character> mCharacters;
//
//        public CharacterAdapter(List<Character> characters){
//            mCharacters = characters;
//        }
//
//        public void setCharacters(List<Character> characters){
//            mCharacters = characters;
//        }
//
//    }
//}


}
