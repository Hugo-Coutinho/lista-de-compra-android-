package com.example.administradorlocal.listadecompras.views;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.fragment.AddProductsFragment;
import com.example.administradorlocal.listadecompras.fragment.CreateProductsFragment;
import com.example.administradorlocal.listadecompras.fragment.ListProductsFragment;
import com.example.administradorlocal.listadecompras.persistence.Database;


public class shopList extends AppCompatActivity implements
        AddProductsFragment.OnFragmentInteractionListener,
        CreateProductsFragment.OnFragmentInteractionListener,
        ListProductsFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private ActionBar toolbar;
    public static Database db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "productdb").allowMainThreadQueries().build();

        /*toolbar.setTitle("shop");*/
        loadFragment(new ListProductsFragment());
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
