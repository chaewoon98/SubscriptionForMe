package com.example.subscriptionforme.home;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.subscriptionforme.R;

public class CustomOnTouchListener implements View.OnTouchListener {

    public Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CustomOnTouchListener(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        float curX = motionEvent.getX();
        float curY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                view.setBackgroundColor(context.getColor(R.color.transparentColorInTouch));
                return true;
            }
            case MotionEvent.ACTION_UP: {
                view.setBackgroundColor(context.getColor(R.color.transparentColor));
                return false;
            }
            default:
                return false;
        }
    }
}
