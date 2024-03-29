package com.ortin.gesturetranslator.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.InformationFragmentBinding;
import com.ortin.gesturetranslator.decorators.SpacesItemDecoration;
import com.ortin.gesturetranslator.app.models.DeveloperCardInfo;
import java.util.ArrayList;
import java.util.List;

public class InformationFragment extends Fragment {

    private InformationFragmentBinding binding;

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
        initListeners();
    }

    private void init() {
        List<DeveloperCardInfo> cards = new ArrayList<>();
        cards.add(new DeveloperCardInfo("PavelStalone", "Android developer and ML engineer", R.drawable.pavel_stalone, "https://github.com/PavelStalone", "https://vk.com/pasha_just_pasha"));
        cards.add(new DeveloperCardInfo("Glebix", "ML engineer, Android and Web developer", R.drawable.glebix, "https://github.com/PoroshinGA", "https://t.me/PoroshinG"));
        cards.add(new DeveloperCardInfo("qondeeter", "UI/UX - designer and data engineer", R.drawable.qondeeter, "https://github.com/kond1ter", "https://t.me/qondeeter"));
        cards.add(new DeveloperCardInfo("Sova", "UI/UX - writer, Android developer and data engineer", R.drawable.sova, "https://github.com/N1kySSS", "https://vk.com/sova___666"));
        cards.add(new DeveloperCardInfo("Sever", "Teach lead and mentor", R.drawable.sever, "https://github.com/jacksever", "https://t.me/jasever"));
        cards.add(new DeveloperCardInfo("LittlePony00", "Android developer", R.drawable.little_pony00, "https://github.com/LittlePony00", "https://t.me/littlepony01"));
        cards.add(new DeveloperCardInfo("AccessAndrei", "ML engineer", R.drawable.access_andrei, "https://github.com/AccessAndrei", "https://t.me/AxAndrei"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        binding.developCardsRV.setLayoutManager(linearLayoutManager);
        CardsAdapter adapter = new CardsAdapter(cards, requireContext());
        binding.developCardsRV.setAdapter(adapter);
        binding.developCardsRV.addItemDecoration(new SpacesItemDecoration(75));
    }

    private void initListeners() {
        View.OnClickListener clickListener = viewForUrl -> {
            String url = "https://github.com/PavelStalone/GestureTranslator";

            if (viewForUrl == binding.appGithubButton) {
                url = "https://github.com/PavelStalone/GestureTranslator";
            } else if (viewForUrl == binding.webAppButton) {
                url = "https://gesture-recognition-web-6jdc-git-master-poroshinga.vercel.app/";
            }

            goToUrl(url, requireContext());
        };
        binding.appGithubButton.setOnClickListener(clickListener);
        binding.webAppButton.setOnClickListener(clickListener);
    }

    private boolean goToUrl(@NonNull String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                return true;
            }
        } catch (Exception ignore) {
            Toast.makeText(context, "Error attempting launchURL", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
