package com.example.b07_project.ui.announcement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07_project.databinding.FragmentAnnouncementBinding;

public class AnnouncementFragment extends Fragment {

    private FragmentAnnouncementBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnnouncementViewModel announcementViewModel =
                new ViewModelProvider(this).get(AnnouncementViewModel.class);

        binding = FragmentAnnouncementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAnnoucement;
        announcementViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}