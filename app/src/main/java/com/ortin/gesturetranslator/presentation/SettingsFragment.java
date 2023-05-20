package com.ortin.gesturetranslator.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.databinding.GestureListLayoutBinding;
import com.ortin.gesturetranslator.databinding.SettingsLayoutBinding;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private SettingsLayoutBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwitchCompat switchCompat = binding.switchCompat;
        switchCompat.setOnCheckedChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SettingsLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        ТУТ ВРОДЕ КАК ПЕРЕКЛЮЧЕНИЕ ТЕМЫ
    }
}
