package com.siri.comiccharacters;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HeroListActivity extends SingleFragmentActivity {
//
//    @Override
//    protected Fragment createFragment() {
//        return new HeroListFragment();
//    }
@Override
protected Fragment createFragment() {

    return HeroListFragment.newInstance();
}

}
