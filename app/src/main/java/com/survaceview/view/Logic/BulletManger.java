package com.survaceview.view.Logic;

import android.graphics.Canvas;

import com.survaceview.view.model.BulletBase;

import java.util.Vector;

/**
 * Created by Administrator on 2017/10/27.
 */

public class BulletManger {

    static private BulletManger instance;

    static public BulletManger getInstance() {
        if (instance == null) {
            instance = new BulletManger();
        }
        return instance;
    }

    public Vector<BulletBase> mBullets;

    private BulletManger() {
        mBullets = new Vector<>();
    }

    public void add(BulletBase bullet) {
        if (mBullets != null && !mBullets.contains(bullet)) {
            mBullets.add(bullet);
        }
    }

    public void removeBullet(BulletBase bullet) {
        if (mBullets != null && mBullets.contains(bullet)) {
            mBullets.remove(bullet);
            bullet.destroy();
        }
    }

    public void draw(Canvas canvas) {
        synchronized (mBullets) {
            if (mBullets != null && mBullets.size() > 0) {
                for (int i = 0; i < mBullets.size(); i++) {
                    BulletBase blt = mBullets.get(i);
                    blt.draw(canvas);
                }
            }
        }
    }


}
