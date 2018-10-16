package com.example.administradorlocal.listadecompras.util;

import android.graphics.Bitmap;

import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.views.shopList;

public class ValidateImpl implements Validate {


    private Product p;

    public ValidateImpl() {

    }

    private boolean isNameOk(String name) {
        Product p;

        p = shopList.db.productDao().fetchProductByName(name);

/*        if (p != null) {
            if (name != p.getName()) {
                return name.length() <= 7;
            } else {
                return false;
            }
        } else {
            return false;
        }*/

        return p == null ||
                p != null && p.getName().length() <= 7 && p.getName() != name;
    }


    @Override
    public boolean isNamevalid(String name) {
        if (isNameOk(name)) {
            return name != null && name != "" && !name.isEmpty() && name.length() <= 7;
        }
        return false;
    }

    @Override
    public boolean isImageEmpty(Bitmap img) {
        return img == null;
    }

    @Override
    public String imageNameFailedCheck(String name, Bitmap img) {
        boolean checkName = false;
        boolean checkImage = false;
        String MESSAGE = "ocorreu um erro, verifique as informações por favor!";

        checkImage = isImageEmpty(img);
        checkName = isNameOk(name);

        if (checkImage) {
            MESSAGE = "para salvar capture a foto por favor!";
        } else if (name.length() > 7) {
            MESSAGE = "nome do produto tem que ser menor que 8";

        } else if (p != null) {
            MESSAGE = "produto com esse nome ja existe";
        } else {
            MESSAGE = "para salvar digite nome do produto!";
        }
        return MESSAGE;
    }
}
