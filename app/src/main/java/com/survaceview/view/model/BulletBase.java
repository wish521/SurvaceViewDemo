package com.survaceview.view.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.survaceview.utils.ScreenUtil;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BulletBase {

    private boolean isFree = true;
    public static final int TYPE_ROLE = 1;
    public static final int TYPE_PNC = 2;


    public boolean getFree() {
        return isFree;
    }

    public void setIsfree(boolean isfree) {
        this.isFree = isfree;
    }

    int mType = -1;

    public Bitmap mBmp;

    public float mX, mY;

    public int mScreenHeight;
    public int mScreenWindth;


    public int getType() {
        return mType;
    }

    public abstract void draw(Canvas canvas);


    public void init(Context context) {
        mScreenWindth = ScreenUtil.SCREEN_W;
        mScreenHeight = ScreenUtil.SCREEN_H;
        rect = new Rect();
    }

    public void setType(int type) {
        this.mType = type;
    }

    public abstract void move();

    public void destroy() {
        mBmp.recycle();
        mBmp = null;
    }

    public abstract void sendBullet(float x, float y);


    Rect rect;

    public Rect getCollideArea() {
        rect.set((int) mX, (int) mY, (int) mX + mBmp.getWidth(), (int) mY + mBmp.getHeight());
        return rect;
    }
}
