package com.ortin.gesturetranslator.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.databinding.InformationLayoutBinding;

public class InformationFragment extends Fragment {

    InformationLayoutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = InformationLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
