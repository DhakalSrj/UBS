package com.example.ubs.ui.exchange;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubs.R;
import com.example.ubs.databinding.FragmentExchangeBinding;



public class ExchangeFragment extends Fragment {

    private FragmentExchangeBinding binding;
    public Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExchangeViewModel exchangeViewModel =
                new ViewModelProvider(this).get(ExchangeViewModel.class);

        binding = FragmentExchangeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textExchange;
        exchangeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public static class MyAdapter extends RecyclerView.Adapter<PlaceViewHolder> {


        private final Context context;
        private final int[] images;
        private final String[] placeNames;
        private final String[] placeGuide;

        public MyAdapter(Context context, int[] images, String[] placeNames, String[] placeGuide) {
            this.context = context;
            this.images = images;
            this.placeNames = placeNames;
            this.placeGuide = placeGuide;
        }

        @Override
        @NonNull
        public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycleview_row_item, parent, false);
            return new PlaceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
            holder.placeName.setText(placeNames[position]);
            holder.place.setImageResource(images[position]);
            holder.share.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Checkout the new event "
                        + placeNames[holder.getAbsoluteAdapterPosition()] +
                        "\nHere is the link with full info: " + placeGuide[holder.getAbsoluteAdapterPosition()]);
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Send To"));
            });
            holder.visit.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(placeGuide[holder.getAbsoluteAdapterPosition()]));
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return images.length;
        }
    }

   static class PlaceViewHolder extends RecyclerView.ViewHolder {

        ImageView place;
        TextView placeName;
        Button share;
        Button visit;

        PlaceViewHolder(View itemView) {
            super(itemView);

            place = itemView.findViewById(R.id.ivPlace);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            share = itemView.findViewById(R.id.btnShare);
            visit = itemView.findViewById(R.id.btnVisit);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}