package com.aibible.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aibible.R;
import com.aibible.models.Tool;
import com.aibible.adapters.ToolAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Tool> tools;

    public HomeFragment(List<Tool> tools){
        this.tools = tools;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.toolsRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        List<Tool> currentItems = new ArrayList<>();

        for(int i = 0; i < 50; i++){
            currentItems.add(tools.get(i));
        }

        ToolAdapter adapter = new ToolAdapter(currentItems, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
