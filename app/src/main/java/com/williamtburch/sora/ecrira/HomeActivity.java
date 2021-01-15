package com.williamtburch.sora.ecrira;


import androidx.fragment.app.Fragment;

public class HomeActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment() {
        return new HomeFragment();
    }
}
