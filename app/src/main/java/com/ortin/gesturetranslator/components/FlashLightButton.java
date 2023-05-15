package com.ortin.gesturetranslator.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ortin.gesturetranslator.databinding.FlashlightBinding;

public class FlashLightButton extends LinearLayout {

    FlashlightBinding binding;

    private boolean status = false;
    private OnChangedStatusListener onChangedStatusListener = null;

    public FlashLightButton(Context context) {
        this(context, null);
    }

    public FlashLightButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlashLightButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FlashLightButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        binding = FlashlightBinding.inflate(LayoutInflater.from(context), this, true);
        init();
    }

    private void init() {
        binding.flashLight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                status = !status;
                if (status) {
                    binding.flashLight.setMinAndMaxProgress(0f, 0.5f);
                    binding.flashLight.playAnimation();
                    if (onChangedStatusListener != null) onChangedStatusListener.onStart();
                } else {
                    binding.flashLight.setMinAndMaxProgress(0.5f, 1f);
                    binding.flashLight.playAnimation();
                    if (onChangedStatusListener != null) onChangedStatusListener.onStop();
                }
            }
        });
    }

    public boolean getStatus(){
        return status;
    }

    public void setOnChangedStatusListener(OnChangedStatusListener onChangedStatusListener){
        this.onChangedStatusListener = onChangedStatusListener;
    }
}
