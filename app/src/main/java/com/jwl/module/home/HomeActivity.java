package com.jwl.module.home;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.jwl.GlobalConfig;
import com.jwl.R;
import com.jwl.entity.ArticleData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private ViewPager mViewPager;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCoordinatorLayout = ((CoordinatorLayout) findViewById(R.id.coordinatorlayout));
        toolbar.setTitle("干货");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mFragmentList = new ArrayList<>();
        initTabLayout("干货");
        initFragments("干货");
        setDatas();
        mTabLayout.setupWithViewPager(mViewPager);
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_gank:
                break;
            case R.id.nav_article:
                break;
            case R.id.nav_news:
                break;
            case R.id.nav_zhihu:
                break;
            case R.id.nav_video:
                break;
            case R.id.nav_about:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initTabLayout(String title) {
        mTabLayout = ((TabLayout) findViewById(R.id.tablayout));
        switch (title) {
            case "干货":
                for (int i = 0; i < GlobalConfig.CATEGORIES.length; i++) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(GlobalConfig.CATEGORIES[i]));
                }
                break;
        }


    }

    @Override
    public void initFragments(String title) {
        switch (title) {
            case "干货":
                for (int i=0;i<GlobalConfig.CATEGORIES.length;i++) {
                    mFragmentList.add(HomeFragment.newInstance(GlobalConfig.CATEGORIES[i]));
                }
                break;
        }
    }

    @Override
    public void setDatas() {
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        mViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(),mFragmentList));
    }
}
