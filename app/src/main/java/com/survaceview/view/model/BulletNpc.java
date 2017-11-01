package com.survaceview.view.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.survaceview.R;
import com.survaceview.view.Logic.BulletManger;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BulletNpc extends BulletBase {

    public BulletNpc(Context context) {
        init(context);
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null && mBmp != null) {
            canvas.drawBitmap(mBmp, mX - mBmp.getWidth() / 2, mY + mBmp.getHeight(), null);
            move();
        }
    }

    @Override
    public void init(Context context) {
        setType(TYPE_PNC);
        Bitmap tempBmp = BitmapFactory.decodeStream(context.getResources().openRawResource(R.raw.laser));
        Matrix matrix = new Matrix();
        matrix.postRotate(180, tempBmp.getWidth() >> 1, tempBmp.getHeight() >> 1);
        mBmp = Bitmap.createBitmap(tempBmp, 0, 0, tempBmp.getWidth(), tempBmp.getHeight(), matrix, true);
        tempBmp.recycle();
        tempBmp = null;
        super.init(context);
    }

    @Override
    public void move() {
        mY += 30;
        if (mY >= mScreenHeight) {
            BulletManger.getInstance().removeBullet(this);
        }
    }

    @Override
    public void sendBullet(float x, float y) {
        mX = x;
        mY = y;
        BulletManger.getInstance().add(this);
    }


}
