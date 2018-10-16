package com.example.administradorlocal.listadecompras.util;

import android.graphics.Bitmap;

public interface Validate {

    boolean isNamevalid(String name);

    boolean isImageEmpty(Bitmap img);

    String imageNameFailedCheck(String name, Bitmap img);

}
