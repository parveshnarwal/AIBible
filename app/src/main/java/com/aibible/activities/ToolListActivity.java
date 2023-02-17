package com.aibible.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aibible.R;
import com.aibible.adapters.ToolCategoryAdapter;
import com.aibible.adapters.ToolCategoryListAdapter;
import com.aibible.models.Tool;
import com.aibible.models.ToolCategory;

import java.util.ArrayList;
import java.util.List;

public class ToolListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_list);

        RecyclerView recyclerView = findViewById(R.id.toolCategoryRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        List<Tool> tools = (List<Tool>) getIntent().getSerializableExtra("TOOLS_LIST");

        ToolCategoryListAdapter adapter = new ToolCategoryListAdapter(tools, this);
        recyclerView.setAdapter(adapter);
    }
}