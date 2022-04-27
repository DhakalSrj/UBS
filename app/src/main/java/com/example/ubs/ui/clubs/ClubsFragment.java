package com.example.ubs.ui.clubs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubs.databinding.FragmentClubsBinding;

public class ClubsFragment extends Fragment {

    private FragmentClubsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClubsViewModel clubsViewModel =
                new ViewModelProvider(this).get(ClubsViewModel.class);

        binding = com.example.ubs.databinding.FragmentClubsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textClubs;
        clubsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}