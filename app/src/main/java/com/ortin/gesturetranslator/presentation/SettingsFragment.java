package com.ortin.gesturetranslator.presentation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.SettingsLayoutBinding;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener{
    private SettingsLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SettingsLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.switchCompatTheme.setOnCheckedChangeListener(this);
        binding.switchCompatGpu.setOnCheckedChangeListener(this);
        binding.switchCompatPercents.setOnCheckedChangeListener(this);
//        ТАК ИДЕЯ СКАЗАЛА НУЖНО |
        binding.seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);
        binding.seekBar.getProgressDrawable().setColorFilter(getContext().getColor(R.color.element_text_color_day), PorterDuff.Mode.SRC_ATOP);
        binding.seekBar.getThumb().setColorFilter(getContext().getColor(R.color.element_text_color_day), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        binding.seekBarText.setText(String.valueOf(seekBar.getProgress()));
    }
}
