package com.example.administradorlocal.listadecompras.util.toolbar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administradorlocal.listadecompras.R;

public class CreateProductToolbar extends BaseToolbar {

    public CreateProductToolbar(View view, Context ctx) {
    super(view,"",ctx);
    }

    public void configureTitle() {
        super.toolbar.setTitle(super.ctx.getString(R.string.home_toolbar_empty));
    }

    @Override
    public Toolbar getToolbar() {
        return super.getToolbar();
    }
}
