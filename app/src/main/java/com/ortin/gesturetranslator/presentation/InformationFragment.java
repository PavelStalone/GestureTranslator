package com.ortin.gesturetranslator.presentation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.databinding.InformationFragmentBinding;

public class InformationFragment extends Fragment {

    InformationFragmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = InformationFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void goToWebApplication(){
        goToUrl("будет ссылка");
    }

    private void goToAppGithub(){
        goToUrl("https://github.com/PavelStalone/GestureTranslator");
    }
    private void goToPavelStaloneGithub(){
        goToUrl("https://github.com/PavelStalone");
    }
    private void goToPavelStaloneContacts(){}

    private void goToGlebixGithub(){
        goToUrl("https://github.com/PoroshinGA");
    }
    private void goToGlebixContacts(){}

    private void goToQondeeterGithub(){
        goToUrl("https://github.com/kond1ter");
    }
    private void goToQondeeterContacts(){}

    private void goToSovaGithub(){
        goToUrl("https://github.com/N1kySSS");
    }
    private void goToSovaContacts(){}

    private void goToGlazeezalgGithub(){
        goToUrl("https://github.com/glazeezalg");
    }
    private void goToGlazeezalgContacts(){}

    private void goToSeverGithub(){
        goToUrl("https://github.com/jacksever");
    }
    private void goToSeverContacts(){}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
