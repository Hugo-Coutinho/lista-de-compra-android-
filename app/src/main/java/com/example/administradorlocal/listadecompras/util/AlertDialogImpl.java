package com.example.administradorlocal.listadecompras.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import com.example.administradorlocal.listadecompras.controller.IFragment;

public class AlertDialogImpl implements IAlertDialog {


    @Override
    public void alertPositiveButton(String msg, Context ctx, final Integer navigation, final Fragment frg, final FragmentManager fm, final IFragment fragment) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        if (frg instanceof Fragment) {
                            fragment.goToFragment(frg, fm, navigation);
                        } else {
                            return;
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
