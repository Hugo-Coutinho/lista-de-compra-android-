package com.example.administradorlocal.listadecompras.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;

public class ImageManipulateImpl implements ImageManipulate {


    public ImageManipulateImpl() {
    }


    private File getFile(Context ctx, String name) {
        ContextWrapper wrapper = new ContextWrapper(ctx.getApplicationContext());
        File file = wrapper.getDir("Images", MODE_PRIVATE);
        return new File(file, name + ".jpg");
    }


    @Override
    public Uri getImagebyUri(Context ctx, String name) {

        return Uri.parse(name);
    }

    @Override
    public String getImagePath(Context ctx, String name) {
        File file = getFile(ctx, name);
        return file.getAbsolutePath();
    }

    @Override
    public void setImage(Context ctx, String name, Bitmap img) {
        File file = getFile(ctx, name);
        try {
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();

        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
    }
}
