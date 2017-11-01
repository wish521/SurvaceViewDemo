package com.survaceview.view.model;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.survaceview.view.Logic.BulletFactory;
import com.survaceview.view.Logic.BulletManger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/10/26.
 */

public class MainPlane extends PlaneBase {


    public MainPlane(Resources res, int id) {
        super(res, id);
        mBmMapHeight = mBitmap.getHeight();
        mBmMapWidth = mBitmap.getWidth();
    }

    public void LoadPic(Resources res, int id) {
        mBitmap = BitmapFactory.decodeStream(res.openRawResource(id));
    }

    public void init() {
        isInit = true;
        timer = new Timer();
        rect = new Rect();
        startShot();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, posX, posY, null);
    }


    public boolean isInited() {
        return isInit;
    }


    public void setPostion(float x, float y) {
        posX = x;
        posY = y;
    }


    public void sendBullet() {
        createBullet().sendBullet(posX + mBmMapWidth / 2, posY - mBitmap.getHeight());
        createBullet().sendBullet(posX + mBmMapWidth / 2 - 40, posY - mBitmap.getHeight());
        createBullet().sendBullet(posX + mBmMapWidth / 2 + 40, posY - mBitmap.getHeight());
        createBullet().sendBullet(posX + mBmMapWidth / 2 + 80, posY - mBitmap.getHeight());
        createBullet().sendBullet(posX + mBmMapWidth / 2 - 80, posY - mBitmap.getHeight());
    }

    @Override
    public void startShot() {
        {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendBullet();
                }
            }, 100, 200);
        }
    }

    public BulletBase createBullet() {
        BulletBase bulletBase = getBullet();
        if (bulletBase != null) {
            return bulletBase;
        } else {
            BulletRole bullet = null;
            try {
                bullet = BulletFactory.createBulletRole();
                bullet.setIsfree(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bullet;
        }
    }

    public BulletBase getBullet() {
        int size = BulletManger.getInstance().mBullets.size();
        for (int i = 0; i < size; i++) {
            if (BulletManger.getInstance().mBullets.get(i).getFree()) {
                BulletManger.getInstance().mBullets.get(i).setIsfree(false);
                return BulletManger.getInstance().mBullets.get(i);
            }
        }
        return null;
    }


    public Rect getCollideArea() {
        rect.set((int) posX, (int) posY, (int) posX + mBitmap.getWidth(), (int) posY + mBitmap.getHeight());
        return rect;
    }
}
