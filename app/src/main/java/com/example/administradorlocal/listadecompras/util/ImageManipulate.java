package com.example.administradorlocal.listadecompras.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

public interface ImageManipulate {

    Uri getImagebyUri(Context ctx, String name);

    String getImagePath(Context ctx, String name);

    void setImage(Context ctx, String name, Bitmap img);

}
