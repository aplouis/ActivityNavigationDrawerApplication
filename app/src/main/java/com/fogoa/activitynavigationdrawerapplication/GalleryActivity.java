package com.fogoa.activitynavigationdrawerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fogoa.activitynavigationdrawerapplication.extensions.BaseDrawerActivity;

public class GalleryActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_gallery);
        super.onCreateDrawer();

        toolbar.setTitle(R.string.title_activity_gallery);
        navigationView.setCheckedItem(R.id.nav_gallery);

    }
}
