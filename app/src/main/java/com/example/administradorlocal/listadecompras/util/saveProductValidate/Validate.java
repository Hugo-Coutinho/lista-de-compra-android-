package com.example.administradorlocal.listadecompras.util.saveProductValidate;

import android.graphics.Bitmap;

public interface Validate {

    boolean isNamevalid(String name);

    boolean isImageEmpty(Bitmap img);

    String imageNameFailedCheck(String name, Bitmap img);

}
