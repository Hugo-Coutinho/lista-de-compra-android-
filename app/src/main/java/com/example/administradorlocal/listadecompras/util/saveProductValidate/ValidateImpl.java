package com.example.administradorlocal.listadecompras.util.saveProductValidate;

import android.graphics.Bitmap;

import com.example.administradorlocal.listadecompras.persistence.entity.Product;
import com.example.administradorlocal.listadecompras.feature.main.main_container;

public class ValidateImpl implements Validate {


    private Product p;

    public ValidateImpl() {

    }

    private boolean isNameOk(String name) {

        p = main_container.db.productDao().fetchProductByName(name);

        if (p instanceof Product) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean isNamevalid(String name) {
        if (isNameOk(name)) {
            return name != null && name != "" && !name.isEmpty();
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
        } else if (p instanceof Product) {
            MESSAGE = "produto com esse nome ja existe";
        } else {
            MESSAGE = "para salvar digite nome do produto!";
        }
        return MESSAGE;
    }
}
