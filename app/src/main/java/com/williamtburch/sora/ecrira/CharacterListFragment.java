package com.williamtburch.sora.ecrira;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CharacterListFragment extends Fragment {

    private static final String USAGE_TYPE = "usage_type";

    //todo make a slide menu to add variables such as race,



    private RecyclerView mCharacterRecyclerView;
    private CharacterAdapter mAdapter;
    private String mType;

    public static CharacterListFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString(USAGE_TYPE, type);

        CharacterListFragment fragment = new CharacterListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container,
                false);
        mCharacterRecyclerView = (RecyclerView) view.findViewById(R.id.character_list_recycler_view);
        mCharacterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mType = getArguments().getString(USAGE_TYPE);

//        Character test1 = new Character();
//        test1.setFirstName("hello");
//        test1.setLastName("world");
//        test1.setAge(5);
//
//        Character test2 = new Character();
//        test1.setFirstName("foo");
//        test1.setLastName("bar");
//        test1.setAge(9);
//        CharacterLab.get(getActivity()).addCharacter(test1);
//        CharacterLab.get(getActivity()).addCharacter(test2);


        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_button:
                Character character = new Character();
                CharacterLab.get(getActivity()).addCharacter(character);
                Intent intent = CharacterPagerActivity.newIntent(getActivity(), character.getID());
                startActivity(intent);
                return true;
            case R.id.search_button:
                return true;  //// implement later
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    private void updateUI() {
        CharacterLab characterLab = CharacterLab.get(getActivity());
        List<Character> characters = characterLab.getCharacters();
        if (mAdapter == null) {
            mAdapter = new CharacterAdapter(characters);
            mCharacterRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCharacters(characters);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class CharacterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Character mCharacter;

        private TextView mNameTextView;
        private TextView mAgeTextView;
        private ImageView mImageThumbnail;
        private String mFullName;

        private String mNickname;
        private String mName;
        private int mNickLength;
        private int mNameLength;
        private boolean mNickExists = false;
        private boolean mNameExists = false;

        public CharacterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_character, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.character_name);
            mAgeTextView = (TextView) itemView.findViewById(R.id.character_age);
            mImageThumbnail = (ImageView)itemView.findViewById(R.id.character_image_thumbnail);
        }

        public void bind(Character character) {
            mCharacter = character;

            mNickname = mCharacter.getNickName();
            mName = mCharacter.getFirstName();

            if (mNickname !=null && !mNickname.isEmpty()) {
                mNickExists = true;
                mNickLength = mNickname.length();
                if(mNickLength > 12){
                    mNickLength = 12;
                }
            }

            if (mName != null && !mName.isEmpty()) {
                mNameExists = true;
                mNameLength = mName.length();
                if(mNameLength > 12){
                    mNameLength = 12;
                }
            }





    //todo bug: crashes when deleting a character with a nickname?


            if (mNickExists) {
                mNameTextView.setText(mNickname.toCharArray(), 0, mNickLength);
                if(mNickLength == 12){
                    mNameTextView.append("...");
                    //todo fix "exactly 12 characters showing ..." bug
                }
            }
            else if (mNameExists) {
                mNameTextView.setText(mName.toCharArray(), 0, mNameLength);
                if(mNickLength == 12){
                    mNameTextView.append("...");
                }
            }
            else{
                mNameTextView.setText("Unnamed");

            }



            if (mCharacter.getAge() != 0) {
                mAgeTextView.setText(mCharacter.getAgeAsString());
            } else {
                mAgeTextView.setVisibility(View.INVISIBLE);
            }
            if(mCharacter.isHasImage()){
                    mImageThumbnail.setImageURI(null);
                    try {
//                mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), Uri.parse(mCharacter.getImageURI())));
                        Picasso.get().load(Uri.parse(mCharacter.getImageURI())).fit().centerCrop().into(mImageThumbnail);
                    }catch(Exception e){
                        e.getMessage();
                    }
            } else{
                mImageThumbnail.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onClick(View view) {
            //if the usage type is chooser, it just gets the nickname. if the usage type is home, it goes into the character sheet
            if(mType != null && mType.equals("chooser")) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", mCharacter.getNickName());
                getActivity().setResult(1, returnIntent);
                getActivity().finish();
            }
            else{
                Intent intent = CharacterPagerActivity.newIntent(getActivity(), mCharacter.getID());
                startActivity(intent);
            }
        }
    }

    private class CharacterAdapter extends RecyclerView.Adapter<CharacterHolder> {

        private List<Character> mCharacters;

        public CharacterAdapter(List<Character> characters) {
            mCharacters = characters;
        }

        @Override
        public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CharacterHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CharacterHolder holder, int position) {
            Character character = mCharacters.get(position);
            holder.bind(character);
        }

        @Override
        public int getItemCount() {
            return mCharacters.size();
        }

        public void setCharacters(List<Character> characters) {
            mCharacters = characters;
        }
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
