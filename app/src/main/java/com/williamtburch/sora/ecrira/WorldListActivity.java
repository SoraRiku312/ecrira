package com.williamtburch.sora.ecrira;

import androidx.fragment.app.Fragment;

public class WorldListActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment(){



        return new WorldListFragment();
    }
}
