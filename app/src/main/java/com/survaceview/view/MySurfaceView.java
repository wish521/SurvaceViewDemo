package com.survaceview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.survaceview.R;
import com.survaceview.utils.ScreenUtil;
import com.survaceview.view.Logic.BulletFactory;
import com.survaceview.view.Logic.BulletManger;
import com.survaceview.view.Logic.MainPlaneManerger;
import com.survaceview.view.Logic.NpcManerger;
import com.survaceview.view.maps.Map;

/**
 * Created by hongtao on 2017/10/25.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    SurfaceHolder mSurfaceHolder;

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }


    boolean mSurfaceIsCreated = false;
    Map mMap;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceIsCreated = true;
        BulletFactory.propear(getContext());
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ScreenUtil.SCREEN_W = width;
        ScreenUtil.SCREEN_H = height;
        mMap = new Map(width, height);
        mMap.init(getResources(), R.raw.bg);

        MainPlaneManerger.getInstance().init(getResources(), width, height);

        NpcManerger.getInstance().init(getResources());
        gameThread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceIsCreated = false;
    }

    long startTime;
    long endTime;
    long renderTime;
    Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {

                startTime = System.nanoTime();
                Canvas canvas = mSurfaceHolder.lockCanvas();
                try {
                    if (mSurfaceIsCreated) {
                        if (mMap != null) {
                            mMap.draw(canvas);
                        }

                        MainPlaneManerger.getInstance().run(canvas);

                        if (NpcManerger.getInstance() != null) {
                            NpcManerger.getInstance().run(canvas);
                        }

                        BulletManger.getInstance().draw(canvas);

                        if (MainPlaneManerger.getInstance().getIsDie()) {
                            break;
                        }
                    }
                    endTime = System.nanoTime();
                    renderTime = endTime - startTime;

                    if (1000 / 30 - renderTime > 0) {
                        Thread.sleep(1000 / 30 - renderTime);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                MainPlaneManerger.getInstance().move(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

}
