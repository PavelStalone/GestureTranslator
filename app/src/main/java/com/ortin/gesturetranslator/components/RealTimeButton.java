package com.ortin.gesturetranslator.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.ortin.gesturetranslator.R;

public class RealTimeButton extends LottieAnimationView implements View.OnClickListener {
    private boolean play = true;
    private OnChangedStatusListener onChangedStatusListener;

    public interface OnChangedStatusListener{
        void onStart();
        void onStop();
    }

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
    private void init(){
        this.setBackground(getContext().getDrawable(R.drawable.background_realtime));
        this.setAnimation(R.raw.play_stop);
        this.setRepeatCount(0);
        this.setProgress(0f);
        this.setOnClickListener(this);
    }

    public void onStart(){
        play = true;
        this.setMinAndMaxProgress(0.5f, 1f);
        this.playAnimation();
    }

    public void onStop(){
        play = false;
        this.setMinAndMaxProgress(0f, 0.5f);
        this.playAnimation();
    }

    @Override
    public void onClick(View view) {
        if (play) {
            onStop();
            if (onChangedStatusListener != null) onChangedStatusListener.onStop();
        }
        else {
            onStart();
            if (onChangedStatusListener != null) onChangedStatusListener.onStart();
        }
    }

    public void setOnChangedStatusListener(OnChangedStatusListener onChangedStatusListener) {
        this.onChangedStatusListener = onChangedStatusListener;
    }

    public boolean isPlay() {
        return play;
    }
}
