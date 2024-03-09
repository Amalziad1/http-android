package com.example.todo2;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private TransitionDrawable transitionDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        ImageView sun = findViewById(R.id.sun);
        ImageView moon = findViewById(R.id.moon);
        ImageView cloud1 = findViewById(R.id.cloud1);
        ImageView cloud2 = findViewById(R.id.cloud2);
        //set animation for cloud1 and make it infinitely
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.cloud1);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                // restart the animation when it completes
                cloud1.startAnimation(animation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        cloud1.startAnimation(animation);
        //set animation for cloud2 and make it infinitely
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.cloud2);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation2) {}
            @Override
            public void onAnimationEnd(Animation animation2) {
                //restart the animation when it completes
                cloud2.startAnimation(animation2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        cloud2.startAnimation(animation2);
        //set animation to the moon
        Animation anim2 = new MyAnimation(moon,1500,2);//1500
        anim2.setDuration(20000);//20 seconds to complete one circle
        moon.startAnimation(anim2);
        //set animation to the sun
        Animation anim1 = new MyAnimation(sun,1500,1);//1500
        anim1.setDuration(20000);//20sec
        sun.startAnimation(anim1);
        //set transition for the main scene between the two images
        transitionDrawable = (TransitionDrawable) imageView.getDrawable();
        startTransition();
    }
    private void startTransition() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                transitionDrawable.reverseTransition(8000); // reverse transition duration
                                                                    //made it 8 seconds according to what I saw better
                handler.postDelayed(this, 10000); //total duration for both transitions 10seconds for each photo
            }
        }, 1500); // delay before starting the transition, made it 1.5 seconds according to other animation's delay
                            //because I couldn't avoid this delay
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
