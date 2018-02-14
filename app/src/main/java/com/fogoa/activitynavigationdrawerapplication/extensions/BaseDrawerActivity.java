package com.fogoa.activitynavigationdrawerapplication.extensions;


import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.fogoa.activitynavigationdrawerapplication.GalleryActivity;
import com.fogoa.activitynavigationdrawerapplication.MainActivity;
import com.fogoa.activitynavigationdrawerapplication.ManageActivity;
import com.fogoa.activitynavigationdrawerapplication.R;
import com.fogoa.activitynavigationdrawerapplication.SendActivity;
import com.fogoa.activitynavigationdrawerapplication.ShareActivity;
import com.fogoa.activitynavigationdrawerapplication.SlideShowActivity;
import com.fogoa.activitynavigationdrawerapplication.data.models.UserItem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BaseDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = BaseDrawerActivity.class.getSimpleName();

    public NavigationView navigationView;
    public View headerNavigationView;

    //UI elements
    protected DrawerLayout drawer;
    protected Toolbar toolbar;
    NavigationView nav_view;
    TextView tvNavName;
    ImageView ivNavHeader;
    TextView tvNavEmail;


    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        //if (Constants.DEBUG) Log.e(TAG, "setContentView inflate content layout: "+layoutResID);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    protected void onCreateDrawer() {
        //isLoginReq = true;
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle(R.string.title_activity_main);
        //setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_camera);
        headerNavigationView = navigationView.getHeaderView(0); // 0-index header
        ivNavHeader = (ImageView) headerNavigationView.findViewById(R.id.ivNavHeader);
        tvNavName = (TextView) headerNavigationView.findViewById(R.id.tvNavName);
        tvNavEmail= (TextView) headerNavigationView.findViewById(R.id.tvNavEmail);

        //setup date
        if(appContext.currentDate == null) {
            appContext.currentDate = new Date();
        }

        if (appContext.userItem!=null) {
            onUserUpdate(appContext.userItem);
        }
        else {
            appContext.userItem = new UserItem();
            appContext.userItem.first_name = "My";
            appContext.userItem.last_name = "User";
            appContext.userItem.email_address = "me@domain.com";
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (appContext.userItem!=null) {
            onUserUpdate(appContext.userItem);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getActivity() instanceof MainActivity) {
                super.onBackPressed();
            }
            else {
                goToNavDrawerItem(R.id.nav_camera);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        if (item.isChecked()){
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);

        final int id = item.getItemId();
        goToNavDrawerItem(id);

        //return true;
        return false;  //return false does not check the selected item
    }

    private void goToNavDrawerItem(int id) {
        Intent intent = null;
        if (id == R.id.nav_camera) {
            intent = new Intent(getActivity(), MainActivity.class);
        }
        else if (id == R.id.nav_gallery) {
            intent = new Intent(getActivity(), GalleryActivity.class);
        }
        else if (id == R.id.nav_slideshow) {
            intent = new Intent(getActivity(), SlideShowActivity.class);
        }
        else if (id == R.id.nav_manage) {
            intent = new Intent(getActivity(), ManageActivity.class);
        }
        else if (id == R.id.nav_share) {
            intent = new Intent(getActivity(), ShareActivity.class);
        }
        else if (id == R.id.nav_send) {
            intent = new Intent(getActivity(), SendActivity.class);
        }
        if (intent!=null) {
            getActivity().startActivity(intent);
            /*
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
            else {
                getActivity().startActivity(intent);
            }
            */
            finish();
        }
    }

    @Override
    protected void onUserUpdate(UserItem userItem) {
        super.onUserUpdate(userItem);

        //if (Constants.DEBUG) Log.e(TAG, "onUserUpdate: "+userItem.first_name+" "+userItem.last_name);
        if (tvNavName!=null) {
            String strName = userItem.first_name+" "+userItem.last_name;
            tvNavName.setText(strName);
        }
        if (tvNavEmail!=null) {
            tvNavEmail.setText(userItem.email_address);
        }
    }


}
