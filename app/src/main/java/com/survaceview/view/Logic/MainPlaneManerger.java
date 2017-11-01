package com.survaceview.view.Logic;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.survaceview.R;
import com.survaceview.view.model.BulletBase;
import com.survaceview.view.model.BulletNpc;
import com.survaceview.view.model.MainPlane;

/**
 * Created by Administrator on 2017/10/31.
 */

public class MainPlaneManerger {
    static MainPlaneManerger instance;

    public static MainPlaneManerger getInstance() {
        if (instance == null) {
            instance = new MainPlaneManerger();
        }
        return instance;
    }

    MainPlane mPlane;

    public void init(Resources res, int width, int height) {
        mPlane = new MainPlane(res, R.raw.plane);
        mPlane.init();
        mPlane.setPostion(width >> 1, height - 50);
    }

    public void run(Canvas canvas) {
        mPlane.draw(canvas);
        collideCheck();
    }

    boolean die;

    public boolean getIsDie() {
        return die;
    }

    public void collideCheck() {
        Rect rectRole = mPlane.getCollideArea();
        for (int i = 0; i < BulletManger.getInstance().mBullets.size(); i++) {
            BulletBase bullet = BulletManger.getInstance().mBullets.get(i);
            if (bullet instanceof BulletNpc) {
                Rect rectBullet = bullet.getCollideArea();
                if (Rect.intersects(rectBullet, rectRole)) {
                    die = true;
                }
            }
        }
    }

    public void move(float rawX, float rawY) {
        mPlane.setPostion(rawX, rawY);
    }
}
