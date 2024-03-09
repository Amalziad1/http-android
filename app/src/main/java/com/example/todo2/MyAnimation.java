package com.example.todo2;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyAnimation extends Animation {
    private View view;
    private float cx, cy;       // center x,y position of circular path
    private float prevX, prevY; // previous x,y position of image during animation
    private float r;            // radius of circle
    private float prevDx, prevDy;// previous displacement
    private int sun_moon;
    public MyAnimation(View view, float r,int sun_moon){
        this.view = view;
        this.r = r;
        this.sun_moon=sun_moon;
        this.setRepeatCount(Animation.INFINITE);
        this.setStartOffset(100);//
    }
    @Override
    public boolean willChangeBounds() {
        return true;//indicates that the animation will change the bounds of the view
    }
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        // calculate position of image center
        int cxImage = width / 2;
        int cyImage = height / 2;
        cx = view.getLeft() + cxImage;
        cy = view.getTop() + cyImage;

        // set previous position to center
        prevX = cx;
        prevY = cy;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if(interpolatedTime == 0){
            t.getMatrix().setTranslate(prevDx, prevDy);
            return;
        }
        float angleDeg=0;
        if (sun_moon==1){
            angleDeg = (interpolatedTime * 360f + 90) % 360;
        }else{
            angleDeg = (interpolatedTime * 360f - 90) % 360;
        }
        float angleRad = (float) Math.toRadians(angleDeg);

        // r = radius, cx and cy = center point, a = angle (radians)
        float x = (float) (cx + r * Math.cos(angleRad));
        float y = (float) (cy + r * Math.sin(angleRad));


        float dx = prevX - x;
        float dy = prevY - y;
        view.setTranslationX(dx);
        view.setTranslationY(dy);

    }
}

