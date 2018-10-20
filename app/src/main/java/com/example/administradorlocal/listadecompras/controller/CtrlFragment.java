package com.example.administradorlocal.listadecompras.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administradorlocal.listadecompras.R;

public class CtrlFragment implements com.example.administradorlocal.listadecompras.controller.IFragment {

    @Override
    public void goToFragment(Fragment frg,FragmentManager manager) {

        FragmentManager fm = manager;
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, frg);
        ft.commit();
    }
}
