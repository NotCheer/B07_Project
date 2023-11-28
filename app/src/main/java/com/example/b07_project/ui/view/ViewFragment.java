package com.example.b07_project.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.b07_project.databinding.FragmentViewBinding;


public class ViewFragment extends Fragment {
    private FragmentViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewViewModel viewViewModel =
                new ViewModelProvider(this).get(ViewViewModel.class);

        binding = FragmentViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView;
        viewViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
