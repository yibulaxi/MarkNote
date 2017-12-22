package me.shouheng.notepal.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import me.shouheng.notepal.R;
import me.shouheng.notepal.databinding.ActivityMainBinding;
import me.shouheng.notepal.databinding.ActivityMainNavHeaderBinding;
import me.shouheng.notepal.intro.IntroActivity;

public class MainActivity extends CommonActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void beforeSetContentView() {
        setTranslucentStatusBar();
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {

        IntroActivity.launchIfNecessary(this);

        configToolbar();

        initHeaderView();
    }

    private void initHeaderView() {
        View header = getBinding().nav.inflateHeaderView(R.layout.activity_main_nav_header);
        ActivityMainNavHeaderBinding headerBinding = DataBindingUtil.bind(header);
        header.setOnClickListener(v -> {});
        header.setOnLongClickListener(v -> true);
    }

    private void configToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_main_menu_white);
        if (!isDarkTheme()) toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getBinding().drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getBinding().drawerLayout.isDrawerOpen(GravityCompat.START)){
            getBinding().drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}