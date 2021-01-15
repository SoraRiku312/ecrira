package com.williamtburch.sora.ecrira;


import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class CharacterActivity extends SingleFragmentActivity {

    private static final String EXTRA_CHARACTER_ID =
            "com.williamtburch.sora.ecrira.character_id";

    public static Intent newIntent(Context packageContext, UUID characterId){
        Intent intent = new Intent(packageContext, CharacterActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID characterId = (UUID)getIntent().getSerializableExtra(EXTRA_CHARACTER_ID);
        return CharacterFragment.newInstance(characterId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
