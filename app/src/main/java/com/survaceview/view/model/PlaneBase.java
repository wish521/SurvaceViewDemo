package com.survaceview.view.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Timer;

/**
 * Created by Administrator on 2017/10/31.
 */

public abstract class PlaneBase {

    public float posY = 0;
    public float posX = 0;

    public int mBmMapHeight;
    public int mBmMapWidth;

    public PlaneBase(Resources res, int id) {
        LoadPic(res, id);
    }


    public void LoadPic(Resources res, int id) {
        mBitmap = BitmapFactory.decodeStream(res.openRawResource(id));
        mBmMapWidth = mBitmap.getWidth();
    }

    public Bitmap mBitmap;
    public int state = 1;

    public abstract void init();

    public void draw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, posX, posY, null);
    }

    public boolean isInit = false;

    public boolean isInited() {
        return isInit;
    }


    public void setPostion(float x, float y) {
        posX = x;
        posY = y;
    }


    public Timer timer;

    public void sendBullet() {
        createBullet().sendBullet(posX, posY - mBmMapHeight);
    }

    public abstract void startShot();

    public abstract BulletBase createBullet();

    public Rect rect;

    public Rect getCollideArea() {
        rect.set((int) posX, (int) posY, (int) posX + mBitmap.getWidth(), (int) posY + mBitmap.getHeight());
        return rect;
    }
}
