package com.fogoa.activitynavigationdrawerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fogoa.activitynavigationdrawerapplication.extensions.BaseDrawerActivity;

public class ShareActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_share);
        super.onCreateDrawer();

        toolbar.setTitle(R.string.title_activity_share);
        navigationView.setCheckedItem(R.id.nav_share);


    }
}
