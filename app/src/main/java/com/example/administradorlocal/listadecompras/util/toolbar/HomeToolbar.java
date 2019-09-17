package com.example.administradorlocal.listadecompras.util.toolbar;

import android.content.Context;
import android.view.View;
import com.example.administradorlocal.listadecompras.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeToolbar extends BaseToolbar {

    private Boolean productlistIsEmpty;

    public HomeToolbar(View view, Boolean productlistIsEmpty,  Context ctx) {
        super(view,"",ctx);
        this.productlistIsEmpty = productlistIsEmpty;
    }

    public void configureTitle() {
        if (this.productlistIsEmpty == true) {
            super.title = super.ctx.getResources().getString(R.string.home_toolbar_empty);
        } else  {
            super.title = this.getTitleFormatted();
        }
        super.toolbar.setTitle(super.title);
    }

    private String getTitleFormatted() {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = sdf.format(new Date());
        return super.ctx.getResources().getString(R.string.home_toolbar_list) + " "+ dateFormatted;
    }
}
