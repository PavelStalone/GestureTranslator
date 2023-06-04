package com.ortin.gesturetranslator.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.InformationFragmentBinding;
import com.ortin.gesturetranslator.decorators.SpacesItemDecoration;
import com.ortin.gesturetranslator.models.DeveloperCard;

import java.util.ArrayList;
import java.util.List;

public class InformationFragment extends Fragment {

    InformationFragmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = InformationFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        List<DeveloperCard> cards = new ArrayList<>();
        cards.add(new DeveloperCard("PavelStalone", "Team lead, Android developer and ML engineer", R.drawable.pavel_stalone, "https://github.com/PavelStalone", ""));
        cards.add(new DeveloperCard("Glebix", "ML engineer, Android and Web developer", R.drawable.glebix, "https://github.com/PoroshinGA", ""));
        cards.add(new DeveloperCard("qondeeter", "UI/UX - designer and data engineer", R.drawable.qondeeter, "https://github.com/kond1ter", ""));
        cards.add(new DeveloperCard("Sova", "UI/UX - writer and data engineer", R.drawable.sova, "https://github.com/N1kySSS", ""));
        cards.add(new DeveloperCard("Glazeezalg", "Analyst", R.drawable.glazeezalg, "https://github.com/glazeezalg", ""));
        cards.add(new DeveloperCard("Sever", "Teach lead and mentor", R.drawable.sever, "https://github.com/jacksever", ""));

        CardsAdapter adapter = new CardsAdapter(cards, requireContext());
        binding.developCardsRV.setAdapter(adapter);
        binding.developCardsRV.addItemDecoration(new SpacesItemDecoration(75));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
