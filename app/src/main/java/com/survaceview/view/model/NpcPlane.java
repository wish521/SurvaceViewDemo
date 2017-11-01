package com.survaceview.view.model;

import android.content.res.Resources;
import android.graphics.Rect;

import com.survaceview.utils.ScreenUtil;
import com.survaceview.view.Logic.BulletFactory;

import java.util.Random;
import java.util.Timer;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NpcPlane extends PlaneBase {

    public NpcPlane(Resources res, int id) {
        super(res, id);
    }

    public BulletBase createBullet() {
        BulletNpc bullet = null;
        try {
            bullet = BulletFactory.createBulletNpc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bullet;
    }

    @Override
    public void init() {
        isInit = true;
        timer = new Timer();
        rect = new Rect();
    }

    public void sendBullet() {
        createBullet().sendBullet(posX, posY);
    }

    @Override
    public void startShot() {

    }

    public void move() {
        posY += 10;
        setPostion(posX, posY);
        if (posY > ScreenUtil.SCREEN_H) {
            Random r = new Random();
            int x = r.nextInt(ScreenUtil.SCREEN_W);
            if (x + mBmMapWidth > ScreenUtil.SCREEN_W) {
                x -= mBmMapWidth;
            }
            setPostion(x, 0);
            // NpcManerger.getInstance().remove(this);
        }
    }

    public void realease() {
        mBitmap.recycle();
        mBitmap = null;
    }

}
