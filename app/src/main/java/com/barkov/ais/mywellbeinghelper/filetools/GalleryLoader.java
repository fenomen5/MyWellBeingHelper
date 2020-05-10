package com.barkov.ais.mywellbeinghelper.filetools;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryLoader {


    ArrayList<Bitmap> images;

    public GalleryLoader(String path, Context ctx) {

        Log.d("dbg", "path"+path);
        AssetManager assetMgr = ctx.getResources().getAssets();
        String[] files = new String[]{};
        try {
            files = assetMgr.list(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        images = new ArrayList<Bitmap>();
        for (String file: files
             ) {
            try {
                //File f=new File(path, file);
                //Log.d("dbg", "files"+f.toString());
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeStream(assetMgr.open(path+"/" +file));
                Log.d("dbg", "bitmap"+bitmap.toString());
                images.add(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Bitmap> getImages() {
        Log.d("dbg", "loader images"+images.size());
        return images;
    }
}
