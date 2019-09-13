package com.example.administradorlocal.listadecompras.util.fragmentHelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.feature.main.main_container;

public class CtrlFragment implements com.example.administradorlocal.listadecompras.util.fragmentHelper.IFragment {

    @Override
    public void goToFragment(Fragment frg, FragmentManager manager, Integer navigation) {


        if (navigation == 0 || navigation == 1 || navigation == 2) {
            main_container.setBottomNavigationViewChecked(navigation);
        }

        FragmentManager fm = manager;
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, frg);
        ft.addToBackStack(null);
        ft.commit();
    }
}
