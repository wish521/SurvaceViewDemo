package com.survaceview.view.maps;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by hongtao on 2017/10/26.
 */

public class Map {
    int mScreenHeight;
    int mScreenWidth;
    int posY = 0;

    int mBmMapHeight;

    public Map(int ScreenWidth, int ScreenHeight) {
        mScreenHeight = ScreenHeight;
        mScreenWidth = ScreenWidth;

    }

    Bitmap bmMap;

    public void init(Resources res, int id) {
        bmMap = BitmapFactory.decodeStream(res.openRawResource(id));
        mBmMapHeight = bmMap.getHeight();
        posY = -(mBmMapHeight - mScreenHeight);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bmMap, 0f, posY, null);
        // posY += 2;
        if (posY == 0) {
            posY = -(mBmMapHeight - mScreenHeight);
        }
    }
}
