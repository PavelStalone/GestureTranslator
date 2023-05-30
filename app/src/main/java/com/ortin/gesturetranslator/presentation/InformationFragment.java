package com.ortin.gesturetranslator.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.ortin.gesturetranslator.R;
import com.ortin.gesturetranslator.databinding.DevelopCardItemBinding;
import com.ortin.gesturetranslator.databinding.InformationFragmentBinding;
import com.ortin.gesturetranslator.decorators.SpacesItemDecoration;
import com.ortin.models.DevelopCard;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void goToUrl(@NonNull String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void init() {
        List<DevelopCard> cards = new ArrayList<>();
        cards.add(new DevelopCard("PavelStalone", "Team lead, Android developer and ML engineer", R.drawable.pavel_stalone, "https://github.com/PavelStalone", ""));
        cards.add(new DevelopCard("Glebix", "ML engineer, Android and Web developer", R.drawable.glebix, "https://github.com/PoroshinGA", ""));
        cards.add(new DevelopCard("qondeeter", "UI/UX - designer and data engineer", R.drawable.qondeeter, "https://github.com/kond1ter", ""));
        cards.add(new DevelopCard("Sova", "UI/UX - writer and data engineer", R.drawable.sova, "https://github.com/N1kySSS", ""));
        cards.add(new DevelopCard("Glazeezalg", "Analyst", R.drawable.glazeezalg, "https://github.com/glazeezalg", ""));
        cards.add(new DevelopCard("Sever", "Teach lead and mentor", R.drawable.sever, "https://github.com/jacksever", ""));

        CardsAdapter adapter = new CardsAdapter(cards);
        binding.developCardsRV.setAdapter(adapter);
        binding.developCardsRV.addItemDecoration(new SpacesItemDecoration(75));
    }

    class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {
        private List<DevelopCard> data;

        public CardsAdapter(List<DevelopCard> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public CardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            DevelopCardItemBinding itemBinding;
            itemBinding = DevelopCardItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            return new ViewHolder(itemBinding);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            DevelopCard card = data.get(position);
            DevelopCardItemBinding itemBinding = holder.getItemBinding();

            itemBinding.name.setText(card.getName());
            itemBinding.description.setText(card.getDescription());
            itemBinding.icon.setImageDrawable(requireContext().getDrawable(card.getIcon()));

            View.OnClickListener clickListener = view -> {
                String url = "https://github.com/PavelStalone/GestureTranslator";

                if (view == itemBinding.gitHubBTN) {
                    url = card.getGitHub();
                } else if (view == itemBinding.contactBTN) {
                    url = card.getContact();
                }

                goToUrl(url);
            };
            itemBinding.gitHubBTN.setOnClickListener(clickListener);
            itemBinding.contactBTN.setOnClickListener(clickListener);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final DevelopCardItemBinding itemBinding;

            public ViewHolder(@NonNull DevelopCardItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }

            public DevelopCardItemBinding getItemBinding() {
                return itemBinding;
            }
        }
    }

//    private void goToWebApplication(){
//        goToUrl("будет ссылка");
//    }
//
//    private void goToAppGithub(){
//        goToUrl("https://github.com/PavelStalone/GestureTranslator");
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
