package com.example.chaowang.imageapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.chaowang.imageapplication.utils.DataUtils;
import com.example.chaowang.imageapplication.utils.RetrofitUtils;
import com.example.chaowang.imageapplication.view.ComingSoonFragment;
import com.example.chaowang.imageapplication.view.InTheatersFragment;
import com.example.chaowang.imageapplication.view.NewMoviesFragment;
import com.example.chaowang.imageapplication.view.TopFragment;
import com.example.chaowang.imageapplication.view.USBoxFragment;
import com.example.chaowang.imageapplication.view.WeeklyFragment;

import java.util.HashMap;

import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFM;
    private InTheatersFragment mInTheatersFragment;
    private ComingSoonFragment mComingSoonFragment;
    private WeeklyFragment mWeeklyFragment;
    private USBoxFragment mUSBoxFragment;
    private TopFragment mTopFragment;
    private NewMoviesFragment mNewMoviesFragment;

    private HashMap<String, Bitmap> hashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (BuildConfig.DEBUG)
        {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        hashMap.put();

        getDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getDefaultFragment() {
        mFM = getFragmentManager();
        mInTheatersFragment = (InTheatersFragment)mFM.findFragmentByTag("in_theaters");
        if (mInTheatersFragment == null)
        {
            mInTheatersFragment = new InTheatersFragment();
        }
        FragmentTransaction transaction = mFM.beginTransaction();
        transaction.replace(R.id.id_content, mInTheatersFragment, "in_theaters");
        transaction.commit();
        getSupportActionBar().setTitle(R.string.in_theaters);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataUtils.getInstance().setNull();
        RetrofitUtils.getInstance().setNull();
        mFM = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        replaceFragment(item);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(MenuItem item)
    {
        FragmentTransaction transaction = mFM.beginTransaction();
        switch (item.getItemId())
        {
            case R.id.in_theaters:
                if (mInTheatersFragment == null)
                {
                    mInTheatersFragment = new InTheatersFragment();
                }
                transaction.replace(R.id.id_content, mInTheatersFragment);
                getSupportActionBar().setTitle(R.string.in_theaters);
                break;
            case R.id.coming_soon:
                if (mComingSoonFragment == null){
                    mComingSoonFragment = new ComingSoonFragment();
                }
                transaction.replace(R.id.id_content, mComingSoonFragment);
                getSupportActionBar().setTitle(R.string.coming_soon);
                break;
            case R.id.weekly:
                if (mWeeklyFragment == null){
                    mWeeklyFragment = new WeeklyFragment();
                }
                transaction.replace(R.id.id_content, mWeeklyFragment);
                getSupportActionBar().setTitle(R.string.weekly);
                break;
            case R.id.us_box:
                if (mUSBoxFragment == null){
                    mUSBoxFragment = new USBoxFragment();
                }
                transaction.replace(R.id.id_content, mUSBoxFragment);
                getSupportActionBar().setTitle(R.string.us_box);
                break;
            case R.id.top:
                if (mTopFragment == null){
                    mTopFragment = new TopFragment();
                }
                transaction.replace(R.id.id_content, mTopFragment);
                break;
            case R.id.new_movies:
                if (mNewMoviesFragment == null){
                    mNewMoviesFragment = new NewMoviesFragment();
                }
                transaction.replace(R.id.id_content, mNewMoviesFragment);
                getSupportActionBar().setTitle(R.string.new_movies);
                break;
        }
        transaction.commit();
    }
}
