package com.example.administradorlocal.listadecompras.util.alertDialog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.administradorlocal.listadecompras.util.fragmentHelper.IFragment;

public interface IAlertDialog {

    void alertPositiveButton(String msg, Context ctx, final Integer navigation, final Fragment frg, final FragmentManager fm, final IFragment fragment);

}
