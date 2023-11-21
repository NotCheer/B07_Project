package com.example.b07_project.AnnouncementModule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnnouncementFragment extends Fragment {
    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }
    protected RecyclerView mRecyclerView;
    protected AnnouncementAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Announcement> announcementList;

    public void onCreate() {
        Bundle bundle = null;
        super.onCreate(bundle);
        
    }
}
