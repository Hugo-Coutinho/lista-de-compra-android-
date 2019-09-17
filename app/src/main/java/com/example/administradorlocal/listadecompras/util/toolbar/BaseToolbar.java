package com.example.administradorlocal.listadecompras.util.toolbar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.administradorlocal.listadecompras.R;

public abstract class BaseToolbar {

    protected View view;
    protected String title;
    protected Toolbar toolbar;
    protected Context ctx;

    protected BaseToolbar(View view, String title, Context ctx) {
        this.view = view;
        this.title = title;
        this.ctx = ctx;
        this.toolbar = this.view.findViewById(R.id.toolbar_common);
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }
}
