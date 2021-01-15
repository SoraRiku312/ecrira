package com.williamtburch.sora.ecrira;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class WorldActivity extends SingleFragmentActivity {

    private static final String EXTRA_WORLD_ID =
            "com.williamtburch.sora.ecrira.world_id";

    public static final Intent newIntent(Context packageContext, UUID worldId) {
        Intent intent = new Intent(packageContext, WorldActivity.class);
        intent.putExtra(EXTRA_WORLD_ID, worldId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID worldId = (UUID)getIntent().getSerializableExtra(EXTRA_WORLD_ID);
        return CharacterFragment.newInstance(worldId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
