package com.ortin.gesturetranslator.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.SettingsLayoutBinding;
import com.ortin.gesturetranslator.domain.usecases.SaveLoadSettingsUseCase;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {
    private SettingsLayoutBinding binding;

    @Inject
    SaveLoadSettingsUseCase saveLoadSettingsUseCase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SettingsLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        if (saveLoadSettingsUseCase.getTheme()){
            binding.switchCompatTheme.setChecked(true);
            binding.themeSettingsInfo.setText(R.string.settings_layout_switch_theme_to_day);
        } else {
            binding.switchCompatTheme.setChecked(false);
            binding.themeSettingsInfo.setText(R.string.settings_layout_switch_theme_to_night);
        }
        binding.switchCompatGpu.setChecked(saveLoadSettingsUseCase.getGpu());
        binding.switchCompatPercents.setChecked(saveLoadSettingsUseCase.getPercent());

        ArrayAdapter adapter = (ArrayAdapter) binding.spinner.getAdapter();
        int position = adapter.getPosition(String.valueOf(saveLoadSettingsUseCase.getSpeedFrameDetection()));
        binding.spinner.setSelection(position);

        initListeners();
    }

    private void initListeners() {
        binding.switchCompatTheme.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.themeSettingsInfo.setText(R.string.settings_layout_switch_theme_to_day);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.themeSettingsInfo.setText(R.string.settings_layout_switch_theme_to_night);
            }

            saveLoadSettingsUseCase.setTheme(b);
        }));

        binding.switchCompatGpu.setOnCheckedChangeListener(((compoundButton, b) -> {
            saveLoadSettingsUseCase.setGpu(b);
        }));

        binding.switchCompatPercents.setOnCheckedChangeListener(((compoundButton, b) -> {
            saveLoadSettingsUseCase.setPercent(b);
        }));

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saveLoadSettingsUseCase.setSpeedFrameDetection(Integer.parseInt(adapterView.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
