package com.example.administradorlocal.listadecompras.feature.main;

import android.arch.persistence.room.Room;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.feature.addProducts.AddProductsFragment;
import com.example.administradorlocal.listadecompras.feature.createProducts.fragment.CreateProductsFragment;
import com.example.administradorlocal.listadecompras.feature.listProducts.fragment.ListProductsFragment;
import com.example.administradorlocal.listadecompras.persistence.database.Database;


public class shopList extends AppCompatActivity implements
        AddProductsFragment.OnFragmentInteractionListener,
        CreateProductsFragment.OnFragmentInteractionListener,
        ListProductsFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private ActionBar toolbar;
    public static BottomNavigationView mBtmView;
    public static Database db;


    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    //mTextMessage.setText(R.string.title_list);
                    /*toolbar.setTitle("lista");*/
                    fragment = new ListProductsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_add:
                    //mTextMessage.setText(R.string.title_add_product);
                    /*toolbar.setTitle("add");*/
                    fragment = new AddProductsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_create_list:
                    /*toolbar.setTitle("criar lista");*/
                    //mTextMessage.setText(R.string.title_create_List);
                    fragment = new CreateProductsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        toolbar = getSupportActionBar();
        this.mBtmView = findViewById(R.id.navigation);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "productdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        /*toolbar.setTitle("shop");*/
        loadFragment(new ListProductsFragment());
    }

    public static void setBottomNavigationViewChecked(Integer n) {

        switch (n) {
            case 0:
                mBtmView.getMenu().findItem(R.id.navigation_list).setChecked(true);
                return;
            case 1:
                mBtmView.getMenu().findItem(R.id.navigation_add).setChecked(true);
                return;
            case 2:
                mBtmView.getMenu().findItem(R.id.navigation_create_list).setChecked(true);
                return;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.i_createList:
                return false;

            case R.id.i_delete_Products:
                return false;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        // Otherwise defer to system default behavior.
        mBtmView.getMenu().findItem(R.id.navigation_list).setChecked(true);
        loadFragment(new ListProductsFragment());
    }


    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
