package com.aibible;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ToolCategoryAdapter extends RecyclerView.Adapter<ToolCategoryAdapter.CategoryViewHolder>{

    private final List<ToolCategory> toolCategoryList;
    private Context context;

    public ToolCategoryAdapter(List<ToolCategory> toolCategoryList, Context context){

        this.toolCategoryList = toolCategoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ToolCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new ToolCategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ToolCategory tc = toolCategoryList.get(position);
        holder.button.setText(tc.categoryName);
        @SuppressLint("DefaultLocale") String count = String.format("%02d", tc.count);
        holder.badge.setText(count);
    }

    @Override
    public int getItemCount() {
        return toolCategoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private final Button button;
        private final TextView badge;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.categoryButton);
            badge = itemView.findViewById(R.id.tvBadge);
        }
    }
}
