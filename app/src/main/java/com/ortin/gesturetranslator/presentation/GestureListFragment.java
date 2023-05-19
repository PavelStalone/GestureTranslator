package com.ortin.gesturetranslator.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.databinding.GestureListLayoutBinding;
import com.ortin.gesturetranslator.databinding.InformationLayoutBinding;

public class GestureListFragment extends Fragment {
    GestureListLayoutBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = GestureListLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
