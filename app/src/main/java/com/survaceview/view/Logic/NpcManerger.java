package com.survaceview.view.Logic;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.survaceview.R;
import com.survaceview.utils.ScreenUtil;
import com.survaceview.view.model.BulletBase;
import com.survaceview.view.model.BulletRole;
import com.survaceview.view.model.NpcPlane;

import java.util.Random;
import java.util.Vector;

/**
 * Created by hongtao on 2017/10/30.
 */

public class NpcManerger {


    static NpcManerger instance;

    static public NpcManerger getInstance() {
        if (instance == null) {
            instance = new NpcManerger();
        }
        return instance;
    }

    static Vector<NpcPlane> mNpcs;

    public static void init(Resources resources) {
        NpcPlane plane;
        mNpcs = new Vector<>(20);

        for (int i = 0; i < 15; i++) {
            plane = new NpcPlane(resources, R.raw.plane2);
            plane.init();
            Random r = new Random();
            int x = r.nextInt(ScreenUtil.SCREEN_W);
            if (x + plane.mBmMapWidth > ScreenUtil.SCREEN_W) {
                x -= plane.mBmMapWidth;
            }
            int y = r.nextInt(1500);
            plane.setPostion(x, -y);
            mNpcs.add(plane);
        }
    }

    public void run(Canvas c) {
        draw(c);
        logic();
    }

    public void draw(Canvas c) {
        if (mNpcs == null || mNpcs.size() == 0 || c == null) {
            return;
        }
        for (int i = 0; i < mNpcs.size(); i++) {
            NpcPlane npcPlane = mNpcs.get(i);
            npcPlane.draw(c);
        }
    }

    public void logic() {

        for (int i = 0; i < mNpcs.size(); i++) {
            NpcPlane plane = mNpcs.get(i);
            plane.move();
        }
        collideCheck();
    }

    Random r = new Random();

    public void collideCheck() {
        for (int p = 0; p < mNpcs.size(); p++) {
            Rect rectNpc = mNpcs.get(p).getCollideArea();
            for (int i = 0; i < BulletManger.getInstance().mBullets.size(); i++) {
                BulletBase bullet = BulletManger.getInstance().mBullets.get(i);
                if (bullet instanceof BulletRole) {
                    Rect rectBullet = bullet.getCollideArea();
                    if (Rect.intersects(rectBullet, rectNpc)) {
                        int x = r.nextInt(ScreenUtil.SCREEN_W);
                        mNpcs.get(p).setPostion(x, 0);
                        bullet.setIsfree(true);
                        bullet.mY = ScreenUtil.SCREEN_H;
                    }
                }
            }
        }
    }

    public void remove(NpcPlane npcPlane) {
        mNpcs.remove(npcPlane);
        npcPlane.realease();
    }

    public void realease() {

    }

}
