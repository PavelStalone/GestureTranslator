package com.ortin.gesturetranslator.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ortin.gesturetranslator.databinding.DevelopCardItemBinding;
import com.ortin.gesturetranslator.app.models.DeveloperCard;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {
    private final List<DeveloperCard> data;
    private final Context context;

    public CardsAdapter(List<DeveloperCard> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsAdapter.ViewHolder(DevelopCardItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DeveloperCard card = data.get(position);
        DevelopCardItemBinding itemBinding = holder.getItemBinding();

        itemBinding.name.setText(card.getName());
        itemBinding.description.setText(card.getDescription());
        itemBinding.icon.setImageResource(card.getIcon());

        View.OnClickListener clickListener = view -> {
            String url = "https://github.com/PavelStalone/GestureTranslator";

            if (view == itemBinding.gitHubBTN) {
                url = card.getGitHub();
            } else if (view == itemBinding.contactBTN) {
                url = card.getContact();
            }

            goToUrl(url, context);
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
}
