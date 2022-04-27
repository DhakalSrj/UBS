package com.example.ubs.ui.communication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubs.databinding.FragmentCommunicationBinding;


public class CommunicationFragment extends Fragment {

    private FragmentCommunicationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommunicationViewModel communicationViewModel =
                new ViewModelProvider(this).get(CommunicationViewModel.class);

        binding = FragmentCommunicationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCommunication;
        communicationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}