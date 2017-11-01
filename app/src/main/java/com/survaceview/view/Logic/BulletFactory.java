package com.survaceview.view.Logic;

import android.content.Context;

import com.survaceview.view.model.BulletNpc;
import com.survaceview.view.model.BulletRole;

/**
 * Created by Administrator on 2017/10/27.
 */

public class BulletFactory {

    private BulletFactory() {

    }

    static BulletFactory instance;

    static Context mContext;

    public static void propear(Context context) {
        mContext = context;
    }


    public static BulletRole createBulletRole() throws Exception {
        if (mContext != null) {
            BulletRole bullet = new BulletRole(mContext);
            return bullet;
        } else {
            throw new Exception("prepare before use!");
        }
    }

    public static BulletNpc createBulletNpc() throws Exception {
        if (mContext != null) {
            BulletNpc bullet = new BulletNpc(mContext);
            return bullet;
        } else {
            throw new Exception("prepare before use!");
        }
    }


}
