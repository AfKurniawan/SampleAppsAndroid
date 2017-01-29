package com.arioki.thekos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    android.support.v4.app.FragmentManager mFragmentManager;

    NavigationView navigationView;
    android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

        // tampilan default awal ketika aplikasii dijalankan
        if (savedInstanceState == null) {
            xfragmentTransaction.replace(R.id.frame_container, new Home()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
    //   getMenuInflater().inflate(R.menu.main, menu);
    //  return true;
    //}

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
        int id = item.getItemId();
        // Untuk memanggil layout dari menu yang dipilih
        if (id == R.id.nav_home) {
            android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.frame_container, new Home()).commit();
            // } else if (id == R.id.nav_chat) {
            //    android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            //     xfragmentTransaction.replace(R.id.frame_container,new Chat()).commit();
        } else if (id == R.id.nav_favorit) {
            android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.frame_container, new Favorit()).commit();
        } else if (id == R.id.nav_history) {
            android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.frame_container, new Histori()).commit();
        } else if (id == R.id.nav_tambahkos) {
            android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.frame_container, new AddKos()).commit();
            //} else if (id == R.id.nav_listkos) {
            //   android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            // xfragmentTransaction.replace(R.id.frame_container,new ListKos()).commit();
        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, com.arioki.thekos.user.login.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.frame_container, new Setting()).commit();
            //} else if (id == R.id.nav_about) {
            //   android.support.v4.app.FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            // xfragmentTransaction.replace(R.id.frame_container,new About()).commit();
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
