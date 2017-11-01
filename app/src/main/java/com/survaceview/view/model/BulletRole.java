package com.survaceview.view.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.survaceview.R;
import com.survaceview.view.Logic.BulletManger;

/**
 * Created by hongtao on 2017/10/27.
 */

public class BulletRole extends BulletBase {


    public BulletRole(Context context) {
        init(context);
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null && mBmp != null && !getFree()) {
            move();
            canvas.drawBitmap(mBmp, mX - mBmp.getWidth() / 2, mY, null);
        }
    }

    @Override
    public void init(Context context) {
        setType(TYPE_ROLE);
        mBmp = BitmapFactory.decodeStream(context.getResources().openRawResource(R.raw.laser));
        super.init(context);
    }

    @Override
    public void move() {
        mY -= 30;
        if (mY <= -mBmp.getHeight()) {
            setIsfree(true);
            mY = mScreenHeight;
        }
    }

    public void sendBullet(float x, float y) {
        mX = x;
        mY = y;
        BulletManger.getInstance().add(this);
    }

}
