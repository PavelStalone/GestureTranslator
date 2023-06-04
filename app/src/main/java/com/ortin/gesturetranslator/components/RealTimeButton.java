package com.ortin.gesturetranslator.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.ortin.gesturetranslator.R;

public class RealTimeButton extends LottieAnimationView implements View.OnClickListener {
    private boolean state = true;
    private OnChangedStatusListener onChangedStatusListener;

    public RealTimeButton(Context context) {
        this(context, null);
    }

    public RealTimeButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RealTimeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init() {
        this.setBackground(getContext().getDrawable(R.drawable.background_realtime));
        this.setAnimation(R.raw.play_stop);
        this.setRepeatCount(0);
        this.setMinAndMaxProgress(0f,0.1f);
        this.playAnimation();
        this.setSpeed(2.3f);
        this.setOnClickListener(this);
    }

    private void checkState() {
        if (state) {
            this.setMinAndMaxProgress(0.5f, 1f);
            if (onChangedStatusListener != null) onChangedStatusListener.onStart();
        } else {
            this.setMinAndMaxProgress(0f, 0.5f);
            if (onChangedStatusListener != null) onChangedStatusListener.onStop();
        }
        this.playAnimation();
    }

    @Override
    public void onClick(View view) {
        state = !state;
        checkState();
    }

    public void setOnChangedStatusListener(OnChangedStatusListener onChangedStatusListener) {
        this.onChangedStatusListener = onChangedStatusListener;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean status) {
        state = status;
        checkState();
    }
}
