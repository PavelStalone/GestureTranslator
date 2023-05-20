package com.ortin.gesturetranslator.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.ortin.gesturetranslator.R;

public class ToggleTheme extends LottieAnimationView implements View.OnClickListener {
    private static boolean status = false;
    private OnChangedStatusListener onChangedStatusListener;

    public ToggleTheme(Context context) {
        this(context, null);
    }

    public ToggleTheme(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleTheme(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setAnimation(R.raw.toogle_theme_mode);
        this.setRepeatCount(0);
        Log.e("Toogle", "init: " + status);
        setStatus(status);
        this.setSpeed(2f);
        this.setOnClickListener(this);
    }

    public void setStatus(boolean status) {
        ToggleTheme.status = status;
        Log.e("Toogle", "status: " + status);
        this.setProgress(status ? 0.5f : 0f);
        this.pauseAnimation();
    }

    public void onStart() {
        this.setMinAndMaxProgress(0.5f, 1f);
        this.playAnimation();
    }

    public void onStop() {
        this.setMinAndMaxProgress(0f, 0.5f);
        this.playAnimation();
    }

    @Override
    public void onClick(View view) {
        status = !status;
        if (status) {
            onStop();
            if (onChangedStatusListener != null) onChangedStatusListener.onStop();
        } else {
            onStart();
            if (onChangedStatusListener != null) onChangedStatusListener.onStart();
        }
    }

    public void setOnChangedStatusListener(OnChangedStatusListener onChangedStatusListener) {
        this.onChangedStatusListener = onChangedStatusListener;
    }

    public boolean isStatus() {
        return status;
    }
}
