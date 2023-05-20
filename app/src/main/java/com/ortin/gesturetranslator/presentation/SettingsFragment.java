package com.ortin.gesturetranslator.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.components.OnChangedStatusListener;
import com.ortin.gesturetranslator.databinding.SettingsFrameBinding;

public class SettingsFragment extends Fragment {
    private SettingsFrameBinding binding;

    private static final String TAG = "SettingsFragment";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SettingsFrameBinding.inflate(inflater, container, false);
        Log.e(TAG, "Create");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
    }


    private void initListeners() {
        binding.toogleTheme.setOnChangedStatusListener(new OnChangedStatusListener() {
            @Override
            public void onStart() {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            @Override
            public void onStop() {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
