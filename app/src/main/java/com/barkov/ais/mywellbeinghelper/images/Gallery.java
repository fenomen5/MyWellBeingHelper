package com.barkov.ais.mywellbeinghelper.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class Gallery {

    ImageSwitcher window;
    int currentSlideNumber;

    ArrayList<Bitmap> images;

    int status;
    static final int RUN_STATUS = 1;
    static final int PAUSED_STATUS = 2;
    Context ctx;

    public Gallery(Context ctx, ImageSwitcher window, ArrayList<Bitmap> img, int currentSlideNumber) {
        this.window = window;
        this.currentSlideNumber = currentSlideNumber;
        images = img;
        this.ctx = ctx;
        initImageSwitcher();
    }

    protected void initImageSwitcher() {

        window.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgVw= new ImageView(ctx);
                imgVw.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ViewGroup.LayoutParams lp =  new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                );
                //lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                imgVw.setLayoutParams(lp);
                imgVw.setImageBitmap(images.get(currentSlideNumber));
                return imgVw;
            }
        });
        window.setInAnimation(ctx, android.R.anim.slide_in_left);
        window.setOutAnimation(ctx, android.R.anim.slide_out_right);
    }
    public void showSlides()
    {
        status = RUN_STATUS;
        currentSlideNumber = 0;
    }

    public void nextSlide()
    {
        if (this.status == RUN_STATUS) {

            currentSlideNumber++;

            if (currentSlideNumber == this.images.size()) {
                currentSlideNumber = 0;
            }
            Log.d("dbg", images.get(currentSlideNumber).toString());
            Drawable d = new BitmapDrawable(
                    ctx.getResources(),
                    images.get(currentSlideNumber)
            );
            window.setImageDrawable(d);
            //this.btnsUpdate(currentSlideNumber);
        }

    }

    public void setSlide(int slideNumber)
    {
        currentSlideNumber = slideNumber;
        Drawable d = new BitmapDrawable(
                ctx.getResources(),
                images.get(currentSlideNumber)
        );

        window.setImageDrawable(d);
        //this.btnsUpdate(currentSlideNumber);
    }

    public void pause()
    {
        this.status = PAUSED_STATUS;
    }

    public void unpause()
    {
        this.status = RUN_STATUS;
    }

    /*protected void btnsUpdate(int number)
    {
        for (int i = 0; i < btns.length; i++) {
            btns[i].setBackgroundColor(ctx.getResources().getColor(R.color.colorRed));
            if (i == number) {
                btns[i].setBackgroundColor(ctx.getResources().getColor(R.color.colorYellow));
            }
        }
    }*/
}
