package com.aibible;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private List<ToolCategory> toolCategoryList;

    public CategoryFragment(List<ToolCategory> toolCategoryList){
        this.toolCategoryList = toolCategoryList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);


        ToolCategoryAdapter adapter = new ToolCategoryAdapter(toolCategoryList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
