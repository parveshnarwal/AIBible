package com.aibible.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aibible.R;
import com.aibible.models.Tool;
import com.aibible.models.ToolCategory;
import com.aibible.activities.ToolListActivity;
import com.aibible.adapters.ToolCategoryAdapter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


        ToolCategoryAdapter adapter = new ToolCategoryAdapter(toolCategoryList, getContext(), (categoryName) -> {
            startNewActivity(categoryName);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void startNewActivity(String name){

        Optional<ToolCategory> toolCategory = toolCategoryList
                .stream()
                .filter(tc -> tc.categoryName.equals(name))
                .findFirst();

        if(toolCategory.isPresent()){
            List<Tool> tools = toolCategory.get().toolList;
            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getContext(), ToolListActivity.class);
            i.putExtra("TOOLS_LIST", (Serializable) tools);
            requireContext().startActivity(i);
        }


    }
}
