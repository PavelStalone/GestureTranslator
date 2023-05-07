package com.ortin.gesturetranslator.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.ortin.gesturetranslator.R;

public class RealTimeButton extends LottieAnimationView implements View.OnClickListener {
    private final Context context;
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
        this.context = context;
        init();
    }

    private void init(){
        this.setAnimation(R.raw.stop_start);
        this.setRepeatCount(0);
        this.setOnClickListener(this);
    }

    public void onStart(){
        play = true;
        this.setAnimation(R.raw.start_stop);
        this.playAnimation();
    }

    public void onStop(){
        play = false;
        this.setAnimation(R.raw.stop_start);
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
